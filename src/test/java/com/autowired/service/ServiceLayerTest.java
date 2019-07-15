package com.autowired.service;

import com.autowired.dao.CustomerDao;
import com.autowired.dao.InvoiceDao;
import com.autowired.dao.InvoiceItemDao;
import com.autowired.dao.ItemDao;
import org.junit.Before;

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

        service = new ServiceLayer(customerDao, invoiceDao, itemDao, invoiceItemDao);

    }
}
