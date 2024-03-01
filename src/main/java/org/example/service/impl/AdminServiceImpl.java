package org.example.service.impl;

import org.example.dao.HomeTaskDao;
import org.example.dao.TaskDao;
import org.example.dao.WorkTaskDao;
import org.example.dao.impl.HomeTaskDaoImpl;
import org.example.dao.impl.TaskDaoImpl;
import org.example.dao.impl.WorkTaskDaoImpl;
import org.example.pojo.HomeTask;
import org.example.pojo.Task;
import org.example.pojo.WorkTask;
import org.example.pojo.embeddable.Address;
import org.example.service.AdminServise;

import java.sql.Timestamp;
import java.util.List;

public class AdminServiceImpl implements AdminServise {

    private final HomeTaskDao homeTaskDao = new HomeTaskDaoImpl();
    private final WorkTaskDao workTaskDao = new WorkTaskDaoImpl();
    private final TaskDao taskDao = new TaskDaoImpl();

    @Override
    public void deleteRowById(Long id) {

        if (id != null) {
            taskDao.delete(id);
        }
    }

    @Override
    public Task createTask(String name, String description) {

        return taskDao.create(Task.builder()
                                  .name(name)
                                  .description(description)
                                  .build());
    }

    @Override
    public Task updateTask(Task task) {

        return taskDao.update(task);
    }

    @Override
    public HomeTask createHomeTask(String name, String description, Timestamp startDate, Address address) {

        return homeTaskDao.create(HomeTask.builder()
                                      .address(address)
                                      .description(description)
                                      .name(name)
                                      .startDate(startDate)
                                      .build());
    }

    @Override
    public HomeTask updateHomeTask(HomeTask homeTask) {

        return homeTaskDao.update(homeTask);
    }

    @Override
    public WorkTask createWorkTask(String name, String description, Integer cost) {

        return workTaskDao.create(WorkTask.builder()
                                      .cost(cost)
                                      .name(name)
                                      .description(description)
                                      .build());
    }

    @Override
    public WorkTask updateWorkTask(WorkTask workTask) {

        return workTaskDao.update(workTask);
    }

    @Override
    public List<Task> getAllTasks() {

        return taskDao.getAll();
    }

    @Override
    public List<HomeTask> getAllHomeTasks() {

        return homeTaskDao.getAll();
    }

    @Override
    public List<WorkTask> getAllWorkTasks() {

        return workTaskDao.getAll();
    }

    @Override
    public Task getById(Long id) {
        if (id != null) {
            return taskDao.get(id);
        }
        return null;
    }

    @Override
    public void close() {

        homeTaskDao.closeManager();
        workTaskDao.closeManager();
        taskDao.closeManager();
    }
}
