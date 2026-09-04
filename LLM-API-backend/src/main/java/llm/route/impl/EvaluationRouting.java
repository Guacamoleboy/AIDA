package llm.route.impl;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import llm.controller.EvaluationController;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class EvaluationRouting {

    // Attributes
    private final EvaluationController evaluationController;

    // _________________________________________________________________________________________________________________

    public EvaluationRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.evaluationController = new EvaluationController(em);
    }

    public EndpointGroup routes() {
        return () -> {

            path("/evaluate", () -> {

                post("/", evaluationController::evaluate);

            });

        };

    }

}
