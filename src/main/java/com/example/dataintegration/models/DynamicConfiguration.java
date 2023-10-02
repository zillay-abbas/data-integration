package com.example.dataintegration.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DynamicConfiguration {
    private final String fileNamePattern;
    private final List<DynamicConfigurationField> fields;
    private final Map<String, Map<String, String>> mappings;

    public DynamicConfiguration() {
        this.fileNamePattern = "";
        this.fields = new ArrayList<>();
        this.mappings = new HashMap<>();
    }

    public DynamicConfiguration(String fileNamePattern, List<DynamicConfigurationField> fields, Map<String, Map<String, String>> mappings) {
        this.fileNamePattern = fileNamePattern;
        this.fields = fields;
        this.mappings = mappings;
    }

    public boolean validateFilename(String input) {
        return Pattern.compile(fileNamePattern).matcher(input).matches();
    }

    public String tryMap(String mappingKey, String input) {
        if (!mappings.containsKey(mappingKey)) {
            return null;
        }
        return mappings.get(mappingKey).getOrDefault(input, null);
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public List<DynamicConfigurationField> getFields() {
        return fields;
    }

    public Map<String, Map<String, String>> getMappings() {
        return mappings;
    }

    public static final String GLOBAL_DATE_FORMAT = "yyyy-MM-dd";
}
