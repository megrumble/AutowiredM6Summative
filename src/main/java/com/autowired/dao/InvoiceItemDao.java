package com.autowired.dao;

import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;

import java.util.List;

public interface InvoiceItemDao extends ItemDao {
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
     * @param invoiceItem - The InvoiceItem object sent from the RestController
     */
    void updateInvoiceItem(InvoiceItem invoiceItem);

    /**
     * Deletes a InvoiceItem from the database using a InvoiceItemID from the RestController
     * @param invoiceItemId
     */
    void deleteInvoiceItem(int invoiceItemId);

    /**
     * Add a Item object to the database.
     *
     * @param item - The Item object sent from the RestController
     * @return Item - The Item object is returned to the RestController
     */
    @Override
    Item addItem(Item item);

    /**
     * Retrieves a Item object from the database using a given Item id
     *
     * @param itemId
     * @return Item - The Item object is returned to the RestController
     */
    @Override
    Item getItem(int itemId);

    /**
     * Retrieves a list of all Items in the database
     *
     * @return List<Item>
     */
    @Override
    List<Item> getAllItems();

    /**
     * Updates an Item from the Item object sent from the RestController
     *
     * @param item - The Item object sent from the RestController
     */
    @Override
    void updateItem(Item item);

    /**
     * Deletes a Item from the database using a ItemID from the RestController
     *
     * @param itemId
     */
    @Override
    void deleteItem(int itemId);
}
