package com.example.dataintegration.services;

import com.example.dataintegration.util.CsvToModel;
import com.example.dataintegration.util.DynamicConfigurationSingleton;
import com.example.dataintegration.models.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class CsvParsingServiceImpl implements CsvParsingService {
    @Override
    public List<JsonObject> parseCsv(MultipartFile file) {
        List<JsonObject> resultList = new ArrayList<>();

        DynamicConfiguration config = DynamicConfigurationSingleton.getInstance();

        Map<String, DynamicConfigurationField> configMap = new HashMap<>();
        for (DynamicConfigurationField fieldConfig : config.getFields()) {
            configMap.put(fieldConfig.getSourceField(), fieldConfig);
        }

        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (CSVReader reader = new CSVReader(new FileReader((File) tempFile))) {

            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                JsonObject model = new JsonObject();
                boolean addRecord = true;
                JsonPayComponent payComponent = new JsonPayComponent();
                boolean generateEmpCode = false;

                for (int i = 0; i < headers.length; i++) {
                    String header = headers[i];
                    Object value = line[i];

                    if (configMap.containsKey(header)) {
                        DynamicConfigurationField fieldConfig = configMap.get(header);

                        if (fieldConfig.isMandatory() && value.toString().isBlank()) {
                            if(model.getAction().equals("hire") && fieldConfig.getFieldType().equals("EmployeeCode")){
                                generateEmpCode = true;
                                continue;
                            } else {
                                addRecord = false;
                                break;
                            }
                        }

                        if(generateEmpCode && fieldConfig.getTargetField().equals("hire_date")){
                            model.setEmployeeCode(CsvToModel.generateEmployeeCode((String) value));
                        }

                        if (fieldConfig.getDataType() == DataType.Integer) {
                            value = Integer.parseInt((String) value);
                        } else if (fieldConfig.getDataType() == DataType.Decimal) {
                            value = Double.parseDouble((String) value);
                        } else if (fieldConfig.getDataType() == DataType.Text || fieldConfig.getDataType() == DataType.Date) {
                            value = value.toString();
                        } else if (fieldConfig.getDataType() == DataType.Bool) {
                            value = Boolean.parseBoolean((String) value);
                        }

                        if (fieldConfig.getFieldType().getTypeName().equals("Regular") && fieldConfig.getTargetEntity().equals("salary_component")) {
                            // This logic is responsible for adding new payComponents to the payComponents array.
                            // Please note that this operation assumes that the data columns in the CSV file are in the correct order,
                            // as the mapping of each column to a payComponent field relies on the order.
                            // (pay_amount, pay_currency,..., compensation_amount, compensation_currency)

                            payComponent = (JsonPayComponent) payComponent.setFieldIfEmpty(fieldConfig.getTargetField(), value);

                            if (payComponent.areAllFieldsFilled()) {
                                List<JsonPayComponent> currentPayComponents = model.getPayComponents();
                                if (currentPayComponents == null) {
                                    currentPayComponents = new ArrayList<>();
                                }
                                currentPayComponents.add(payComponent);
                                model.setPayComponents(currentPayComponents);
                                payComponent = new JsonPayComponent();
                            }
                        } else {
                            model = CsvToModel.mapCsvToModelField(model, value, fieldConfig);
                        }
                    }
                }

                if (addRecord) {
                    resultList.add(model);
                }
            }


        } catch (IOException | CsvValidationException e) {
            tempFile.delete();
            e.printStackTrace();
            return null;
        }
        tempFile.delete();
        return resultList;
    }

}
