package com.autowired.dao;

import com.autowired.model.Customer;

import java.util.List;

public interface CustomerDao {
    /**
     * Add a customer object to the database.
     * @param customer - The customer object sent from the RestController
     * @return customer - The customer object is returned to the RestController
     */
    Customer addCustomer(Customer customer);

    /**
     * Retrieves a customer object from the database using a given customer id
     * @param customerId
     * @return customer - The customer object is returned to the RestController
     */
    Customer getCustomer(int customerId);

    /**
     * Retrieves a list of all customers in the database
     * @return List<Customer>
     */
    List<Customer> getAllCustomers();

    /**
     * Updates a customer from the customer object sent from the RestController
     * @param customer - The customer object sent from the RestController
     */
    void updateCustomer(Customer customer);

    /**
     * Deletes a customer from the database using a customerID from the RestController
     * @param customerId
     */
    void deleteCustomer(int customerId);
}
