package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderController implements CrudController<Order> {


    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private Utils utils;
    private ItemDAO itemDAO;

    public OrderController(OrderDAO orderDAO, Utils utils) {
        this.orderDAO = orderDAO;
        this.utils = utils;
    }



    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDAO.readAll();
        for (Order order : orders) {
            LOGGER.info(order);
        }
        return orders;
    }

    @Override
    public Order create() {
        boolean validID = false;
        Order order;
        do {
            LOGGER.info("Please enter a customer id");
            Long customer_id = utils.getLong();
            order = orderDAO.create(new Order(customer_id));
            if(order != null){
                validID = true;
            }
        }while(!validID);

        boolean fin = false;
        do {
            LOGGER.info("Please enter the Item id to add into the order");
            Long item_id = utils.getOrderItemAction();
            LOGGER.info("Please enter q to stop");
            orderDAO.Create_orderItem(order, item_id);


        } while(!fin);

        LOGGER.info("Order created");
        return order;


    }




    @Override
    
    public Order update() {
        LOGGER.info("Order created");
        return update();

    }


    @Override
    public int delete() {
        LOGGER.info("Order created");

     return delete();
    }


}
