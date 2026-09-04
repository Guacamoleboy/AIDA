package llm.route.impl;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import llm.controller.OpenAIController;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class OpenAIRouting {

    // Attributes
    private final OpenAIController openAIController;

    // _________________________________________________________________________________________________________________

    public OpenAIRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.openAIController = new OpenAIController(em);
    }

    public EndpointGroup routes() {
        return () -> {

            path("/openai", () -> {

                post("/evaluate", openAIController::sendRequest);

            });

        };



    }

}
