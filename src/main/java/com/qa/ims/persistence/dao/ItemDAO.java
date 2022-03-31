package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


 public class ItemDAO implements Dao<Item>{

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String item = resultSet.getString("item");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        return new Item(id, item, price,quantity);
    }

    @Override
     public Item readItem(Long item_id) {
         return null;
     }

     /**
     * Reads all items from the database
     *
     * @return A list of items
     */
    @Override
    public List<Item> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from items");) {
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(modelFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }



     public Item readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }



    /**
     * Creates a item in the database
     *
     * @param item - takes in a item object. id will be ignored
     */

     @Override
     public Item create(Item item) {
         try (Connection connection = DBUtils.getInstance().getConnection();
              PreparedStatement statement = connection
                      .prepareStatement("INSERT INTO items(item, price, quantity) VALUES (?,?, ?)");) {
             statement.setString(1, item.getItem());
             statement.setDouble(2, item.getPrice());
             statement.setInt(2, item.getQuantity());
             statement.executeUpdate();
             return readLatest();
         } catch (Exception e) {
             LOGGER.debug(e);
             LOGGER.error(e.getMessage());
         }
         return null;
     }

     @Override
     public Item read(Long id) {
         try (Connection connection = DBUtils.getInstance().getConnection();
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?");) {
             statement.setLong(1, id);
             try (ResultSet resultSet = statement.executeQuery();) {
                 resultSet.next();
                 return modelFromResultSet(resultSet);
             }
         } catch (Exception e) {
             LOGGER.debug(e);
             LOGGER.error(e.getMessage());
         }
         return null;
     }

    /**
     * Updates a item in the database
     *
     * @param item - takes in a item object, the id field will be used to
     *                 update that item in the database
     * @return
     */
    @Override
    public Item update(Item item) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("update items set item ='" + item.getItem() + "', price ='" + item.getPrice() + "', quantity ='" + item.getQuantity() + "'  where id =" + item.getId());
            return readItem(item.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a item in the database
     *
     * @param id - id of the item
     */
    @Override
    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            return statement.executeUpdate("delete from items where id = " + id);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}