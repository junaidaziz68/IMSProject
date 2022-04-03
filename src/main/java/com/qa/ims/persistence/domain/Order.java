package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Order {


    private Long id;
    private Long  customer_id;
    private ArrayList<Item> items;
    private Double cost;





    public Order(ArrayList<Item> items) {
        this.items = items;
    }

    public Order(Long id, Long customer_id, ArrayList<Item> items) {
        this.id = id;
        this.customer_id = customer_id;
        this.items = items;
    }

    public Order (Long id,Long customer_id){

        this.id=id;
        this.customer_id=customer_id;

    }


    public Order(Long customer_id) {
		this.customer_id = customer_id;
	}


    public Order(Long customer_id, Long item_id, int quantity) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {return customer_id;}

    public void setCustomer_id(Long customer_id) {this.customer_id = customer_id;}

    public void setItems(ArrayList<Item> items){
		this.items = items;
	}


    public ArrayList<Item> getItems() {return items;}

    public double tCost(){
        double t = 0;
        for(Item x : items){t+=x.getPrice();}
        this.cost= t;
        return t;

    }
    public void setCost (double cost) {
        this.cost = cost;
    }


    public double getCost() {
        return cost;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", items=" + items +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customer_id, order.customer_id) && Objects.equals(items, order.items) && Objects.equals(cost, order.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer_id, items, cost);
    }
}





