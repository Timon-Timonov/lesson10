package org.example.utils;

import org.example.Constants;
import org.example.pojo.embeddable.Address;
import org.example.service.AdminServise;

import java.util.Random;

public class Creater {

    private static final Random RANDOM = new Random();

    private Creater() {
    }

    public static void createTasks(AdminServise adminServise) {
        for (int i = 0; i < Constants.COUNT_OF_EACH_TASKS; i++) {
            Address address = Address.builder()
                                  .city(Constants.CITIES[RANDOM.nextInt(Constants.CITIES.length)])
                                  .street(Constants.STREETS[RANDOM.nextInt(Constants.CITIES.length)])
                                  .build();

            adminServise.createHomeTask(Constants.HOME_TASK + i,
                Constants.HOMETASK_DESCRIPTION + i,
                null,
                address);
            adminServise.createWorkTask(Constants.WORK_TASK + i,
                Constants.WORKTASK_DESCRIPTION + i,
                RANDOM.nextInt(Constants.MAX_COST_VALUE));
            adminServise.createTask(Constants.TASK + i,
                Constants.TASK_DESCRIPTION + i);
        }
    }
}
