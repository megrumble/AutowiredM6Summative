package com.autowired.controller;

import com.autowired.dao.InvoiceDaoJdbcTemplateImpl;
import com.autowired.dao.InvoiceItemDaoJdbcTemplateImpl;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class InvoiceItemController {

    @Autowired
    private InvoiceItemDaoJdbcTemplateImpl invoiceItemDaoJdbcTemplate;

    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)

    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem addInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem){
        return invoiceItem;
    }
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem updateInvoiceItem(@PathVariable int invoiceItemId, @RequestBody @Valid InvoiceItem invoiceItem){
        invoiceItem.setInvoiceItemId(invoiceItemId);
        return invoiceItem;
    }
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable int invoiceItemId){
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(invoiceItemId);
        return invoiceItem;
    }

    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.DELETE)
    public void deleteInvoiceItem(@PathVariable int invoiceItemId){
        invoiceItemDaoJdbcTemplate.deleteInvoiceItem(invoiceItemId);
    }
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.GET)
    public List<InvoiceItem> getAllInvoiceItems(){
        return invoiceItemDaoJdbcTemplate.getAllInvoiceItems();
    }
}
