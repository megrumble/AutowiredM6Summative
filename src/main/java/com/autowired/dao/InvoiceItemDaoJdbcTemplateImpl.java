package com.autowired.dao;

import com.autowired.model.InvoiceItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

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
=======
    JdbcTemplate jdbcTemplate;

    private static final String ADD_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id, item_id, quantity, unit_rate, discount) values (?, ?, ?, ?, ?)";
    private static final String SELECT_INVOICE_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";
    private static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "select * from invoice_item";
    private static final String UPDATE_INVOICE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, item_id = ?, quantity = ?, unit_rate = ?, discount = ? where invoice_item_id = ?";
    private static final String DELETE_INVOICE_ITEM_SQL =
            "delete from invoice_item where invoice_item_id = ?";

    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Add a InvoiceItem object to the database.
     * @param invoiceItem - The invoiceItem object sent from the RestController
     * @return InvoiceItem - The InvoiceItem object is returned to the RestController
     */
    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(ADD_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getItemId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitRate(),
                invoiceItem.getDiscount());

        int invoiceItemId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoiceItem.setInvoiceItemId(invoiceItemId);

        return invoiceItem;
    }

    /**
     * Retrieves a InvoiceItem object from the database using a given InvoiceItem id
     * @param invoiceItemId@return InvoiceItem - The InvoiceItem object is returned to the RestController
     */
    @Override
    public InvoiceItem getInvoiceItem(int invoiceItemId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, invoiceItemId);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all InvoiceItems in the database
     * @return List<InvoiceItem>
     */
    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);
    }

    /**
     * Updates an InvoiceItem from the InvoiceItem object sent from the RestController
     * @param invoiceItem
     */
    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getItemId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitRate(),
                invoiceItem.getDiscount(),
                invoiceItem.getInvoiceItemId());
    }

    /**
     * Deletes a InvoiceItem from the database using a InvoiceItemID from the RestController
     * @param invoiceItemId
     */
    @Override
    public void deleteInvoiceItem(int invoiceItemId) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, invoiceItemId);
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();

        invoiceItem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setItemId(rs.getInt("item_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitRate(rs.getBigDecimal("unit_rate"));
        invoiceItem.setDiscount(rs.getBigDecimal("discount"));

        return invoiceItem;
    }
}
