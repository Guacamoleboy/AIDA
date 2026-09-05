package llm.service.external;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.errors.OpenAIRetryableException;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import llm.config.OpenAIConfig;
import llm.dto.external.request.OpenAIRequest;
import llm.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;

public class OpenAIService {

    // Attributes
    private final OpenAIClient openAIClient;
    private static final Deque<Long> REQUEST_TIMESTAMPS = new ArrayDeque<>();

    // _________________________________________________________________________________________________________________

    public OpenAIService() {

        this.openAIClient = OpenAIOkHttpClient.builder()
                .apiKey(OpenAIConfig.getApiKey())
                .build();

    }

    // _________________________________________________________________________________________________________________

    public String sendResponse(Response response) {

        return response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .findFirst()
                .orElse("");

    }

    // _________________________________________________________________________________________________________________

    public String evaluateSubmission(String submission) {

        // Prompts
        String systemPrompt = loadResource(
                "prompts/system-prompt.md"
        );

        String userPrompt = loadResource(
                "prompts/user-prompt.txt"
        );

        String rubric = loadResource(
                "rubric/rubric.json"
        );

        // Input
        String input = userPrompt
                .replace("{{RUBRIC}}", rubric)
                .replace("{{STUDENT_REPORT}}", submission);

        // OpenAI request
        OpenAIRequest openAIRequest = new OpenAIRequest();

        openAIRequest.setModel(OpenAIConfig.getModel());
        openAIRequest.setInstructions(systemPrompt);
        openAIRequest.setInput(input);

        // Request
        Response response = sendRequest(openAIRequest);

        // Response
        return sendResponse(response);

    }

    // _________________________________________________________________________________________________________________

    public Response sendRequest(OpenAIRequest openAIRequest) {

        ResponseCreateParams responseCreateParams = ResponseCreateParams.builder()
                .model(openAIRequest.getModel())
                .instructions(openAIRequest.getInstructions())
                .input(openAIRequest.getInput())
                .build();

        for (int attempt = 1; attempt <= OpenAIConfig.getMaxAttempts(); attempt++) {
            acquireRequestSlot();

            try {
                return openAIClient.responses().create(responseCreateParams);
            } catch (OpenAIRetryableException e) {
                if (attempt == OpenAIConfig.getMaxAttempts()) {
                    throw new ApiException(
                            503,
                            "OpenAI could not process the evaluation after "
                                    + OpenAIConfig.getMaxAttempts() + " attempts",
                            e
                    );
                }

                waitBeforeRetry(attempt);
            }
        }

        throw new ApiException(503, "OpenAI could not process the evaluation");

    }

    // _________________________________________________________________________________________________________________

    private static synchronized void acquireRequestSlot() {
        long now = System.currentTimeMillis();
        long oneMinuteAgo = now - 60_000L;

        while (!REQUEST_TIMESTAMPS.isEmpty()
                && REQUEST_TIMESTAMPS.peekFirst() <= oneMinuteAgo) {
            REQUEST_TIMESTAMPS.removeFirst();
        }

        if (REQUEST_TIMESTAMPS.size() >= OpenAIConfig.getMaxRequestsPerMinute()) {
            throw new ApiException(
                    429,
                    "Evaluation limit reached. Try again in a minute"
            );
        }

        REQUEST_TIMESTAMPS.addLast(now);
    }

    // _________________________________________________________________________________________________________________

    private void waitBeforeRetry(int attempt) {
        long delay = OpenAIConfig.getInitialRetryDelayMillis()
                * (1L << (attempt - 1));

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(503, "OpenAI retry was interrupted", e);
        }
    }

    // _________________________________________________________________________________________________________________

    private String loadResource(String path) {

        try (InputStream inputStream =
                     OpenAIService.class
                             .getClassLoader()
                             .getResourceAsStream(path)) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "Resource not found: " + path
                );
            }

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not read resource: " + path,
                    e
            );
        }

    }

}
