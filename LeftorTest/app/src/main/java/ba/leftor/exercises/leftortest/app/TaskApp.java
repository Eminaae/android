package ba.leftor.exercises.leftortest.app;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import ba.leftor.exercises.leftortest.TodoService;
import ba.leftor.exercises.leftortest.models.Status;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by jasminsuljic on 22/01/16.
 */
public class TaskApp extends Application {

    private static TaskApp instance;
    private final TodoService todoService;
    private List<String> taskStatuses;

    private List<TaskGroup> taskGroups;
    private List<String> taskPriorities;

    public TaskApp(){
        instance = this;
        this.todoService = new TodoService();
        taskGroups = todoService.getTaskGroups();
        this.taskStatuses = todoService.getTaskStatusList();
        this.taskPriorities = todoService.getTaskPriorityList();
    }

    public static TaskApp getInstance() {
        return instance;
    }

    public List<TaskGroup> getTaskGroups() {
        return taskGroups;
    }

    public List<String> getTaskStatuses() {
        return taskStatuses;
    }

    public List<String> getTaskPriorities() {
        return taskPriorities;
    }
}
