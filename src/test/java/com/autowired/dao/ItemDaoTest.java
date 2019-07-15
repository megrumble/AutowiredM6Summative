package com.autowired.dao;

import com.autowired.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {

    @Autowired
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        // cleanup the test database
        itemDao.getAllItems()
                    .stream()
                    .forEach(item -> itemDao.deleteItem(item.getItemId()));
    }

    @Test
    public void addGetDeleteItem() {

        // Add item to the database
        Item item = new Item();
        item.setName("Item Name");
        item.setDescription("Item Description");
        item.setDailyRate(new BigDecimal("25.99"));
        item = itemDao.addItem(item);

        // Get item from database
        Item retrievedItem = itemDao.getItem(item.getItemId());

        // Check if item retrieved is the same as the one in RAM / this is checking if the addItem and getItem are working
        assertEquals(item, retrievedItem);

        // Delete the item
        itemDao.deleteItem(item.getItemId());

        // Check if the item was successfully deleted
        retrievedItem =itemDao.getItem(item.getItemId());
        assertNull(retrievedItem);

    }

    @Test
    public void getAllItems() {

        Item item = new Item();
        item.setName("Item Name");
        item.setDescription("Item Description");
        item.setDailyRate(new BigDecimal("25.99"));
        item = itemDao.addItem(item);

        item = new Item();
        item.setName("Item Name 2");
        item.setDescription("Item Description 2");
        item.setDailyRate(new BigDecimal("55.99"));
        item = itemDao.addItem(item);

        // get all items
        List<Item> itemList = itemDao.getAllItems();

        // Check if 2 items are retrieved
        assertEquals(2, itemList.size());

    }

    @Test
    public void updateItem() {
        Item item = new Item();
        item.setName("Item Name");
        item.setDescription("Item Description");
        item.setDailyRate(new BigDecimal("25.99"));
        item = itemDao.addItem(item);

        item.setName("Item Name Amended");
        item.setDescription("Item Description Amended");
        item.setDailyRate(new BigDecimal("55.99"));
        itemDao.updateItem(item);

        // retrieve updated item from the database
        Item retrievedItem = itemDao.getItem(item.getItemId());
        // Check if the item in the database was changed / did the updateItem work.
        assertEquals(item, retrievedItem);
    }




}
