package com.example.dataintegration.util;


import com.example.dataintegration.models.DynamicConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class DynamicConfigurationSingleton {
    private static DynamicConfiguration instance;

    private DynamicConfigurationSingleton() {
    }

    public static synchronized DynamicConfiguration getInstance() {
        if (instance == null) {
            instance = loadConfiguration();
        }
        return instance;
    }

    private static DynamicConfiguration loadConfiguration() {
        try {
            ClassPathResource resource = new ClassPathResource("config/dynamic_config.json");
            return new ObjectMapper().readValue(resource.getInputStream(), DynamicConfiguration.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration.", e);
        }
    }
}

