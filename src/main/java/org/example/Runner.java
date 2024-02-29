package org.example;

import org.example.pojo.HomeTask;
import org.example.pojo.Task;
import org.example.pojo.WorkTask;
import org.example.service.AdminServise;
import org.example.service.impl.AdminServiceImpl;
import org.example.utils.Creater;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        try {
            try (AdminServise service = new AdminServiceImpl()) {

                Creater.createTasks(service);

                List<HomeTask> listH = service.getAllHomeTasks();
                System.out.println(listH.size());
                listH.forEach(System.out::println);

                List<WorkTask> listW = service.getAllWorkTasks();
                System.out.println(listW.size());
                listW.forEach(System.out::println);

                List<Task> listT = service.getAllTasks();
                System.out.println(listT.size());
                listT.forEach(System.out::println);

                HomeTask homeTask;
                Long id = Constants.START_ID;
                Task task = service.getById(id);
                while (!(task instanceof HomeTask)) {
                    task = service.getById(++id);
                }
                homeTask = (HomeTask) task;

                System.out.println(homeTask.getDescription());
                System.out.println(homeTask.getName());
                System.out.println(homeTask.getId());

                homeTask.setName(Constants.NEW_NAME);
                homeTask.setDescription(Constants.NEW_DESCRIPTION);
                System.out.println(service.updateHomeTask(homeTask));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
