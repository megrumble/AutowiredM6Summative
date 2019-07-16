package com.autowired.service;

import com.autowired.dao.*;
import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;
import com.autowired.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    ItemDao itemDao;
    InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpItemDaoMock();
        setUpInvoiceItemDaoMock();

        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);

    }

    @Test
    public void saveFindInvoice(){

        InvoiceViewModel ivm = new InvoiceViewModel();

        Customer customer = new Customer();
        customer.setFirstName("Ringo");
        customer.setLastName("Starr");
        customer.setCompany("Beatles");
        customer.setEmail("Ringo@gmail.com");
        customer.setPhone("7045748392");

        ivm.setCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        ivm.setInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(1);
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitRate(new BigDecimal("2.50"));
        invoiceItem.setDiscount(new BigDecimal(".50"));
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);




        service.saveInvoice(ivm);

        InvoiceViewModel fromService = service.findInvoice(ivm.getInvoice().getInvoiceId());

        assertEquals(fromService, invoice);

    }

    @Test
    public void findAllInvoices(){
        InvoiceViewModel ivm = new InvoiceViewModel();

        Customer customer = new Customer();
        customer.setFirstName("Ringo");
        customer.setLastName("Starr");
        customer.setCompany("Beatles");
        customer.setEmail("Ringo@gmail.com");
        customer.setPhone("7045748392");

        ivm.setCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        ivm.setInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(1);
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitRate(new BigDecimal("2.50"));
        invoiceItem.setDiscount(new BigDecimal(".50"));
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        ivm.setInvoiceItemList(invoiceItemList);

        ivm = service.saveInvoice(ivm);

        List<InvoiceViewModel> fromService = service.findAllInvoices();

        assertEquals(fromService.size(), 1);

    }

    @Test
    public void removeInvoice(){
        InvoiceViewModel ivm = new InvoiceViewModel();

        Customer customer = new Customer();
        customer.setFirstName("Ringo");
        customer.setLastName("Starr");
        customer.setCompany("Beatles");
        customer.setEmail("Ringo@gmail.com");
        customer.setPhone("7045748392");

        ivm.setCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        ivm.setInvoice(invoice);

       Invoice invoice1 = service.findInvoice(ivm.getInvoice().getInvoiceId());


    }



    private void setUpCustomerDaoMock() {
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Ringo");
        customer.setLastName("Starr");
        customer.setCompany("Beatles");
        customer.setEmail("Ringo@gmail.com");
        customer.setPhone("7045748392");

        Customer customer1 = new Customer();
        customer1.setFirstName("Ringo");
        customer1.setLastName("Starr");
        customer1.setCompany("Beatles");
        customer1.setEmail("Ringo@gmail.com");
        customer1.setPhone("7045748392");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).addCustomer(customer1);
        doReturn(customer).when(customerDao).getCustomer(1);
        doReturn(customerList).when(customerDao).getAllCustomers();

    }

    private CustomerDao mock(Class<CustomerDaoJdbcTemplateImpl> customerDaoJdbcTemplateClass) {
    }

    private void setUpItemDaoMock(){
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item();
        item.setItemId(1);
        item.setName("Item Name");
        item.setDescription("Item Description");
        item.setDailyRate(new BigDecimal("25.99"));
        item = itemDao.addItem(item);

        Item item1 = new Item();
        item1.setName("Item Name");
        item1.setDescription("Item Description");
        item1.setDailyRate(new BigDecimal("25.99"));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        doReturn(item).when(itemDao).addItem(item1);
        doReturn(item).when(itemDao).getItem(1);
        doReturn(itemList).when(itemDao).getAllItems();
    }

    private void setUpInvoiceDaoMock(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setLateFee(BigDecimal.valueOf(3.09));
        invoice.setOrderDate(LocalDate.now());
        invoice.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(1);
        invoice1.setLateFee(BigDecimal.valueOf(3.09));
        invoice1.setOrderDate(LocalDate.now());
        invoice1.setPickUpDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice1.setReturnDate(LocalDate.now().plus(10,ChronoUnit.DAYS));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }

    public void setUpInvoiceItemDaoMock(){
        invoiceItemDao = (InvoiceItemDao) mock(InvoiceItemDaoJdbcTemplateImpl.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setItemId(1);
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitRate(new BigDecimal("2.50"));
        invoiceItem.setDiscount(new BigDecimal(".50"));

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setItemId(1);
        invoiceItem1.setQuantity(2);
        invoiceItem1.setUnitRate(new BigDecimal("2.50"));
        invoiceItem1.setDiscount(new BigDecimal(".50"));

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItems();


    }

}
