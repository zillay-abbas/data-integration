package com.example.dataintegration.services;

import com.example.dataintegration.models.JsonObject;

import java.util.List;

public interface DataIntegrationService {
    public String processDataAdd(JsonObject jsonObject);
    public String processDataUpdate(JsonObject jsonObject);
    public String processDataDelete(JsonObject jsonObject);
}
