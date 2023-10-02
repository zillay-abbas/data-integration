package com.example.dataintegration.services;

import com.example.dataintegration.entities.PersonEntity;
import com.example.dataintegration.entities.SalaryComponentEntity;
import com.example.dataintegration.models.Gender;
import com.example.dataintegration.models.JsonObject;
import com.example.dataintegration.models.JsonPayComponent;
import com.example.dataintegration.repository.PersonRepository;
import com.example.dataintegration.repository.SalaryComponentRepository;
import com.example.dataintegration.util.DateUtils;
import com.example.dataintegration.util.PersonEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DataIntegrationServiceImpl implements DataIntegrationService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    SalaryComponentRepository salaryComponentRepository;

    @Override
    public String processDataAdd(JsonObject jsonObject) {
        Map<String, Object> data = jsonObject.getData();
        PersonEntity personEntity = new PersonEntity();

        personEntity = PersonEntityMapper.mapToPersonEntity(data);

        personRepository.save(personEntity);

        List<JsonPayComponent> payComponentsData = jsonObject.getPayComponents();

        for (JsonPayComponent payComponentData : payComponentsData) {
            SalaryComponentEntity salaryComponentEntity = new SalaryComponentEntity();

            salaryComponentEntity.setAmount((int) payComponentData.getAmount().intValue());
            salaryComponentEntity.setCurrency((String) payComponentData.getCurrency());
            salaryComponentEntity.setStartDate(Date.valueOf((String) payComponentData.getStartDate()));
            salaryComponentEntity.setEndDate(Date.valueOf((String) payComponentData.getEndDate()));
            salaryComponentEntity.setPersonByPersonId(personEntity);


            salaryComponentRepository.save(salaryComponentEntity);
        }

        return "Records Processed";
    }

    @Override
    public String processDataUpdate(JsonObject jsonObject) {
        Map<String, Object> data = jsonObject.getData();

        Optional<PersonEntity> personOptional = personRepository.findByEmployeeCode(jsonObject.getEmployeeCode());
        if (personOptional.isPresent()) {
            PersonEntity existingPerson = personOptional.get();
            PersonEntity personEntity = PersonEntityMapper.mapToPersonEntity(data);

            if (personEntity.getFullName() != null && personEntity.getFullName().isEmpty()) {
                existingPerson.setFullName(personEntity.getFullName());
            }

            if (personEntity.getGender() != null) {
                existingPerson.setGender(personEntity.getGender());
            }

            if (personEntity.getBirthdate() != null) {
                existingPerson.setBirthdate(personEntity.getBirthdate());
            }

            if (personEntity.getEmployeeCode() != null && !personEntity.getEmployeeCode().isEmpty()) {
                existingPerson.setEmployeeCode(personEntity.getEmployeeCode());
            }

            if (personEntity.getHireDate() != null) {
                existingPerson.setHireDate(personEntity.getHireDate());
            }

            if (jsonObject.getPayComponents() != null && !jsonObject.getPayComponents().isEmpty()) {
                for (JsonPayComponent jsonPayComponent : jsonObject.getPayComponents()) {

                    SalaryComponentEntity newSalaryComponent = new SalaryComponentEntity();
                    newSalaryComponent.setAmount(jsonPayComponent.getAmount().intValue());
                    newSalaryComponent.setCurrency(jsonPayComponent.getCurrency());
                    newSalaryComponent.setStartDate(DateUtils.parseDate(jsonPayComponent.getStartDate()));
                    newSalaryComponent.setEndDate(DateUtils.parseDate(jsonPayComponent.getEndDate()));
                    newSalaryComponent.setPersonByPersonId(existingPerson);

                    existingPerson.getSalaryComponentsById().add(newSalaryComponent);
                }
            }

            personRepository.save(existingPerson);
        } else {
            return "";
        }
        return "Records Processed";
    }

    @Override
    public String processDataDelete(JsonObject jsonObject) {
        Optional<PersonEntity> personOptional = personRepository.findByEmployeeCode(jsonObject.getEmployeeCode());

        if (personOptional.isPresent()) {
            PersonEntity existingPerson = personOptional.get();
            if (!existingPerson.getSalaryComponentsById().isEmpty()) {
               salaryComponentRepository.deleteAll(existingPerson.getSalaryComponentsById());
            }

            personRepository.delete(existingPerson);
        } else {
            return "Employee with code " + jsonObject.getEmployeeCode() + " not found.";
        }

        return "Records Processed";
    }
}
