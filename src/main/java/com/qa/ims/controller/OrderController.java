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
    public Order create(){
        Order order;
        LOGGER.info("Enter customer id");
        Long customer_id = utils.getLong();
        order= orderDAO.create(new Order(customer_id));
        LOGGER.info("Enter item id to add into the order");
        Long item_id = utils.getOrderItemAction();
        LOGGER.info("Enter Quantity");
        int quantity = utils.getInt();
        orderDAO.Create_orderItem(order, item_id,quantity);
        return  null;
    }




    @Override
    
    public Order update() {
        boolean validOrderID = false;
        Long id;
        Order order;
        do {
            LOGGER.info("Please enter the id of the order you would like to update");
            id = utils.getLong();

            order = orderDAO.readOrder(id);
            if(order != null){
                validOrderID = true;
            }else {
                LOGGER.info("This order id does not exist!");
            }
        }while(!validOrderID);

        boolean showOptions = true;
        do {
            LOGGER.info("What would you like to update?");
            LOGGER.info("ADD: To add an item to an order");
            LOGGER.info("REMOVE: To remove an item from an order");
            LOGGER.info("RETURN: To return to previous selection");

            boolean stop = false;
            do {
                String option = utils.getString().toLowerCase();
                switch (option) {
                    case "add":
                        boolean validChecker = false;

                        do {
                            LOGGER.info("Please enter the Item id to add into the order");
                            LOGGER.info("Enter q to exit without adding items");
                            Long item_id = utils.getOrderItemAction();
                            if(item_id == null) {
                                stop = true;
                                break;
                            }
                            LOGGER.info("Enter the quantity of items to add to your order");
                            int quantity = utils.getInt();
                            order = orderDAO.Create_orderItem(order, item_id, quantity);

                            if(order != null) {
                                LOGGER.info("Item added to order!");
                                validChecker = true;
                                stop = true;
                            }
                        }while(!validChecker);
                        break;
                    case "remove":
                        validChecker = false;
                        do {
                            LOGGER.info("Please enter the Item id to remove from your order");
                            LOGGER.info("Enter q to exit without removing items");
                            Long item_id = utils.getOrderItemAction();
                            if(item_id == null) {
                                stop = true;
                                break;
                            }
                            order = orderDAO.Delete_orderItem(order, item_id);

                            if(order != null) {
                                LOGGER.info("Item removed from order!");
                                validChecker = true;
                                stop = true;
                            }
                        }while(!validChecker);
                        break;
                    case "return":
                        stop = true;
                        showOptions = false;
                        break;
                    default:
                        LOGGER.info("That's not an option! Please try again");
                        break;
                }
            } while (!stop);
        } while (showOptions);



        LOGGER.info("Order has been Updated");
        return order;
    }








    @Override
    public int delete() {
        LOGGER.info("enter order id to delete your order");
        Long id = utils.getLong();
        return orderDAO.delete(id);
    }



}
