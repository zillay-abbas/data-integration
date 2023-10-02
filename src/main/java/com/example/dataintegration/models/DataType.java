package com.example.dataintegration.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataType {
    Text("Text"),
    Integer("Integer"),
    Decimal("Decimal"),
    Bool("Bool"),
    Date("Date");

    private final String typeName;

    DataType(String typeName) {
        this.typeName = typeName;
    }

    @JsonValue
    public String getTypeName() {
        return typeName;
    }
}
