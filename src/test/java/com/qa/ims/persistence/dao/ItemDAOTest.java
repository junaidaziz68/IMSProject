package com.qa.ims.persistence.dao;
import com.qa.ims.persistence.dao.ItemDAOTest;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class ItemDAOTest {

    private final ItemDAO DAO = new ItemDAO();


    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Item created = new Item(1L,"ps5" , 2);
        assertEquals(created, DAO.create(created));
    }


    @Test
    public void testReadAll() {
        List<Item> expected = new ArrayList<>();
        expected.add(new Item(2L, "apple", 20));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Item(3L, "ps5", 30), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Item(5L, "ps5", 3), DAO.read(ID));
    }

    @Test
    public void testUpdate() {
        final Item updated = new Item(1L, "ps5", 2);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
}
