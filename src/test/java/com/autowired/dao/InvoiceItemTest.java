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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemTest {

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
    public void addGetDeleteInvoiceItem(){

        //first add customer, item, and invoice

        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 5, 3));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 1));
        invoice.setReturnDate(LocalDate.of(2019, 8, 6));
        invoice.setLateFee(new BigDecimal("12.00"));
        invoice = invoiceDao.addInvoice(invoice);

        Item item = new Item();
        item.setName("Yesterday");
        item.setDescription("dvd");
        item. setDailyRate(new BigDecimal("2.50"));
<<<<<<< HEAD
        item =itemDao.addItem(item);
=======
        item = itemDao.addItem(item);
>>>>>>> 4d440656f7d389efb16cd5516e2feced88b69cc1

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitRate(new BigDecimal("2.50"));
        invoiceItem.setDiscount(new BigDecimal(".50"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem1, invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertNull(invoiceItem1);

    }

    @Test
    public void updateInvoiceItem() {


        //first add customer, item, and invoice

        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Harrison");
        customer.setPhone("1234567890");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("Beatles");

        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 5, 3));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 1));
        invoice.setReturnDate(LocalDate.of(2019, 8, 6));
        invoice.setLateFee(new BigDecimal("12.00"));
        invoice = invoiceDao.addInvoice(invoice);

        Item item = new Item();
        item.setName("Yesterday");
        item.setDescription("dvd");
        item.setDailyRate(new BigDecimal("2.50"));
        item = itemDao.addItem(item);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitRate(new BigDecimal("2.50"));
        invoiceItem.setDiscount(new BigDecimal(".50"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem.setDiscount(new BigDecimal(".75"));
        invoiceItem.setQuantity(4);
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem1, invoiceItem);

    }

        @Test
        public void getAllInvoiceItems(){


            //first add customer, item, and invoice

            Customer customer = new Customer();
            customer.setFirstName("George");
            customer.setLastName("Harrison");
            customer.setPhone("1234567890");
            customer.setEmail("geo@gmail.com");
            customer.setCompany("Beatles");

            customerDao.addCustomer(customer);

            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getCustomerId());
            invoice.setOrderDate(LocalDate.of(2019, 5, 3));
            invoice.setPickUpDate(LocalDate.of(2019, 6, 1));
            invoice.setReturnDate(LocalDate.of(2019, 8, 6));
            invoice.setLateFee(new BigDecimal("12.00"));
            invoiceDao.addInvoice(invoice);

            Item item = new Item();
            item.setName("Yesterday");
            item.setDescription("dvd");
            item. setDailyRate(new BigDecimal("2.50"));
            itemDao.addItem(item);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoice.getInvoiceId());
            invoiceItem.setItemId(item.getItemId());
            invoiceItem.setQuantity(2);
            invoiceItem.setUnitRate(new BigDecimal("2.50"));
            invoiceItem.setDiscount(new BigDecimal(".50"));
            invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

            InvoiceItem invoiceItem1 = new InvoiceItem();
            invoiceItem1.setInvoiceId(invoice.getInvoiceId());
            invoiceItem1.setItemId(item.getItemId());
            invoiceItem1.setQuantity(3);
            invoiceItem1.setUnitRate(new BigDecimal("1.50"));
            invoiceItem1.setDiscount(new BigDecimal(".75"));
            invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem);

            List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();

            assertEquals(invoiceItemList.size(), 2);


        }


}
