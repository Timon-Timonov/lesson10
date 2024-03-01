package org.example.dao.impl;

import org.example.dao.TaskDao;
import org.example.pojo.Task;

public class TaskDaoImpl extends DaoImpl<Task, Long> implements TaskDao {

    public TaskDaoImpl() {

        super(Task.class);
    }
}
