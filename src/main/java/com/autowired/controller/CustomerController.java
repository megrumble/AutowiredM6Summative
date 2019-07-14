package com.autowired.controller;

import com.autowired.dao.CustomerDaoJdbcTemplateImpl;
import com.autowired.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerDaoJdbcTemplateImpl customerDaoJdbcTemplate;

    /**
     * Invokes the addCustomer method of the customerDaoJdbcTemplate with the customer object
     * deserialized from the RequestBody JSON by the Jackson Mapper class
     * @param customer
     * @return Customer object JSON
     */
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
         return customerDaoJdbcTemplate.addCustomer(customer);
    }

    /**
     * Invokes the updateCustomer method of the customerDaoJdbcTemplate with the customer object
     * deserialized from the RequestBody JSON by the Jackson Mapper class  and the customer Id supplied
     * by the client.
     * @param customerId
     */
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@PathVariable int customerId, @RequestBody @Valid Customer customer) {
        customer.setCustomerId(customerId);
        customerDaoJdbcTemplate.updateCustomer(customer);
    }

    /**
     * Invokes the getCustomer method of the customerDaoJdbcTemplate with the customerId supplied
     * by the client.
     * @param customerId
     * @return Customer object JSON
     */
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer(@PathVariable int customerId) {
        return customerDaoJdbcTemplate.getCustomer(customerId);
    }

    /**
     * Invokes the deleteCustomer method of the customerDaoJdbcTemplate with the customerId supplied
     * by the client.
     * @param customerId
     */
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int customerId) {
        customerDaoJdbcTemplate.deleteCustomer(customerId);
    }

    /**
     * Invokes the getAllCustomers method of the customerDaoJbbcTemplate
     * @return List of Customer objects JSON
     */
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerDaoJdbcTemplate.getAllCustomers();
    }
}
