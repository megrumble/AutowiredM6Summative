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

    /**
     * Invokes the addInvoice method of the invoiceDaoJdbcTemplate with the invoice object
     * deserialized from the RequestBody JSON by the Jackson Mapper class
     * @param invoice
     * @return Invoice object serialized to JSON
     */
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid Invoice invoice) {
        return invoiceDaoJdbcTemplate.addInvoice(invoice);
    }

    /**
     * Invokes the updateInvoice method of the invoiceDaoJdbcTemplate with the invoice object
     * deserialized from the RequestBody JSON by the Jackson Mapper class  and the invoice Id supplied
     * by the client.
     * @param invoiceId
     */
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@PathVariable int invoiceId, @RequestBody @Valid Invoice invoice) {
        invoice.setInvoiceId(invoiceId);
        invoiceDaoJdbcTemplate.updateInvoice(invoice);
    }

    /**
     * Invokes the getInvoice method of the invoiceDaoJdbcTemplate with the invoiceId supplied
     * by the client.
     * @param invoiceId
     * @return Invoice object serialized to JSON
     */
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int invoiceId) {
        return invoiceDaoJdbcTemplate.getInvoice(invoiceId);
    }

    /**
     * Invokes the deleteInvoice method of the invoiceDaoJdbcTemplate with the invoiceId supplied
     * by the client.
     * @param invoiceId
     */
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable int invoiceId) {
        invoiceDaoJdbcTemplate.deleteInvoice(invoiceId);
    }

    /**
     * Invokes the getAllInvoices method of the invoiceDaoJbbcTemplate
     * @return A List of Invoice objects serialized to JSON
     */
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceDaoJdbcTemplate.getAllInvoices();
    }

    /**
     * Invokes the getInvoicesByCustomer method of the invoiceDaoJbbcTemplate
     * @return A List of Invoice objects serialized to JSON
     */
    @RequestMapping(value = "/invoice/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomer(@PathVariable int customerId) {
        return invoiceDaoJdbcTemplate.getInvoicesByCustomer(customerId);
    }
}
