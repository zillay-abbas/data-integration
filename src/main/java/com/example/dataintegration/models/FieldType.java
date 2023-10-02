package com.example.dataintegration.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FieldType {
        Regular("Regular"),
        ActionCode("ActionCode"),
        EmployeeCode("EmployeeCode");

        private final String typeName;

        FieldType(String typeName) {
            this.typeName = typeName;
        }

        @JsonValue
        public String getTypeName() {
            return typeName;
        }
}
