package org.example;

import org.example.pojo.HomeTask;
import org.example.pojo.Task;
import org.example.pojo.WorkTask;
import org.example.pojo.embeddable.Address;

public class MockUtil {

    private MockUtil() {
    }

    public static Address getAddress(int i) {

        return Address.builder()
                   .city(MockConstants.CITIES[i])
                   .street(MockConstants.STREETS[i])
                   .build();
    }

    public static HomeTask getHomeTask(int i) {

        return HomeTask.builder()
                   .name(MockConstants.HOME_TASK + i)
                   .description(MockConstants.HOMETASK_DESCRIPTION + i)
                   .address(getAddress(i))
                   .build();
    }

    public static WorkTask getWorkTask(int i) {

        return WorkTask.builder()
                   .name(MockConstants.WORK_TASK + i)
                   .description(MockConstants.WORKTASK_DESCRIPTION + i)
                   .cost(MockConstants.COST_VALUE[i])
                   .build();
    }

    public static Task getTask(int i) {

        return Task.builder()
                   .name(MockConstants.TASK + i)
                   .description(MockConstants.TASK_DESCRIPTION + i)
                   .build();
    }
}
