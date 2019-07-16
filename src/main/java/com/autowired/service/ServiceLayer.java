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
import java.util.List;

@Component
public class ServiceLayer {

    // private AlbumDao albumDao;
    // private ArtistDao artistDao;
    // private LabelDao labelDao;
    // private TrackDao trackDao;
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

        Customer customer = new Customer();

        customer.setFirstName("fName");
        customer.setLastName("lName");
        customer.setCompany("Company1");
        customer.setEmail("email@gmail.com");
        customer.setPhone("704-777-7777");

        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();

        invoice.setOrderDate(LocalDate.now());
        invoice.setReturnDate(LocalDate.now().plus(5, ChronoUnit.DAYS));
        invoice.setPickUpDate(LocalDate.now());
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setLateFee(BigDecimal.valueOf(95.99));
        invoice = invoiceDao.addInvoice(invoice);

        Item item = new Item();

        item.setName("itemName");
        item.setDescription("itemDescription");
        item.setDailyRate(BigDecimal.valueOf(5.99));
        item = itemDao.addItem(item);


        InvoiceItem invoiceItem = new InvoiceItem();

        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setDiscount(BigDecimal.valueOf(1.99));
        invoiceItem.setUnitRate(BigDecimal.valueOf(6.99));
        invoiceItem.setQuantity(10);


        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setInvoice(invoice);
        invoiceVM.setCustomer(customer);
        invoiceVM.setInvoiceItemList(invoiceItemList);

        return invoiceVM;
    }

    public InvoiceViewModel findInvoice(int id){
        Invoice invoice = invoiceDao.getInvoice(id);

        return buildInvoiceViewModel;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();


        return invoiceViewModel;
    }

}
