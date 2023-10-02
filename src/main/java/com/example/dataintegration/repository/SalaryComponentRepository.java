package com.example.dataintegration.repository;

import com.example.dataintegration.entities.SalaryComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryComponentRepository extends JpaRepository<SalaryComponentEntity, Long> {
}
