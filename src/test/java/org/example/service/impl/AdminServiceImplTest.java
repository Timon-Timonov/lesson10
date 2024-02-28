package org.example.service.impl;

import org.example.MockConstants;
import org.example.MockUtil;
import org.example.dao.HomeTaskDao;
import org.example.dao.TaskDao;
import org.example.dao.WorkTaskDao;
import org.example.dao.impl.HomeTaskDaoImpl;
import org.example.dao.impl.TaskDaoImpl;
import org.example.dao.impl.WorkTaskDaoImpl;
import org.example.pojo.HomeTask;
import org.example.pojo.Task;
import org.example.pojo.WorkTask;
import org.example.service.AdminServise;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.MockUtil.getAddress;
import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {


    private static AdminServise adminServise;
    private static TaskDao taskDao = new TaskDaoImpl();

    @BeforeAll
    public static void tetsBeforeAll() {

        adminServise = new AdminServiceImpl();
    }

    @AfterAll
    public static void testAfterAll() throws Exception {

        taskDao.closeManager();
        adminServise.close();
    }

    @Test
    void deleteRowByIdAndGetById() {

        adminServise.createTask(MockConstants.TASK, MockConstants.TASK_DESCRIPTION);
        int count = taskDao.getAll().size();
        Task task = adminServise.getById(MockConstants.ID_1);
        Task task1 = taskDao.get(MockConstants.ID_1);
        assertEquals(task, task1);
        adminServise.deleteRowById(MockConstants.ID_1);
        List<Task> list = taskDao.getAll();
        assertEquals(list.size() + MockConstants.I_1, count);
        assertFalse(list.contains(task));
    }

    @Test
    void createAllKindOfTasks() {

        int count = taskDao.getAll().size();

        for (int i = 0; i < MockConstants.COUNT_OF_EACH_TASKS; i++) {
            adminServise.createHomeTask(MockConstants.HOME_TASK + i,
                MockConstants.HOMETASK_DESCRIPTION + i,
                null,
                getAddress(i));
            adminServise.createWorkTask(MockConstants.WORK_TASK + i,
                MockConstants.WORKTASK_DESCRIPTION + i,
                MockConstants.COST_VALUE[i]);
            adminServise.createTask(MockConstants.TASK + i,
                MockConstants.TASK_DESCRIPTION + i);
        }

        assertEquals((count + MockConstants.COUNT_OF_EACH_TASKS * MockConstants.I_3),
            taskDao.getAll().size());
    }

    @Test
    void updateTask() {

        adminServise.createTask(MockConstants.TASK, MockConstants.TASK_DESCRIPTION);
        Task task = taskDao.get(MockConstants.ID_2);
        Long id = task.getId();
        task.setName(MockConstants.NEW_NAME);
        task.setDescription(MockConstants.NEW_DESCRIPTION);
        Task task1 = adminServise.updateTask(task);

        assertEquals(task1.getId(), id);
        assertEquals(task1.getName(), MockConstants.NEW_NAME);
        assertEquals(task1.getDescription(), MockConstants.NEW_DESCRIPTION);
        assertTrue(taskDao.getAll().contains(task1));
    }


    @Test
    void updateHomeTask() {

        HomeTaskDao homeTaskDao = new HomeTaskDaoImpl();
        HomeTask homeTask = MockUtil.getHomeTask(MockConstants.I_5);
        HomeTask homeTask1 = homeTaskDao.create(homeTask);
        homeTask1.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        homeTask1.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
        homeTask1.setName(MockConstants.NEW_NAME);
        HomeTask homeTask2 = adminServise.updateHomeTask(homeTask1);
        assertEquals(homeTask2, homeTaskDao.get(homeTask2.getId()));
    }


    @Test
    void updateWorkTask() {

        WorkTaskDao workTaskDao = new WorkTaskDaoImpl();
        WorkTask workTask = MockUtil.getWorkTask(MockConstants.I_5);
        WorkTask workTask1 = workTaskDao.create(workTask);
        workTask1.setCost(MockConstants.COST_VALUE[MockConstants.I_1]);
        workTask1.setDescription(MockConstants.NEW_DESCRIPTION);
        workTask1.setName(MockConstants.NEW_NAME);
        WorkTask workTask2 = adminServise.updateWorkTask(workTask1);
        assertEquals(workTask2, workTaskDao.get(workTask2.getId()));
    }

    @Test
    void getAllTasks() {

        List<Task> list = taskDao.getAll();
        List<Task> list1 = adminServise.getAllTasks();
        assertEquals(list.size(), list1.size());
        assertTrue(list1.containsAll(list));
    }

    @Test
    void getAllHomeTasks(){

        List<HomeTask> list = new HomeTaskDaoImpl().getAll();
        List<HomeTask> list1 = adminServise.getAllHomeTasks();
        assertEquals(list.size(), list1.size());
    }

    @Test
    void getAllWorkTasks() {

        List<WorkTask> list = new WorkTaskDaoImpl().getAll();
        List<WorkTask> list1 = adminServise.getAllWorkTasks();
        assertEquals(list.size(), list1.size());
        assertTrue(list1.containsAll(list));
    }
}