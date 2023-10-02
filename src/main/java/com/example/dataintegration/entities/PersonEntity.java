package com.example.dataintegration.entities;

import com.example.dataintegration.models.Gender;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "person", schema = "employee_data", catalog = "")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Basic
    @Column(name = "birthdate")
    private Date birthdate;
    @Basic
    @Column(name = "employee_code")
    private String employeeCode;
    @Basic
    @Column(name = "hire_date")
    private Date hireDate;
    @Basic
    @Column(name = "termination_date")
    private Date terminationDate;
    @OneToMany(mappedBy = "personByPersonId")
    private Collection<SalaryComponentEntity> salaryComponentsById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = (Gender) gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (id != that.id) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (employeeCode != null ? !employeeCode.equals(that.employeeCode) : that.employeeCode != null) return false;
        if (hireDate != null ? !hireDate.equals(that.hireDate) : that.hireDate != null) return false;
        if (terminationDate != null ? !terminationDate.equals(that.terminationDate) : that.terminationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (terminationDate != null ? terminationDate.hashCode() : 0);
        return result;
    }

    public Collection<SalaryComponentEntity> getSalaryComponentsById() {
        return salaryComponentsById;
    }

    public void setSalaryComponentsById(Collection<SalaryComponentEntity> salaryComponentsById) {
        this.salaryComponentsById = salaryComponentsById;
    }
}
