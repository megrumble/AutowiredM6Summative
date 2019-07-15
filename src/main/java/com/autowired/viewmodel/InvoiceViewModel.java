package com.autowired.viewmodel;

import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {
    private Customer customer;
    private Invoice invoice;
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getCustomer().equals(that.getCustomer()) &&
                getInvoice().equals(that.getInvoice()) &&
                getInvoiceItemList().equals(that.getInvoiceItemList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getInvoice(), getInvoiceItemList());
    }
}
