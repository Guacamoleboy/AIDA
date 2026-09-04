package llm;

import llm.server.Server;

public class Main {

    // Attributes
    private static final Server server = new Server();

    // _________________________________________________________________________________________________________________

    public static void main(String[] args) {

        server.start();

    }

}