package com.autowired.dao;

import com.autowired.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    private static final String ADD_CUSTOMER_SQL =
            "insert into customer(first_name, last_name, email, company, phone) values (?, ?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_SQL =
            "select * from customer where customer_id = ?";
    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "select * from customers";
    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ?, email = ?, company, phone = ? where customer_id = ?";
    private static final String DELET_CUSTOMER_SQL =
            "delete from customer where customer_id = ?";

    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add a customer object to the database.
     *
     * @param customer - The customer object sent from the RestController
     * @return customer - The customer object is returned to the RestController
     */
    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(ADD_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getPhone());
        int customerId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        customer.setCustomerId(customerId);

        return  customer;
    }

    /**
     * Retrieves a customer object from the database using a given customer id
     *
     * @param customerId
     * @return customer - The customer object is returned to the RestController
     */
    @Override
    public Customer getCustomer(int customerId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, customerId);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all customers in the database
     *
     * @return List<Customer>
     */
    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToCustomer);
    }

    /**
     * Updates a customer from the customer object sent from the RestController
     *
     * @param customer - The customer object sent from the RestController
     */
    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getPhone());
    }

    /**
     * Deletes a customer from the database using a customerID from the RestController
     *
     * @param customerId
     */
    @Override
    @Transactional
    public void deleteCustomer(int customerId) {
        jdbcTemplate.update(DELET_CUSTOMER_SQL, customerId);
    }

    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setCompany(rs.getString("company"));
        customer.setPhone(rs.getString("phone"));

         return customer;
    }
}
