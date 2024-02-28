package org.example.dao.impl;

import org.example.dao.HomeTaskDao;
import org.example.pojo.HomeTask;

public class HomeTaskDaoImpl extends DaoImpl<HomeTask, Long> implements HomeTaskDao {

    public HomeTaskDaoImpl() {

        super(HomeTask.class);
    }
}
