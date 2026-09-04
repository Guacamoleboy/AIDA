package llm.controller;

import com.openai.models.responses.Response;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import llm.dto.external.request.EvaluationRequest;
import llm.dto.external.request.OpenAIRequest;
import llm.service.external.OpenAIService;

public class OpenAIController {

    // Attributes
    private final EntityManager em;
    private final OpenAIService openAIService = new OpenAIService();

    // _________________________________________________________________________________________________________________

    public OpenAIController(EntityManager em) {
        this.em = em;
    }

    // _________________________________________________________________________________________________________________

    public void sendResponse() {
        // N/A for now
    }

    // _________________________________________________________________________________________________________________

    public void sendRequest(Context ctx) {

        // Initial setup
        EvaluationRequest request = ctx.bodyAsClass(EvaluationRequest.class);
        OpenAIRequest openAIRequest = new OpenAIRequest();

        // Model information
        openAIRequest.setModel("gpt-5.6-luna");
        openAIRequest.setInstructions("Not added for now.");
        openAIRequest.setInput(request.getSubmission());

        // Response and request
        Response response = openAIService.sendRequest(openAIRequest);
        String result = openAIService.sendResponse(response);

        // Result
        ctx.result(result);

    }

}