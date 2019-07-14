package com.autowired.controller;

import com.autowired.dao.InvoiceItemDaoJdbcTemplateImpl;
import com.autowired.dao.ItemDaoJdbcTemplateImpl;
import com.autowired.model.InvoiceItem;
import com.autowired.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class ItemController {

    @Autowired
    private ItemDaoJdbcTemplateImpl itemDaoJdbcTemplate;

    /**
     * Invokes the addItem method of the itemDaoJdbcTemplate with the Item object
     * deserialized from the RequestBody JSON by the Jackson Mapper class
     * @param item
     * @return Item object JSON
     */
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item addItem(@RequestBody @Valid Item item){
        return itemDaoJdbcTemplate.addItem(item);
    }

    /**
     * Invokes the updateItem method of the invoiceDaoJdbcTemplate with the Item object
     * deserialized from the RequestBody JSON by the Jackson Mapper class  and the itemId supplied
     * by the client.
     * @param itemId
     */
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItem(@PathVariable int itemId, @RequestBody @Valid Item item) {
        item.setItemId(itemId);
        itemDaoJdbcTemplate.updateItem(item);
    }

    /**
     * Invokes the getItem method of the itemDaoJdbcTemplate with the itemId supplied
     * by the client.
     * @param itemId
     * @return Item object JSON
     */
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItem(@PathVariable int itemId) {
        return itemDaoJdbcTemplate.getItem(itemId);
    }

    /**
     * Invokes the deleteItem method of the itemDaoJdbcTemplate with the itemId supplied
     * by the client.
     * @param itemId
     */
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItem(@PathVariable int itemId) {
        itemDaoJdbcTemplate.deleteItem(itemId);
    }

    /**
     * Invokes the getAllItems method of the itemDaoJbbcTemplate
     * @return List of Item objects JSON
     */
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItems(){
        return itemDaoJdbcTemplate.getAllItems();
    }
}
