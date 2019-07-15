package com.autowired.dao;

import com.autowired.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    /**
     * Add an Invoice object to the database.
     * @param invoice - The Invoice object sent from the RestController
     * @return Invoice - The Invoice object is returned to the RestController
     */
    Invoice addInvoice(Invoice invoice);

    /**
     * Retrieves an Invoice object from the database using a given Invoice id
     * @param invoiceId
     * @return Invoice - The Invoice object is returned to the RestController
     */
    Invoice getInvoice(int invoiceId);

    /**
     * Retrieves a list of all Invoices in the database
     * @return List<Invoice>
     */
    List<Invoice> getAllInvoices();

    /**
     * Updates an Invoice from the Invoice object sent from the RestController
     * @param invoice - The Invoice object sent from the RestController
     */
    void updateInvoice(Invoice invoice);

    /**
     * Deletes a Invoice from the database using a InvoiceID from the RestController
     * @param invoiceId
     */
    void deleteInvoice(int invoiceId);

}
