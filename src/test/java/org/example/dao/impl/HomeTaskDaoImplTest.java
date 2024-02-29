package org.example.dao.impl;

import org.example.MockConstants;
import org.example.MockUtil;
import org.example.dao.HomeTaskDao;
import org.example.pojo.HomeTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class HomeTaskDaoImplTest {

    private static HomeTaskDao homeTaskDao;

    @BeforeAll
    public static void testBeforAll() {

        homeTaskDao = new HomeTaskDaoImpl();
    }

    @AfterAll
    public static void testAfterAll() {

        homeTaskDao.closeManager();
    }


    @Test
    public void testCreateAndReadAll() {

        int count1 = homeTaskDao.getAll().size();
        HomeTask homeTask = homeTaskDao.create(MockUtil.getHomeTask(MockConstants.I_1));

        List<HomeTask> list = homeTaskDao.getAll()
                                  .stream()
                                  .peek(Assertions::assertNotNull)
                                  .collect(Collectors.toList());
        int count2 = list.size();
        assertEquals(count1 + 1, count2);

        assertNotNull(homeTask);
        assertNotNull(homeTask.getId());
        assertNull(homeTask.getEndDate());
        assertNotNull(homeTask.getStartDate());

        assertEquals(MockUtil.getAddress(MockConstants.I_1), homeTask.getAddress());
        assertEquals((MockConstants.HOMETASK_DESCRIPTION + MockConstants.I_1), homeTask.getDescription());
        assertEquals((MockConstants.HOME_TASK + MockConstants.I_1), homeTask.getName());
    }
}