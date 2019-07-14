package com.autowired.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

//    invoiceId int(11) not null auto_increment primary key,
//    customerId int(11) not null,
//    orderDate date not null,
//    pickupDate date not null,
//    returnDate date not null,
//    lateFee decimal(8.2) not null
    private int invoiceId;
    private int customerId;

    // @Past(message = "Date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    // @Past(message = "Date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    // @Past(message = "Date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    private BigDecimal lateFee;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getCustomerId() == invoice.getCustomerId() &&
                getOrderDate().equals(invoice.getOrderDate()) &&
                getPickUpDate().equals(invoice.getPickUpDate()) &&
                getReturnDate().equals(invoice.getReturnDate()) &&
                getLateFee().equals(invoice.getLateFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getCustomerId(), getOrderDate(), getPickUpDate(), getReturnDate(), getLateFee());
    }
}
