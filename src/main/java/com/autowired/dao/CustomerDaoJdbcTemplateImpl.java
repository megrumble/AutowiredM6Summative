package com.autowired.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    private static final String ADD_CUSTOMER_SQL =
            "insert into customer(customer_id, first_name, last_name, email, company, phone) values (?, ?, ?, ?, ?, ?)";


    /**
     * Add a customer object to the database.
     *
     * @param customer - The customer object sent from the RestController
     * @return customer - The customer object is returned to the RestController
     */
    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    /**
     * Retrieves a customer object from the database using a given customer id
     *
     * @param customerId
     * @return customer - The customer object is returned to the RestController
     */
    @Override
    public Customer getCustomer(int customerId) {
        return null;
    }

    /**
     * Retrieves a list of all customers in the database
     *
     * @return List<Customer>
     */
    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    /**
     * Updates a customer from the customer object sent from the RestController
     *
     * @param customer - The customer object sent from the RestController
     */
    @Override
    public void updateCustomer(Customer customer) {

    }

    /**
     * Deletes a customer from the database using a customerID from the RestController
     *
     * @param customerId
     */
    @Override
    public void deleteCustomer(int customerId) {

    }
}
