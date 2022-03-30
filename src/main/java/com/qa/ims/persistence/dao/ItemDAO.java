package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ItemDAO implements  Dao<Item>{

   public static final Logger LOGGER = LogManager.getLogger();

    public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double value = resultSet.getDouble("value");
    }

    @Override
    public List<Item> readAll() {

        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
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

    @Override
    public Item read(Long id) {
        return null;
    }

    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public int delete(long id) {
        return 0;
    }


}
