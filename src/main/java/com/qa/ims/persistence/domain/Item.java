package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item  {
    // keeping variables private for security as items will be linked to customer name
    private long id;
    private String name ;
    private double value;
    private Integer quantity;



    public Item()  {


    }


    public Item(long id, String name, double value, Integer quantity) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }


    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return id == item.id && Double.compare(item.value, value) == 0 && Objects.equals(name, item.name) && Objects.equals(quantity, item.quantity);
    }
// added hash for hash-mapping
    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, quantity);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;







    }
}
