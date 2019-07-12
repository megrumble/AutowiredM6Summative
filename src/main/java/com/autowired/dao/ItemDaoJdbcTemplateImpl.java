package com.autowired.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {
    JdbcTemplate jdbcTemplate;
    /**
     * Add a Item object to the database.
     *
     * @param item - The Item object sent from the RestController
     * @return Item - The Item object is returned to the RestController
     */
    @Override
    public Item addItem(Item item) {
        return null;
    }

    /**
     * Retrieves a Item object from the database using a given Item id
     *
     * @param itemId
     * @return Item - The Item object is returned to the RestController
     */
    @Override
    public Item getItem(int itemId) {
        return null;
    }

    /**
     * Retrieves a list of all Items in the database
     *
     * @return List<Item>
     */
    @Override
    public List<Item> getAllItems() {
        return null;
    }

    /**
     * Updates an Item from the Item object sent from the RestController
     *
     * @param item - The Item object sent from the RestController
     */
    @Override
    public void updateItem(Item item) {

    }

    /**
     * Deletes a Item from the database using a ItemID from the RestController
     *
     * @param itemId
     */
    @Override
    public void deleteItem(int itemId) {

    }
}
