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


public class OrderDAO implements Dao<Order> {


    public static final Logger LOGGER = LogManager.getLogger();

   
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customer_id = resultSet.getLong("customer_id");
		ArrayList<Item> items = getItems(id);
		return new Order(id, customer_id, items);
	}

    private ArrayList<Item> getItems(Long id) {

        return new ArrayList<>();

    }


    public Item modelFromResultSetItem(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String item = resultSet.getString("item");
		Double price = resultSet.getDouble("price");
        return new Item(id, item, price);
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
            statement.executeUpdate("INSERT INTO orders(customer_id) values('" + order.getCustomer_id() + "')");
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
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where id = " + id);) {
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
            statement.executeUpdate("UPDATE ORDERS SET customer_id ='" + order.getCustomer_id() + "' where id ='" + order.getId());
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
            return statement.executeUpdate("DELETE FROM orders WHERE ID orders.id =  " + id);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    // creating item in item_order table
    public Order Create_orderItem(Order order, long item_id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("INSERT INTO order_items(order_id, item_id) values('" + order.getId() + "','" + item_id +"');");
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return order;

    }
    // Delete from order_item table

    public Order Delete_orderItem(Order order, long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM order_items WHERE order_items.order_id = " + order.getId() + " AND order_items.item_id = " + item_id + ";");
		    return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return order; 


	}

    public ArrayList<Item> ItemList(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(""
						+ "SELECT items.id, items.name, items.value, order_items.quantity\r\n"
						+ "FROM order_items\r\n"
						+ "INNER JOIN items ON \r\n"
						+ "	order_items.item_id=items.id\r\n"
						+ "    AND order_id = "+ id +";"
						);) {
			ArrayList<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSetItem(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();






}
}