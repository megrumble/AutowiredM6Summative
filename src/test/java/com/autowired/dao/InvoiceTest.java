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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceTest {
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
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
        List<Item> itemList = itemDao.getAllItems();
        itemList.stream().forEach(item -> itemDao.deleteItem(item.getItemId()));
        List<Customer> customerList = customerDao.getAllCustomers();
        customerList.stream().forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));

    }

    @Test
    public void addGetDeleteInvoice(){

        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

//        assertEquals(customer1, customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));



        invoiceDao.addInvoice(invoice);
        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);



        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);
    }

    @Test
    public void updateInvoice(){
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customerDao.addCustomer(customer);

//        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

//        assertEquals(customer1, customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        invoiceDao.addInvoice(invoice);

        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);
    }

    @Test
    public void getAllInvoices(){
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customerDao.addCustomer(customer);

//        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

//        assertEquals(customer1, customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        invoiceDao.addInvoice(invoice);

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(customer.getCustomerId());
        invoice1.setLateFee(BigDecimal.valueOf(3.09));
        invoice1.setOrderDate(LocalDate.now());
        invoice1.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice1.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        invoiceDao.addInvoice(invoice1);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();





        assertEquals(2, invoiceList.size());
    }

    @Test
    public void getInvoiceByCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customerDao.addCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setFirstName("Ringo");
        customer1.setLastName("Star");
        customer1.setPhone("1234567890");
        customer1.setEmail("geo@gmail.com");
        customer1.setCompany("Beatles");

        customerDao.addCustomer(customer1);

//        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

//        assertEquals(customer1, customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        invoiceDao.addInvoice(invoice);

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(customer1.getCustomerId());
        invoice1.setLateFee(BigDecimal.valueOf(3.09));
        invoice1.setOrderDate(LocalDate.now());
        invoice1.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice1.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        invoiceDao.addInvoice(invoice1);

        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomer(customer1.getCustomerId());


        assertEquals(invoice1, invoiceList.get(0));

    }
}
