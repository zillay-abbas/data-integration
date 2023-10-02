package com.example.dataintegration.models;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DynamicConfigurationField {

    private FieldType fieldType = FieldType.Regular;
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private boolean isMandatory = false;

    private String sourceField;
    private String targetEntity;
    private String targetField;

    private DataType dataType = DataType.Text;
    private String mappingKey;
    private String dateFormat;
    private String validationPattern;
    private Integer regexCaptureGroupNr;

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public String getSourceField() {
        return sourceField;
    }

    public void setSourceField(String sourceField) {
        this.sourceField = sourceField;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getMappingKey() {
        return mappingKey;
    }

    public void setMappingKey(String mappingKey) {
        this.mappingKey = mappingKey;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getValidationPattern() {
        return validationPattern;
    }

    public void setValidationPattern(String validationPattern) {
        this.validationPattern = validationPattern;
    }

    public Integer getRegexCaptureGroupNr() {
        return regexCaptureGroupNr;
    }

    public void setRegexCaptureGroupNr(Integer regexCaptureGroupNr) {
        this.regexCaptureGroupNr = regexCaptureGroupNr;
    }

    @Override
    public String toString() {
        return fieldType + "\t" + dataType + "\t" + sourceField + (fieldType == FieldType.Regular ? "\t->\t" + entityKey() : "");
    }

    private String entityKey() {
        return targetEntity != null ? targetEntity + "." + targetField : "";
    }
}
