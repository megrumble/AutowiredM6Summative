package com.autowired.dao;

import com.autowired.model.Item;

import java.util.List;

public interface ItemDao {
    /**
     * Add a Item object to the database.
     * @param item - The Item object sent from the RestController
     * @return Item - The Item object is returned to the RestController
     */
    Item addItem(Item item);

    /**
     * Retrieves a Item object from the database using a given Item id
     * @param itemId
     * @return Item - The Item object is returned to the RestController
     */
    Item getItem(int itemId);

    /**
     * Retrieves a list of all Items in the database
     * @return List<Item>
     */
    List<Item> getAllItems();

    /**
     * Updates an Item from the Item object sent from the RestController
     * @param item - The Item object sent from the RestController
     */
    void updateItem(Item item);

    /**
     * Deletes a Item from the database using a ItemID from the RestController
     * @param itemId
     */
    void deleteItem(int itemId);

}
