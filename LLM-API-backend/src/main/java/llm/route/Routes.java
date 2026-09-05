package llm.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import llm.route.impl.EvaluationRouting;

public class Routes {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static EndpointGroup registerRoutes(EntityManagerFactory emf) {

        // Routings
        EvaluationRouting evaluationRouting = new EvaluationRouting(emf);

        // EndpointGroup Return to server
        return () -> {
            evaluationRouting.routes().addEndpoints();
        };

    }

}
