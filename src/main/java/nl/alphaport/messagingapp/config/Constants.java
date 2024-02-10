package nl.alphaport.messagingapp.config;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2023-12-20   1:41 PM
 */
public final class Constants {
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    public static final String SPRING_PROFILE_H2_CONSOLE = "h2-console";
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";
    public static final String SPRING_PROFILE_K8S = "k8s";
    public static final String REQUEST_UUID = "request-uuid";
    public static final String REQUEST_IP = "request-ip";
    public static final String REQUEST_PRINCIPLE = "request-principle";


    public static final String X_DELAY = "x-delay";
    public static final String X_DELAY_TYPE = "x-delayed-type";
    public static final String X_DELAY_MESSAGE = "x-delayed-message";
    public static final String QUEUE_DIRECT_TYPE = "direct";

    private Constants() {
    }
}
