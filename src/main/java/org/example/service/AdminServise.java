package org.example.service;

import org.example.pojo.HomeTask;
import org.example.pojo.Task;
import org.example.pojo.WorkTask;
import org.example.pojo.embeddable.Address;

import java.sql.Timestamp;
import java.util.List;

public interface AdminServise extends AutoCloseable {

    void deleteRowById(Long id);

    Task createTask(String name, String description);

    Task updateTask(Task task);

    HomeTask createHomeTask(String name, String description, Timestamp startDate, Address address);

    HomeTask updateHomeTask(HomeTask homeTask);

    WorkTask createWorkTask(String name, String description, Integer cost);

    WorkTask updateWorkTask(WorkTask workTask);

    List<Task> getAllTasks();

    List<HomeTask> getAllHomeTasks();

    List<WorkTask> getAllWorkTasks();

    Task getById(Long id);
}
