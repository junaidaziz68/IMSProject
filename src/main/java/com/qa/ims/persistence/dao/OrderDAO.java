package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// print the receipt
// make it so I can add multiple items
//calm

public class OrderDAO implements Dao<Order> {


    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long customer_id = resultSet.getLong("customer_id");
        Long item_id = resultSet.getLong("item_id");
        int quantity = resultSet.getInt("quantity");
        Double cost = resultSet.getDouble("cost");
        return new Order(id, customer_id, item_id, quantity, cost);
    }

    @Override
    public Item readItem(Long item_id) {
        return null;
    }

    //Reads all order from the database

    @Override
    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT orders.id, customer_id, item, order_items.quantity FROM orders JOIN order_items ON orders.id = order_items.order_id JOIN items ON items.id = order_items.item_id; ");) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
            }
            return orders;

        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Order read(Long id) {
        return null;
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    //Creates an Order in the database


    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO orders(customer_id) values('" + order.getCustomer_id() + "'");
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order readOrder(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items where id = " + id);) {
            if (resultSet.next()) {
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    //Updates a order in the database

    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("");
            return readOrder(order.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    // deletes order
    @Override
    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            return statement.executeUpdate("DELETE orders, order_items FROM orders INNER JOIN order_items WHERE orders.id=order_items.order_id AND orders.id =  " + id);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }


    public Order order(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("INSERT INTO order_items(order_id, item_id, quantity) values('" + order.getId() + "','" + order.getItem_id() + "','" + order.getQuantity() + "');");
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return order;

    }
}