package nl.alphaport.messagingapp.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2023-12-20   1:40 PM
 */
public final class DefaultProfileUtil {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil() {
    }

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap();
        defProperties.put("spring.profiles.default", "dev");
        app.setDefaultProperties(defProperties);
    }

    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        return profiles.length == 0 ? env.getDefaultProfiles() : profiles;
    }
}
