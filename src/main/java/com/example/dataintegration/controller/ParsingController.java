package com.example.dataintegration.controller;

import com.example.dataintegration.util.DynamicConfigurationSingleton;
import com.example.dataintegration.models.JsonObject;
import com.example.dataintegration.models.JsonRequest;
import com.example.dataintegration.services.CsvParsingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class ParsingController {

    @Autowired
    CsvParsingService parsingService;

    @PostMapping("/csv")
    public ResponseEntity<JsonRequest> uploadCSV(@RequestParam("file") MultipartFile file) throws JsonProcessingException {
        List<String> errors = new ArrayList<>();
        JsonRequest requestSample = new JsonRequest();

        if (file.isEmpty()) {
            errors.add("File not found.");
            requestSample.setErrors(errors);
            return new ResponseEntity<JsonRequest>(requestSample, HttpStatus.BAD_REQUEST);
        }

        String fileName = file.getOriginalFilename();
        String pattern = DynamicConfigurationSingleton.getInstance().getFileNamePattern();

        if (!(fileName != null && fileName.matches(pattern))) {
            errors.add("Invalid file. Please upload a CSV file with filename as (input_[0-9]).");
            requestSample.setErrors(errors);
            return new ResponseEntity<JsonRequest>(requestSample, HttpStatus.BAD_REQUEST);
        }

        List<JsonObject> parsedCsv = this.parsingService.parseCsv(file);

        if (parsedCsv == null) {
            errors.add("Something went wrong. Please try again");
            requestSample.setErrors(errors);
            return new ResponseEntity<JsonRequest>(requestSample, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        requestSample
                .setUuid(UUID.randomUUID().toString())
                .setFname(fileName)
                .setErrors(errors)
                .setPayload(parsedCsv);

        return new ResponseEntity<JsonRequest>(requestSample, HttpStatus.OK);
    }

}
