package llm.controller;

import io.javalin.http.UploadedFile;
import io.javalin.http.Context;
import llm.dto.external.response.EvaluationResponse;
import llm.exception.ApiException;
import llm.service.external.EvaluationService;

public class EvaluationController {

    // Attributes
    private final EvaluationService evaluationService = new EvaluationService();

    // _________________________________________________________________________________________________________________

    public void evaluate(Context ctx) {

        UploadedFile uploadedFile = ctx.uploadedFile("file");

        if (uploadedFile == null) {
            throw new ApiException(400, "No file provided");
        }

        EvaluationResponse evaluationResponse =
                evaluationService.evaluate(uploadedFile);

        ctx.json(evaluationResponse);

    }

}
