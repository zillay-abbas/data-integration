package com.example.dataintegration.models;

import com.opencsv.bean.CsvBindByName;

public class CsvData {
        @CsvBindByName(column = "ACTION")
        private String actionCode;

        @CsvBindByName(column = "contract_workerId")
        private String workerId;

        @CsvBindByName(column = "worker_name")
        private String fullName;

        @CsvBindByName(column = "worker_personalCode")
        private String personalCode;

        @CsvBindByName(column = "worker_gender")
        private String gender;

        @CsvBindByName(column = "contract_workStartDate")
        private String hireDate;

        @CsvBindByName(column = "contract_endDate")
        private String terminationDate;
        @CsvBindByName(column = "pay_amount")
        private String payAmount;
        @CsvBindByName(column = "pay_currency")
        private String payCurrency;

        @CsvBindByName(column = "pay_effectiveFrom")
        private String startDate;
        @CsvBindByName(column = "pay_effectiveTo")
        private String endDate;
        @CsvBindByName(column = "compensation_amount")
        private String compensationAmount;
        @CsvBindByName(column = "compensation_currency")
        private String compensationCurrency;

        @CsvBindByName(column = "compensation_effectiveFrom")
        private String compensationStartDate;

        @Override
        public String toString() {
                return "CsvData{" +
                        "actionCode='" + actionCode + '\'' +
                        ", workerId='" + workerId + '\'' +
                        ", fullName='" + fullName + '\'' +
                        ", personalCode='" + personalCode + '\'' +
                        ", gender='" + gender + '\'' +
                        ", hireDate='" + hireDate + '\'' +
                        ", terminationDate='" + terminationDate + '\'' +
                        ", payAmount='" + payAmount + '\'' +
                        ", payCurrency='" + payCurrency + '\'' +
                        ", startDate='" + startDate + '\'' +
                        ", endDate='" + endDate + '\'' +
                        ", compensationAmount='" + compensationAmount + '\'' +
                        ", compensationCurrency='" + compensationCurrency + '\'' +
                        ", compensationStartDate='" + compensationStartDate + '\'' +
                        ", compensationEndDate='" + compensationEndDate + '\'' +
                        '}';
        }

        public String getActionCode() {
                return actionCode;
        }

        public void setActionCode(String actionCode) {
                this.actionCode = actionCode;
        }

        public String getWorkerId() {
                return workerId;
        }

        public void setWorkerId(String workerId) {
                this.workerId = workerId;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public String getPersonalCode() {
                return personalCode;
        }

        public void setPersonalCode(String personalCode) {
                this.personalCode = personalCode;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public String getHireDate() {
                return hireDate;
        }

        public void setHireDate(String hireDate) {
                this.hireDate = hireDate;
        }

        public String getTerminationDate() {
                return terminationDate;
        }

        public void setTerminationDate(String terminationDate) {
                this.terminationDate = terminationDate;
        }

        public String getPayAmount() {
                return payAmount;
        }

        public void setPayAmount(String payAmount) {
                this.payAmount = payAmount;
        }

        public String getPayCurrency() {
                return payCurrency;
        }

        public void setPayCurrency(String payCurrency) {
                this.payCurrency = payCurrency;
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

        public String getCompensationAmount() {
                return compensationAmount;
        }

        public void setCompensationAmount(String compensationAmount) {
                this.compensationAmount = compensationAmount;
        }

        public String getCompensationCurrency() {
                return compensationCurrency;
        }

        public void setCompensationCurrency(String compensationCurrency) {
                this.compensationCurrency = compensationCurrency;
        }

        public String getCompensationStartDate() {
                return compensationStartDate;
        }

        public void setCompensationStartDate(String compensationStartDate) {
                this.compensationStartDate = compensationStartDate;
        }

        public String getCompensationEndDate() {
                return compensationEndDate;
        }

        public void setCompensationEndDate(String compensationEndDate) {
                this.compensationEndDate = compensationEndDate;
        }

        @CsvBindByName(column = "compensation_effectiveTo")
        private String compensationEndDate;
}
