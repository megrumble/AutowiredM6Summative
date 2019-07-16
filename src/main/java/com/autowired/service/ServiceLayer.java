package com.autowired.service;

import com.autowired.dao.CustomerDao;
import com.autowired.dao.InvoiceDao;
import com.autowired.dao.InvoiceItemDao;
import com.autowired.dao.ItemDao;
import com.autowired.model.Customer;
import com.autowired.model.Invoice;
import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;
import com.autowired.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;

@Component
public class ServiceLayer {

    private InvoiceDao invoiceDao;
    private CustomerDao customerDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, CustomerDao customerDao, InvoiceItemDao invoiceItemDao, ItemDao itemDao) {
        this.invoiceDao = invoiceDao;
        this.customerDao = customerDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel) {

        // Create customer
        Customer customer = new Customer();
        customer.setFirstName("fName");
        customer.setLastName("lName");
        customer.setCompany("Company1");
        customer.setEmail("email@gmail.com");
        customer.setPhone("704-777-7777");
        customer = customerDao.addCustomer(customer);

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setOrderDate(LocalDate.now());
        invoice.setReturnDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setPickUpDate(LocalDate.now());
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setLateFee(BigDecimal.valueOf(95.99));
        invoice = invoiceDao.addInvoice(invoice);

        // Create two items
        Item item1 = new Item();
        item1.setName("itemName 1");
        item1.setDescription("itemDescription 1");
        item1.setDailyRate(BigDecimal.valueOf(5.99));
        item1 = itemDao.addItem(item1);

        Item item2 = new Item();
        item2.setName("itemName 2");
        item2.setDescription("itemDescription 2");
        item2.setDailyRate(BigDecimal.valueOf(7.99));
        item2 = itemDao.addItem(item2);

        // Create two invoice items
        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(invoice.getInvoiceId());
        invoiceItem1.setItemId(item1.getItemId());
        invoiceItem1.setDiscount(BigDecimal.valueOf(1.99));
        invoiceItem1.setUnitRate(BigDecimal.valueOf(6.99));
        invoiceItem1.setQuantity(10);
        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(invoice.getInvoiceId());
        invoiceItem2.setItemId(item2.getItemId());
        invoiceItem2.setDiscount(BigDecimal.valueOf(3.99));
        invoiceItem2.setUnitRate(BigDecimal.valueOf(10.99));
        invoiceItem2.setQuantity(50);
        invoiceItem2 = invoiceItemDao.addInvoiceItem(invoiceItem2);

        // populate list of items
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();

        // Populate invoice view model
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoice(invoice);
        invoiceViewModel.setCustomer(customer);
        invoiceViewModel.setInvoiceItemList(invoiceItemList);

        return invoiceViewModel;
    }

    public InvoiceViewModel findInvoice(int id){
        return buildInvoiceViewModel(invoiceDao.getInvoice(id));
    }

    public List<InvoiceViewModel> findAllInvoices(){

        List<InvoiceViewModel> invoiceViewModelList = null;
        InvoiceViewModel invoiceViewModel = null;

        // get list of all invoices
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        Iterator<Invoice> invoiceIterator = invoiceList.iterator();
        while (invoiceIterator.hasNext()) {
            invoiceViewModel = buildInvoiceViewModel( invoiceIterator.next());
            invoiceViewModelList.add(invoiceViewModel);
        }

        return invoiceViewModelList;
    }

    // Helper methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){

        // build invoice view model
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        // load invoice in view model
        invoiceViewModel.setInvoice(invoice);

        // get customer from the invoice
        Customer customer = customerDao.getCustomer(invoice.getCustomerId());

        // load customer to the view model
        invoiceViewModel.setCustomer(customer);

        // get list of invoice items by invoiceId
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(invoice.getInvoiceId());

        // load list of invoice items to view model
        invoiceViewModel.getInvoiceItemList(invoiceItemList);

        return invoiceViewModel;

    }

}