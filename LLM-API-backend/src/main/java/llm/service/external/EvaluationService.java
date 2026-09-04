package llm.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.UploadedFile;
import llm.dto.external.response.EvaluationResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EvaluationService {

    // Attributes
    private final OpenAIService openAIService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // _________________________________________________________________________________________________________________

    public EvaluationService() {
        openAIService = new OpenAIService();
    }

    // _________________________________________________________________________________________________________________

    public EvaluationResponse evaluate(UploadedFile uploadedFile) {

        try {

            // Read submission
            String submission = new String(
                    uploadedFile.content().readAllBytes(),
                    StandardCharsets.UTF_8
            );

            System.out.println("Uploaded file: " + uploadedFile.filename());
            System.out.println("Submission length: " + submission.length());

            // Send submission to OpenAI
            String result = openAIService.evaluateSubmission(submission);

            System.out.println("OpenAI result:");
            System.out.println(result);

            // Convert JSON response to DTO
            return objectMapper.readValue(
                    result,
                    EvaluationResponse.class
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not process evaluation file",
                    e
            );
        }

    }

}
