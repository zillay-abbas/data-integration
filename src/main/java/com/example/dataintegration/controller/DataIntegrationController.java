package com.example.dataintegration.controller;

import com.example.dataintegration.models.JsonObject;
import com.example.dataintegration.models.JsonRequest;
import com.example.dataintegration.services.DataIntegrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataIntegrationController {
    @Autowired
    DataIntegrationService dataIntegrationService;

    @PostMapping("/integrate")
    public ResponseEntity<String> integrateData(@RequestBody JsonRequest requestJson) throws JsonProcessingException {
        String uuid = requestJson.getUuid();
        String fname = requestJson.getFname();
        List<String> errors = requestJson.getErrors();
        List<JsonObject> payload = requestJson.getPayload();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseNode = objectMapper.createObjectNode();

        if (uuid == null || uuid.isEmpty() || fname == null
                || fname.isEmpty() || errors == null || payload == null) {
            return new ResponseEntity<String>("Invalid Request", HttpStatus.BAD_REQUEST);
        }

        if (payload.size() > 0) {
            for (JsonObject jsonObject : payload) {
                if(jsonObject.getAction().equalsIgnoreCase("add")){
                    String addResponse = dataIntegrationService.processDataAdd(jsonObject);
                    responseNode.put("Addition Process", addResponse);
                } else if (jsonObject.getAction().equalsIgnoreCase("hire")
                        || jsonObject.getAction().equalsIgnoreCase("change")
                        || jsonObject.getAction().equalsIgnoreCase("terminate")
                        || jsonObject.getAction().equalsIgnoreCase("update")){
                    String updateResponse = dataIntegrationService.processDataUpdate(jsonObject);
                    responseNode.put("Updation Process", updateResponse);
                } else if(jsonObject.getAction().equalsIgnoreCase("delete")) {
                    String delResponse = dataIntegrationService.processDataDelete(jsonObject);
                    responseNode.put("Deletion Process", delResponse);
                }
            }
        }

        return new ResponseEntity<String>(responseNode.toString(), HttpStatus.OK);
    }

}
