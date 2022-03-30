package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item  {

    // keeping variables private for security as items will be linked to customer name
    private long id;
    private String item;
    private double price ;
    private Integer quantity;



    public Item()  {


    }


    public Item(String item, double price) {
        this.item= item;
        this.price = price;
    }

    public Item(String item, double price, Integer quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(Long id, String item, double price, Integer quantity) {
        this.id = id;
        this.item= item;
        this.price= price;
        this.quantity = quantity;
    }



    // getters and setters
    public long getId() {
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

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(item, item.item) && Objects.equals(quantity, item.quantity);
    }
// added hash for hash-mapping
    @Override
    public int hashCode() {
        return Objects.hash(id, item, price, quantity);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;







    }
}
