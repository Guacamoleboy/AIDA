package llm.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.UploadedFile;
import llm.dto.external.response.EvaluationResponse;
import llm.exception.ApiException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

            if (submission.isBlank()) {
                throw new ApiException(400, "The uploaded file is empty");
            }

            // Send submission to OpenAI
            String result = openAIService.evaluateSubmission(submission);

            EvaluationResponse evaluationResponse = objectMapper.readValue(
                    extractJson(result),
                    EvaluationResponse.class);

            calculateOverallScore(evaluationResponse);
            validateSuggestions(evaluationResponse);

            return evaluationResponse;

        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new ApiException(
                    502,
                    "OpenAI returned an invalid evaluation response",
                    e
            );
        } catch (IOException e) {
            throw new ApiException(
                    400,
                    "Could not read the uploaded file",
                    e
            );
        }

    }

    // _________________________________________________________________________________________________________________

    private String extractJson(String result) {
        String trimmedResult = result.trim();

        if (trimmedResult.startsWith("```")) {
            int firstNewLine = trimmedResult.indexOf('\n');
            int closingFence = trimmedResult.lastIndexOf("```");

            if (firstNewLine >= 0 && closingFence > firstNewLine) {
                return trimmedResult.substring(firstNewLine + 1, closingFence).trim();
            }
        }

        return trimmedResult;
    }

    // _________________________________________________________________________________________________________________

    private void validateSuggestions(EvaluationResponse evaluationResponse) {
        if (evaluationResponse.getSuggestions() == null
                || evaluationResponse.getSuggestions().size() < 4
                || evaluationResponse.getSuggestions().size() > 6) {
            throw new ApiException(
                    502,
                    "OpenAI must return between 4 and 6 suggestions"
            );
        }
    }

    // _________________________________________________________________________________________________________________

    private void calculateOverallScore(EvaluationResponse evaluationResponse) {
        if (evaluationResponse.getCriteria() == null
                || evaluationResponse.getCriteria().isEmpty()) {
            throw new ApiException(502, "OpenAI did not return any criterion scores");
        }

        double totalScore = evaluationResponse.getCriteria().stream()
                .mapToDouble(criterion -> criterion.getScore())
                .peek(this::validateCriterionScore)
                .sum();

        double overallScore = totalScore / evaluationResponse.getCriteria().size();
        evaluationResponse.setOverallScore(
                BigDecimal.valueOf(overallScore)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue()
        );
    }

    // _________________________________________________________________________________________________________________

    private void validateCriterionScore(double score) {
        if (!Double.isFinite(score) || score < 0.0 || score > 10.0) {
            throw new ApiException(502, "OpenAI returned a criterion score outside 0.0 to 10.0");
        }
    }

}
