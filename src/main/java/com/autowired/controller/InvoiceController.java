package com.autowired.controller;

import com.autowired.dao.InvoiceDaoJdbcTemplateImpl;
import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class InvoiceController {

    @Autowired
    private InvoiceDaoJdbcTemplateImpl invoiceDaoJdbcTemplate;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)

    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid Invoice invoice){
        return invoice;
    }
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice updateInvoice(@PathVariable int invoiceId, @RequestBody @Valid Invoice invoice){
        invoice.setInvoiceId(invoiceId);
        return invoice;
    }
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int invoiceId){
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        return invoice;
    }

    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int invoiceId){
        invoiceDaoJdbcTemplate.deleteInvoice(invoiceId);
    }
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices(){
        return invoiceDaoJdbcTemplate.getAllInvoices();
    }
}
