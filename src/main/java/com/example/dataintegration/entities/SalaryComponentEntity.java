package com.example.dataintegration.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "salary_component", schema = "employee_data", catalog = "")
public class SalaryComponentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "person_id", insertable = false, updatable = false)
    private long personId;
    @Basic
    @Column(name = "amount")
    private int amount;
    @Basic
    @Column(name = "currency")
    private String currency;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private PersonEntity personByPersonId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryComponentEntity that = (SalaryComponentEntity) o;

        if (id != that.id) return false;
        if (personId != that.personId) return false;
        if (amount != that.amount) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (personId ^ (personId >>> 32));
        result = 31 * result + amount;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    public PersonEntity getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(PersonEntity personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
