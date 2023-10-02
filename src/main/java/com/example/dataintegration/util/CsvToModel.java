package com.example.dataintegration.util;

import com.example.dataintegration.models.DynamicConfigurationField;
import com.example.dataintegration.models.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvToModel {
    public static String validateBirthdate(String datePart, DynamicConfigurationField fieldConfig) {
        Pattern pattern = Pattern.compile(fieldConfig.getValidationPattern());
        Matcher matcher = pattern.matcher(datePart);

        if (matcher.matches()) {
            return datePart.substring(0, 6);
        } else {
            return null;
        }
    }

    public static JsonObject mapCsvToModelField(JsonObject modelParam, Object value, DynamicConfigurationField fieldConfig) {
        JsonObject model = modelParam;

        if (!fieldConfig.getFieldType().getTypeName().equals("Regular")) {
            if (fieldConfig.getSourceField().equals("contract_workerId")) {
                model.setEmployeeCode(value.toString());
            } else {
                model.setAction(value.toString().toLowerCase());
            }
        } else {
            String key = fieldConfig.getTargetEntity() + "." + fieldConfig.getTargetField();
            SimpleDateFormat inputDateFormat;
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (fieldConfig.getTargetField().equals("gender")) {
                if (value.toString().equalsIgnoreCase("male")) {
                    value = 'M';
                } else {
                    value = 'F';
                }
            }

            if (fieldConfig.getTargetField().equals("birthdate")) {
                inputDateFormat = new SimpleDateFormat("yyMMdd");
                String bDay = validateBirthdate((String) value, fieldConfig);

                if (bDay == null) {
                    value = null;
                } else {
                    try {
                        Date date = inputDateFormat.parse((String) value);
                        value = outputDateFormat.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }


            if (fieldConfig.getTargetField().equals("hire_date")) {
                inputDateFormat = new SimpleDateFormat("ddMMyy");
                try {
                    Date date = inputDateFormat.parse((String) value);
                    value = outputDateFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            Map<String, Object> modalData = model.getData();
            if (modalData == null) {
                modalData = new HashMap<>();
            }
            modalData.put(key, value);

            model.setData(modalData);
        }

        return model;
    }

    public static String generateEmployeeCode(String startDate) {
        if (startDate.length() == 5) {
            startDate = "0" + startDate;
        }

        Random random = new Random();
        int orderNumber = random.nextInt(256);
        String orderNumberHex = String.format("%02X", orderNumber);

        return startDate + orderNumberHex;
    }
}
