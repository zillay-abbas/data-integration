package com.example.dataintegration.models;

import java.util.List;
import java.util.Map;

public class JsonObject {
    private String employeeCode;
    private String action;
    private Map<String, Object> data;
    private List<JsonPayComponent> payComponents;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List<JsonPayComponent> getPayComponents() {
        return payComponents;
    }

    public void setPayComponents(List<JsonPayComponent> payComponents) {
        this.payComponents = payComponents;
    }
}
