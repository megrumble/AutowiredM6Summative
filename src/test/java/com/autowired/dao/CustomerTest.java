package com.autowired.dao;

import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        //clean up db
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        invoiceItemList.stream().forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        List<Item> itemList = itemDao.getAllItems();
        itemList.stream().forEach(item -> itemDao.deleteItem(item.getItemId()));

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));

        List<Customer> customerList = customerDao.getAllCustomers();
        customerList.stream().forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));

    }
    @Test
    public void addGetDeleteCustomer(){

        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customer = customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer1, customer);



        customerDao.deleteCustomer(customer.getCustomerId());

        customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertNull(customer1);
    }

    @Test
    public void updateCustomer(){

        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customer = customerDao.addCustomer(customer);


        customer.setFirstName("Ringo");
        customer.setLastName("Starr");
        customer.setPhone("1234237890");
        customer.setEmail("ringo@gmail.com");
        customer.setCompany("Beatles");

        customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer1, customer);

    }

    @Test
    public void getAllCustomers(){
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customerDao.addCustomer(customer);

        customer = new Customer();
        customer.setFirstName("Ringo");
        customer.setLastName("Starr");
        customer.setPhone("1234237890");
        customer.setEmail("ringo@gmail.com");
        customer.setCompany("Beatles");


        customerDao.addCustomer(customer);

        List<Customer> customerList = customerDao.getAllCustomers();
        assertEquals(customerList.size(), 2);


    }
}
