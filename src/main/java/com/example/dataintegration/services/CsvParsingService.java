package com.example.dataintegration.services;

import com.example.dataintegration.models.JsonObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvParsingService {
    public List<JsonObject> parseCsv(MultipartFile file);

}
