package com.autowired.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

//    itemId int(11) not null auto_increment primary key,
//    name varchar(50) not null,
//    description varchar(255),
//    dailyRate decimal(8.2) not null
    // same as track
    private int itemId;

    @NotEmpty(message = "You must supply a value for name.")
    private String name;

    @NotEmpty(message = "You must supply a value for description.")
    private String description;

    private BigDecimal dailyRate;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getItemId() == item.getItemId() &&
                getName().equals(item.getName()) &&
                getDescription().equals(item.getDescription()) &&
                getDailyRate().equals(item.getDailyRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getName(), getDescription(), getDailyRate());
    }
}
