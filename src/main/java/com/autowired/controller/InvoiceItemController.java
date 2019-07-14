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

    /**
     * Invokes the addInvoiceItem method of the invoiceItemDaoJdbcTemplate with the InvoiceItem object
     * deserialized from the RequestBody JSON by the Jackson Mapper class     *
     * @param invoiceItem
     * @return InvoiceItem object JSON
     */
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem addInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem){
        return invoiceItemDaoJdbcTemplate.addInvoiceItem(invoiceItem);
    }

    /**
     * Invokes the updateInvoiceITem method of the invoiceItemDaoJdbcTemplate with the InvoiceItem object
     * deserialized from the RequestBody JSON by the Jackson Mapper class  and the invoiceItemId supplied
     * by the client.
     * @param invoiceItemId
     */
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoiceItem(@PathVariable int invoiceItemId, @RequestBody @Valid InvoiceItem invoiceItem){
        invoiceItem.setInvoiceItemId(invoiceItemId);
        invoiceItemDaoJdbcTemplate.updateInvoiceItem(invoiceItem);
    }

    /**
     * Invokes the getInvoiceITem method of the invoiceItemDaoJdbcTemplate with the invoiceItemId supplied
     * by the client.
     * @param invoiceItemId
     * @return InvoiceItem object JSON
     */
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable int invoiceItemId){
        return invoiceItemDaoJdbcTemplate.getInvoiceItem(invoiceItemId);
    }

    /**
     * Invokes the deleteInvoiceItem method of the invoiceItemDaoJdbcTemplate with the invoiceItemId supplied
     * by the client.
     * @param invoiceItemId
     */
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable int invoiceItemId){
        invoiceItemDaoJdbcTemplate.deleteInvoiceItem(invoiceItemId);
    }

    /**
     * Invokes the getAllInvoiceItems method of the invoiceItemDaoJdbcTemplate
     * @return List of InvoiceItem objects JSON
     */
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems(){
        return invoiceItemDaoJdbcTemplate.getAllInvoiceItems();
    }
}
