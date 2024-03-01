package org.example.dao.impl;

import org.example.dao.WorkTaskDao;
import org.example.pojo.WorkTask;

public class WorkTaskDaoImpl extends DaoImpl<WorkTask, Long> implements WorkTaskDao {

    public WorkTaskDaoImpl() {

        super(WorkTask.class);
    }
}
