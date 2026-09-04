package llm.controller;

import io.javalin.http.UploadedFile;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import llm.dto.external.response.EvaluationResponse;
import llm.exception.ApiException;
import llm.service.external.EvaluationService;

public class EvaluationController {

    // Attributes
    private final EntityManager em;
    private final EvaluationService evaluationService = new EvaluationService();

    // _________________________________________________________________________________________________________________

    public EvaluationController(EntityManager em) {
        this.em = em;
    }

    // _________________________________________________________________________________________________________________

    public void evaluate(Context ctx) {

        // DEBUG
        System.out.println("Controller START");

        UploadedFile uploadedFile = ctx.uploadedFile("file");

        System.out.println("File: " +
                (uploadedFile != null ? uploadedFile.filename() : "NULL"));

        if (uploadedFile == null) {
            throw new ApiException(400, "No file provided");
        }

        System.out.println("Calling EvaluationService from Controller");

        EvaluationResponse evaluationResponse =
                evaluationService.evaluate(uploadedFile);

        System.out.println("EvaluationService returned");

        ctx.json(evaluationResponse);

        System.out.println("Response sent");

    }

}