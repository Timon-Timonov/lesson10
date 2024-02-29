package org.example.dao.impl;

import org.example.dao.HomeTaskDao;
import org.example.pojo.HomeTask;

public class HomeTaskDaoImpl extends DaoImpl<HomeTask, Long> implements HomeTaskDao {

    public HomeTaskDaoImpl() {

        super(HomeTask.class);
    }

    @Override
    public HomeTask update(HomeTask object) {

        Long id = super.update(object).getId();
        return super.get(id);
    }
}
