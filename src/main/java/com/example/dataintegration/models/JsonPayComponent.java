package com.example.dataintegration.models;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonPayComponent {
    private BigDecimal amount;
    private String currency;
    private String startDate;
    private String endDate;

    public JsonPayComponent() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean areAllFieldsFilled() {
        if (amount == null || currency == null || startDate == null || endDate == null ||
                amount.compareTo(BigDecimal.ZERO) == 0 || currency.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            return false;
        }
        return true;
    }

    public Object setFieldIfEmpty(String fieldName, Object value) {
        try {
            Field field;

            if(fieldName.equals("start_date")) {
                field = getClass().getDeclaredField("startDate");
            } else if(fieldName.equals("end_date")){
                field = getClass().getDeclaredField("endDate");
            } else {
                field = getClass().getDeclaredField(fieldName);
            }

            if(fieldName.equals("start_date") || fieldName.equals("end_date")) {
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("ddMMyy");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date = inputDateFormat.parse((String) value);
                    value = outputDateFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            field.setAccessible(true);
            if (field.get(this) == null) {

                if ("amount".equals(fieldName) && value != null) {
                    Double doubleObject = (Double) value;
                    BigDecimal parsedValue = BigDecimal.valueOf(doubleObject);
                    field.set(this, parsedValue);
                } else {
                    field.set(this, value);
                }
                return this;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
