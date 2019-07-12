package com.autowired.model;

import java.util.Objects;

public class Customer {
//    customerId int(11) not null auto_increment primary key,
//    firstName varchar(50) not null,
//    lastName varchar(50) not null,
//    email varchar(75) not null,
//    company varchar(50) not null,
//    phone varchar(50) not null

    private int customerId;
    private String fistName;
    private String lastName;
    private String email;
    private String company;
    private String phone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCustomerId() == customer.getCustomerId() &&
                getFistName().equals(customer.getFistName()) &&
                getLastName().equals(customer.getLastName()) &&
                getEmail().equals(customer.getEmail()) &&
                getCompany().equals(customer.getCompany()) &&
                getPhone().equals(customer.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFistName(), getLastName(), getEmail(), getCompany(), getPhone());
    }

}
