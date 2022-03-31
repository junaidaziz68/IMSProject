package com.qa.ims.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderController implements CrudController<Order> {


    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private Utils utils;


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


    public Order create() {

        LOGGER.info("Please enter your customer id");
        Long customer_id = utils.getLong();
        LOGGER.info("Please enter item id");
        Long item_id = utils.getLong();
        LOGGER.info("please enter purchase quantity");
        int quantity = utils.getDouble().intValue();
        Order order = orderDAO.create(new Order( customer_id, item_id, quantity,null));
        LOGGER.info("Item created");

        return order;

    }

    @Override
    public Order update() {
        LOGGER.info("Please enter the order id  you would like to update");
        Long id = utils.getLong();
        LOGGER.info("Please enter a item id");
        Long item_id = utils.getLong();
        LOGGER.info("Please enter a quantity");
        int quantity = utils.getDouble().intValue();
        Order order = orderDAO.update(new Order(id, item_id, quantity,null));
        LOGGER.info("Customer Updated");
        return order;
    }


    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = utils.getLong();
        return orderDAO.delete(id);
    }





}
