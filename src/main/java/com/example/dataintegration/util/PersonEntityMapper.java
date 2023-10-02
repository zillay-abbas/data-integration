package com.example.dataintegration.util;

import com.example.dataintegration.entities.PersonEntity;
import com.example.dataintegration.models.Gender;

import java.sql.Date;
import java.util.Map;

public class PersonEntityMapper {
    public static PersonEntity mapToPersonEntity(Map<String, Object> data) {
        PersonEntity personEntity = new PersonEntity();

        String fullName = (String) data.get("person.full_name");
        if (fullName != null && !fullName.isEmpty()) {
            personEntity.setFullName(fullName);
        } else {
            throw new IllegalArgumentException("Full name is required.");
        }

        String genderValue = (String) data.get("person.gender");
        if (genderValue != null) {
            try {
                personEntity.setGender(Gender.valueOf(genderValue));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid gender value.");
            }
        }

        String birthdateValue = (String) data.get("person.birthdate");
        if (birthdateValue != null) {
            try {
                personEntity.setBirthdate(Date.valueOf(birthdateValue));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid date format for birthdate.");
            }
        }

        String employeeCode = (String) data.get("person.employee_code");
        if (employeeCode != null && !employeeCode.isEmpty()) {
            personEntity.setEmployeeCode(employeeCode);
        } else {
            throw new IllegalArgumentException("Employee code is required.");
        }

        String hireDateValue = (String) data.get("person.hire_date");
        if (hireDateValue != null) {
            try {
                personEntity.setHireDate(Date.valueOf(hireDateValue));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid date format for hire date.");
            }
        } else {
            throw new IllegalArgumentException("Hire date is required.");
        }

        String terminationDateValue = (String) data.get("person.termination_date");
        if (terminationDateValue != null) {
            try {
                personEntity.setTerminationDate(Date.valueOf(terminationDateValue));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid date format for termination date.");
            }
        }

        return personEntity;
    }
}
