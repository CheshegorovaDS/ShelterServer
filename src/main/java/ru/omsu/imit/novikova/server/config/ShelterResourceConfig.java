package ru.omsu.imit.novikova.server.config;

import org.glassfish.jersey.server.ResourceConfig;

public class ShelterResourceConfig extends ResourceConfig {
    public ShelterResourceConfig() {
        packages("ru.omsu.imit.novikova.resources",
                 "ru.omsu.imit.novikova.rest.mappers");
    }
}
