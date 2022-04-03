package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item  {

    // keeping variables private for security as items will be linked to customer name
    private Long id;
    private String item;
    private double price ;

    public Item(String item, double price) {
        this.setItem(item);
        this.setPrice(price);
    }

    public Item(Long id, String item, double price) {
        this.setId(id);
        this.setItem(item);
        this.setPrice(price);

    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id:" + id + " item:" + item + " price:" + price;}


    @Override
    public int hashCode() {
        return Objects.hash(id, item, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(item, item.item);
// added hash for hash-mapping


    }
}
