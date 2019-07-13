package com.autowired.dao;

import com.autowired.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    /**
     * Add a InvoiceItem object to the database.
     * @param invoiceItem - The invoiceItem object sent from the RestController
     * @return InvoiceItem - The InvoiceItem object is returned to the RestController
     */
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    /**
     * Retrieves a InvoiceItem object from the database using a given InvoiceItem id
     * @param invoiceItemId
     * @return InvoiceItem - The InvoiceItem object is returned to the RestController
     */
    InvoiceItem getInvoiceItem(int invoiceItemId);

    /**
     * Retrieves a list of all InvoiceItems in the database
     * @return List<InvoiceItem>
     */
    List<InvoiceItem> getAllInvoiceItems();

    /**
     * Updates an InvoiceItem from the InvoiceItem object sent from the RestController
     * @param InvoiceItem - The InvoiceItem object sent from the RestController
     */
    void updateInvoiceItem(InvoiceItem invoiceItem);

    /**
     * Deletes a InvoiceItem from the database using a InvoiceItemID from the RestController
     * @param InvoiceItemId
     */
    void deleteInvoiceItem(int invoiceItemId);
}
