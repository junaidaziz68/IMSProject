package com.qa.ims.controller;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemController implements CrudController<Item> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemDAO itemDAO;
    private Utils utils;

    public ItemController(ItemDAO itemDAO, Utils utils) {
        super();
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    /**
     * Reads all items to the logger
     */
    @Override
    public List<Item>  readAll() {
        List<Item> items = itemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }


    /**
     * Creates a item by taking in user input
     */
    @Override
    public Item create() {

        LOGGER.info("Please enter a name");
        String name = utils.getString();
        LOGGER.info("Please enter a value");
        double value = utils.getDouble();
        LOGGER.info("please enter quantity");
         int quantity = utils.getDouble().intValue();
        Item item = itemDAO.create(new Item(name, value,quantity));
        LOGGER.info("Item created");

        return item;

    }

    /**
     * Updates an existing item by taking in user input
     * @return
     */
    @Override
    public Item  update() {
// to check if item is valid
        boolean itemChecker = false;
        Long id;
        do {
            LOGGER.info("Please enter the id of the item you would like to update");
            id = utils.getLong();
            Item checker= itemDAO.readItem(id);
            if(checker != null){
                itemChecker= true;
            }else {
                LOGGER.info("This order id does not exist!");
            }
        }while(!itemChecker);

        LOGGER.info("Please enter a name");
        String name = utils.getString();
        LOGGER.info("Please enter a value");
        double value = utils.getDouble();
        Item item = itemDAO.update(new Item(id, name, value, null));
        LOGGER.info("Item Updated");
        return item;
    }


    //Deletes an existing item by the id of the item
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long delId = utils.getLong();
        return itemDAO.delete(delId);

    }
}



