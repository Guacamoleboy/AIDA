package llm.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import llm.route.impl.EvaluationRouting;
import llm.route.impl.OpenAIRouting;

public class Routes {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static EndpointGroup registerRoutes(EntityManagerFactory emf) {

        // Routings
        OpenAIRouting openAIRouting = new OpenAIRouting(emf);
        EvaluationRouting evaluationRouting = new EvaluationRouting(emf);

        // EndpointGroup Return to server
        return () -> {
            openAIRouting.routes().addEndpoints();
            evaluationRouting.routes().addEndpoints();
        };

    }

}