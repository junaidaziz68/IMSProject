package com.qa.ims.controller;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemController implements CrudController<Item>{

    public static final Logger LOGGER = LogManager.getLogger();
    private ItemDAO itemDAO;
    private Utils utils;
    private Item Item;


    public ItemController(ItemDAO itemDAO, Utils utils) {
        this.itemDAO = itemDAO;
        this.utils = utils;
    }




    @Override
    public List<Item> readAll() {

        List<Item> items = ItemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(item);
            return items;
    }

    @Override
    public Item create() {

            LOGGER.info("Please enter item name ");
            String nameItem = utils.getString();
            LOGGER.info("Please enter a Value");
            Double value = utils.getDouble();
            Item item = itemDAO.create(new Item(nameItem , value));
            LOGGER.info("Item has been created successfully");
            return item;
        }
        }

    }
// create a method to check if item id is valid untill we find id and print to system
    @Override
    public Item update() {
        boolean itemChecker = false;
        Long item_id;
        do {
            LOGGER.info("Please enter the id of the item you would like to update");
            item_id = utils.getLong();
            Item checker = itemDAO.readItem(item_id);
            if(checker != null){
                itemChecker = true;
            }else {
                LOGGER.info("This order id does not exist please enter a valid id !");
            }
        }while(!itemChecker);

        LOGGER.info("Please enter a name");
        String itemName = utils.getString();
        LOGGER.info("Please enter a value");
        double itemValue = utils.getDouble();
        Item item = itemDAO.update(new Item(item_id, itemName, itemValue, null));
        LOGGER.info("Item has been updated successfully");
        return item;


    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = utils.getLong();
        return itemDAO.delete(id);

    }
}
