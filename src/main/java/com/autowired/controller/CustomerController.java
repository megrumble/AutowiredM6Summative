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

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        return customer;
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer updateCustomer(@PathVariable int customerId, @RequestBody @Valid Customer customer) {
        customer.setCustomerId(customerId);
        customerDaoJdbcTemplate.updateCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        return customer;
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int customerId) {
        customerDaoJdbcTemplate.deleteCustomer(customerId);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerDaoJdbcTemplate.getAllCustomers();
    }
}
