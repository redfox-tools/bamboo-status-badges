package tools.redfox.bamboo.status.service;

import tools.redfox.bamboo.status.Status;

public class BadgeBuilder {
    public static String url(Status status, String label, String value) {
        return String.format(
                "https://img.shields.io/static/v1.svg?label=%s&message=%s&color=%s",
                label,
                value,
                status.colour()
        );
    }

    public static String unknownDeployment() {
        return url(Status.UNKNOWN, "Deployment", "UNKNOWN");
    }

    public static String unknownDeployment(String env) {
        return deployment(Status.UNKNOWN, env, "UNKNOWN");
    }

    public static String deployment(Status status, String env, String value) {
        return url(status, String.format("Environment: '%s'", env), value);
    }
}
