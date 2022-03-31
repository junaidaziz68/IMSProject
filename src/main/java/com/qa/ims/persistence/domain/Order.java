package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {


    private Long id;
    private Long  customer_id;
    private Long item_id ;
    private int quantity;
    private Double cost;


    public Order(Long id, Long customer_id, Long item_id, int quantity,Double cost) {
        this.id = id;
        this.customer_id = customer_id;
        this.item_id = item_id;
        this.quantity=quantity;
        this.cost = cost;
    }



    public Order(Long customer_id, Long item_id, int quantity, Double cost) {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customer_id, order.customer_id) && Objects.equals(item_id, order.item_id) && Objects.equals(cost, order.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer_id, item_id, cost);
    }
}





