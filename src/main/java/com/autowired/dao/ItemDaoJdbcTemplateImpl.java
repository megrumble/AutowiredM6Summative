package com.autowired.dao;

import com.autowired.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {
    JdbcTemplate jdbcTemplate;

    private static final String ADD_ITEM_SQL =
                "insert into item (name, description, daily_rate) values (?, ?, ?)";
    private static final String SELECT_ITEM_SQL =
                "select * from item here item_id = ?";
    private static final String SELECT_ALL_ITEMS_SQL =
                "select * from item";
    private static final String UPDATE_ITEM_SQL =
                "update item name = ?, description = ?, daily_rate = ? where item_id = ?";
    private static final String DELETE_SQL =
                "delete from item where item_id = ?";

    @Autowired
    public ItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add a Item object to the database.
     * @param item - The Item object sent from the RestController
     * @return Item - The Item object is returned to the RestController
     */
    @Override
    public Item addItem(Item item) {
        jdbcTemplate.update(ADD_ITEM_SQL,
                item.getName(),
                item.getDescription(),
                item.getDailyRate());

        int itemId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        item.setItemId(itemId);

        return item;
    }

    /**
     * Retrieves a Item object from the database using a given Item id
     * @param itemId
     * @return Item - The Item object is returned to the RestController
     */
    @Override
    public Item getItem(int itemId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ITEM_SQL, this::mapRowToItem, itemId);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all Items in the database
     * @return List<Item>
     */
    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SELECT_ALL_ITEMS_SQL, this::mapRowToItem);
    }

    /**
     * Updates an Item from the Item object sent from the RestController
     * @param item - The Item object sent from the RestController
     */
    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(UPDATE_ITEM_SQL,
                item.getName(),
                item.getDescription(),
                item.getDailyRate(),
                item.getItemId());
    }

    /**
     * Deletes a Item from the database using a ItemID from the RestController
     * @param itemId
     */
    @Override
    public void deleteItem(int itemId) {
        jdbcTemplate.update(DELETE_SQL, itemId);
    }

    private Item mapRowToItem(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();

        item.setItemId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setDailyRate(rs.getBigDecimal("daily_rate"));

        return item;
    }
}
