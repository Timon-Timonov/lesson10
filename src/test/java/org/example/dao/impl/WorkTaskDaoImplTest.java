package org.example.dao.impl;

import org.example.MockConstants;
import org.example.MockUtil;
import org.example.dao.WorkTaskDao;
import org.example.pojo.WorkTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class WorkTaskDaoImplTest {

    private static WorkTaskDao workTaskDao;

    @BeforeAll
    public static void testBeforAll() {

        workTaskDao = new WorkTaskDaoImpl();
    }

    @AfterAll
    public static void testAfterAll() {

        workTaskDao.closeManager();
    }

    @Test
    public void testCreateAndDeleteAndGetAll() {

        WorkTask workTask = MockUtil.getWorkTask(MockConstants.I_4);
        WorkTask workTask1 = MockUtil.getWorkTask(MockConstants.I_5);

        int count = workTaskDao.getAll().size();
        WorkTask workTask2 = workTaskDao.create(workTask);
        WorkTask workTask3 = workTaskDao.create(workTask1);
        List<WorkTask> workTaskList = workTaskDao.getAll();
        int count1 = workTaskList.size();

        assertTrue(workTaskList.contains(workTask2));
        assertTrue(workTaskList.contains(workTask3));
        assertEquals((count + MockConstants.I_2), count1);
        assertNotNull(workTask2.getId());
        assertNotNull(workTask3.getId());

        workTaskDao.delete(workTask3.getId());
        List<WorkTask> list = workTaskDao.getAll();
        assertFalse(list.contains(workTask3));
        assertEquals(list.size() + MockConstants.I_1, count1);
    }
}