package com.example.dataintegration.models;

import java.util.List;

public class JsonRequest {
    private String uuid;
    private String fname;
    private List<String> errors;
    private List<JsonObject> payload;

    public JsonRequest() {
    }

    public JsonRequest(String uuid, String fname, List<String> errors, List<JsonObject> payload) {
        this.uuid = uuid;
        this.fname = fname;
        this.errors = errors;
        this.payload = payload;
    }

    public String getUuid() {
        return uuid;
    }

    public JsonRequest setUuid(String uuid) {
        this.uuid = uuid;
         return this;
    }

    public String getFname() {
        return fname;
    }

    public JsonRequest setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public JsonRequest setErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public List<JsonObject> getPayload() {
        return payload;
    }

    public JsonRequest setPayload(List<JsonObject> payload) {
        this.payload = payload;
        return this;
    }
}
