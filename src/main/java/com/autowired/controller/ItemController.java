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

    @RequestMapping(value = "/item", method = RequestMethod.POST)

    @ResponseStatus(value = HttpStatus.CREATED)
    public Item addItem(@RequestBody @Valid Item item){
        return item;
    }
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public Item updateItem(@PathVariable int itemId, @RequestBody @Valid Item item){
        item.setItemId(itemId);
        return item;
    }
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItem(@PathVariable int itemId){
        Item item = new Item();
        item.setItemId(itemId);
        return item;
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable int itemId){
        itemDaoJdbcTemplate.deleteItem(itemId);
    }
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public List<Item> getAllItems(){
        return itemDaoJdbcTemplate.getAllItems();
    }
}
