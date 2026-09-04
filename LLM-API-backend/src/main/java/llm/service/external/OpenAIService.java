package llm.service.external;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import llm.config.DotEnv;
import llm.dto.external.request.OpenAIRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class OpenAIService {

    // Attributes
    private final OpenAIClient openAIClient;

    // _________________________________________________________________________________________________________________

    public OpenAIService() {

        // DOTENV Setup
        DotEnv dotEnv = new DotEnv();
        String apiKey = dotEnv.get("OPENAI_API_KEY");

        // Client Setup
        this.openAIClient = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
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
                .replace("{{SUBMISSION}}", submission);

        // OpenAI request
        OpenAIRequest openAIRequest = new OpenAIRequest();

        openAIRequest.setModel("gpt-5.6-luna");
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

        return openAIClient.responses().create(responseCreateParams);

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