package com.autowired.viewmodel;

import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {
<<<<<<< HEAD
    private Customer customer;
    private Invoice invoice;
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();
=======

    private int invoiceId;
    private int customerId;
    private Customer customer;
    private int quantity;
    private Item item;
    private BigDecimal unitRate;
    private BigDecimal discount;
    private List<InvoiceItem> invoiceI = new ArrayList<>();

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }
>>>>>>> 7db706554d1dc950ae1b5afdca0cd98642577b9c


    public Customer getCustomer() {
        return customer;
<<<<<<< HEAD
=======
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
>>>>>>> 7db706554d1dc950ae1b5afdca0cd98642577b9c
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

<<<<<<< HEAD
    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
=======
    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public List<InvoiceItem> getInvoiceI() {
        return invoiceI;
    }

    public void setInvoiceI(List<InvoiceItem> invoiceI) {
        this.invoiceI = invoiceI;
>>>>>>> 7db706554d1dc950ae1b5afdca0cd98642577b9c
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
<<<<<<< HEAD
        return getCustomer().equals(that.getCustomer()) &&
                getInvoice().equals(that.getInvoice()) &&
                getInvoiceItemList().equals(that.getInvoiceItemList());
=======
        return getInvoiceId() == that.getInvoiceId() &&
                getCustomerId() == that.getCustomerId() &&
                getQuantity() == that.getQuantity() &&
                getCustomer().equals(that.getCustomer()) &&
                getItem().equals(that.getItem()) &&
                getUnitRate().equals(that.getUnitRate()) &&
                getDiscount().equals(that.getDiscount()) &&
                getInvoiceI().equals(that.getInvoiceI());
>>>>>>> 7db706554d1dc950ae1b5afdca0cd98642577b9c
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(getCustomer(), getInvoice(), getInvoiceItemList());
=======
        return Objects.hash(getInvoiceId(), getCustomerId(), getCustomer(), getQuantity(), getItem(), getUnitRate(), getDiscount(), getInvoiceI());
>>>>>>> 7db706554d1dc950ae1b5afdca0cd98642577b9c
    }
}
