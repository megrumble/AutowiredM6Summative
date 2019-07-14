package com.autowired.dao;

import com.autowired.model.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    JdbcTemplate jdbcTemplate;

    private static final String ADD_INVOICE_SQL =
            "insert into invoice (customer_id, order_date, pickup_date, return_date, late_fee) values (?, ?, ?, ?, ?)";
    private static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";
    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";
    private static final String SELECT_INVOICES_BY_CUSTOMER_SQL =
            "select * from invoice where customer_id = ?";
    private static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? where invoice_id = ?";
    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add an Invoice object to the database.
     * @param invoice - The Invoice object sent from the RestController
     * @return Invoice - The Invoice object is returned to the RestController
     */
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(ADD_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getOrderDate(),
                invoice.getPickUpDate(),
                invoice.getReturnDate(),
                invoice.getLateFee());

        int invoiceId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(invoiceId);

        return invoice;
    }

    /**
     * Retrieves an Invoice object from the database using a given Invoice id
     * @param invoiceId
     * @return Invoice - The Invoice object is returned to the RestController
     */
    @Override
    public Invoice getInvoice(int invoiceId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoiceId);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all Invoices in the database
     * @return List<Invoice>
     */
    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    /**
     * Retrieves a list of all Invoices By Customer in the database
     * @param customerId
     * @return List<Invoice>
     */
    @Override
    public List<Invoice> getInvoicesByCustomer(int customerId) {
        return jdbcTemplate.query(SELECT_INVOICES_BY_CUSTOMER_SQL, this::mapRowToInvoice, customerId);
    }

    /**
     * Updates an Invoice from the Invoice object sent from the RestController
     * @param invoice - The Invoice object sent from the RestController
     */
    @Override
    @Transactional
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getOrderDate(),
                invoice.getPickUpDate(),
                invoice.getReturnDate(),
                invoice.getLateFee(),
                invoice.getInvoiceId());
    }

    /**
     * Deletes a Invoice from the database using a InvoiceID from the RestController
     * @param invoiceId
     */
    @Override
    @Transactional
    public void deleteInvoice(int invoiceId) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
        invoice.setPickUpDate(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturnDate(rs.getDate("return_date").toLocalDate());
        invoice.setLateFee(rs.getBigDecimal("late_fee"));

        return invoice;
    }
}
