package llm.config;

public final class OpenAIConfig {

    // Attributes
    private static final String MODEL = "gpt-5.6-luna";
    private static final int MAX_REQUESTS_PER_MINUTE = 10;
    private static final int MAX_ATTEMPTS = 3;
    private static final long INITIAL_RETRY_DELAY_MILLIS = 1_000L;

    // _________________________________________________________________________________________________________________

    private OpenAIConfig() {

    }

    // _________________________________________________________________________________________________________________

    public static String getApiKey() {
        return DotEnv.get("OPENAI_API_KEY");
    }

    // _________________________________________________________________________________________________________________

    public static String getModel() {
        return MODEL;
    }

    // _________________________________________________________________________________________________________________

    public static int getMaxRequestsPerMinute() {
        return MAX_REQUESTS_PER_MINUTE;
    }

    // _________________________________________________________________________________________________________________

    public static int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    // _________________________________________________________________________________________________________________

    public static long getInitialRetryDelayMillis() {
        return INITIAL_RETRY_DELAY_MILLIS;
    }

}
