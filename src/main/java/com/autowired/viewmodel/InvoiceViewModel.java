package com.autowired.viewmodel;

import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;

import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {

    private Invoice invoice;
    private Customer customer;

    //private List<InvoiceItem> invoiceItemList;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public List<InvoiceItem> getInvoiceItemList(List<InvoiceItem> invoiceItemList) {
//        return this.invoiceItemList;
//    }

//    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
//        this.invoiceItemList = invoiceItemList;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getInvoice().equals(that.getInvoice()) &&
                getCustomer().equals(that.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice(), getCustomer());
    }
}
