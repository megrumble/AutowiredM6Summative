package com.autowired.dao;

import com.autowired.model.InvoiceItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {
    private int invoiceItemId;
    private int invoiceId;
    private int itemId;
    private int quantity;
    private BigDecimal unitRate;
    private BigDecimal discount;
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_INVOICEITEM_SQL =
            "insert into invoice_item (invoiceItemId, invoiceId, itemId, quantity, unitRate, discount) values(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_INVOICEITEM_SQL =
            "select "
    /**
     * Add a InvoiceItem object to the database.
     *
     * @param invoiceItem - The invoiceItem object sent from the RestController
     * @return InvoiceItem - The InvoiceItem object is returned to the RestController
     */
    @Override
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        return null;
    }

    /**
     * Retrieves a InvoiceItem object from the database using a given InvoiceItem id
     *
     * @param invoiceItemId@return InvoiceItem - The InvoiceItem object is returned to the RestController
     */
    @Override
    public InvoiceItem getInvoiceItem(int invoiceItemId) {
        return null;
    }

    /**
     * Retrieves a list of all InvoiceItems in the database
     *
     * @return List<InvoiceItem>
     */
    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return null;
    }

    /**
     * Updates an InvoiceItem from the InvoiceItem object sent from the RestController
     *
     * @param invoiceItem
     */
    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {

    }

    /**
     * Deletes a InvoiceItem from the database using a InvoiceItemID from the RestController
     *
     * @param invoiceItemId
     */
    @Override
    public void deleteInvoiceItem(int invoiceItemId) {

    }
}
