package com.autowired.dao;

import com.autowired.model.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    JdbcTemplate jdbcTemplate;
    /**
     * Add an Invoice object to the database.
     *
     * @param invoice - The Invoice object sent from the RestController
     * @return Invoice - The Invoice object is returned to the RestController
     */
    @Override
    public Invoice addInvoice(Invoice invoice) {
        return null;
    }

    /**
     * Retrieves an Invoice object from the database using a given Invoice id
     *
     * @param invoiceId
     * @return Invoice - The Invoice object is returned to the RestController
     */
    @Override
    public Invoice getInvoice(int invoiceId) {
        return null;
    }

    /**
     * Retrieves a list of all Invoices in the database
     *
     * @return List<Invoice>
     */
    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    /**
     * Updates an Invoice from the Invoice object sent from the RestController
     *
     * @param invoice - The Invoice object sent from the RestController
     */
    @Override
    public void updateInvoice(Invoice invoice) {

    }

    /**
     * Deletes a Invoice from the database using a InvoiceID from the RestController
     *
     * @param invoiceId
     */
    @Override
    public void deleteInvoice(int invoiceId) {

    }
}
