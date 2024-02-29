package org.example.dao.impl;

import org.example.MockConstants;
import org.example.MockUtil;
import org.example.dao.TaskDao;
import org.example.pojo.HomeTask;
import org.example.pojo.Task;
import org.example.pojo.WorkTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TaskDaoImplTest {

    private static TaskDao taskDao;

    @BeforeAll
    public static void testBeforAll() {

        taskDao = new TaskDaoImpl();
    }

    @AfterAll
    public static void testAfterAll() {

        taskDao.closeManager();
    }

    @Test
    public void testCreateAndUpdateAndGetAll() {

        int count = taskDao.getAll().size();
        Task task = MockUtil.getTask(MockConstants.I_0);
        HomeTask homeTask = MockUtil.getHomeTask(MockConstants.I_2);
        WorkTask workTask = MockUtil.getWorkTask(MockConstants.I_3);
        Task task1 = taskDao.create(task);
        Task task2 = taskDao.create(homeTask);
        Task task3 = taskDao.create(workTask);

        assertNotNull(task2.getId());
        assertTrue(task2 instanceof HomeTask);
        assertNotNull(task3.getId());
        assertTrue(task3 instanceof WorkTask);

        int count1 = taskDao.getAll().size();

        long id = task1.getId();

        task.setDescription(MockConstants.NEW_DESCRIPTION);
        task.setName(MockConstants.NEW_NAME);
        Task task4 = taskDao.update(task);
        int count2 = taskDao.getAll().size();

        assertEquals(id, task4.getId());
        assertEquals(MockConstants.NEW_NAME, task4.getName());
        assertEquals(MockConstants.NEW_DESCRIPTION, task4.getDescription());
        assertEquals((count + MockConstants.I_3), count1);
        assertEquals(count1, count2);
    }
}