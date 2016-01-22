package ba.leftor.exercises.leftortest;

import java.util.List;

import ba.leftor.exercises.leftortest.models.Priority;
import ba.leftor.exercises.leftortest.models.Status;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by jasminsuljic on 15/01/16.
 */
public class TodoService {

    List<TaskGroup> taskGroups;
    List<String> taskPriorityList;
    List<String> taskStatusList;

    public TodoService() {

    }

    public List<TaskGroup> getTaskGroups() {
        if (taskGroups == null ){
            taskGroups = TaskGroup.mock();
        }
        return taskGroups;
    }

    public List<String> getTaskPriorityList(){
        if(taskPriorityList == null){
            taskPriorityList = Priority.createTaskPriorityList();
        }
        return taskPriorityList;
    }

    public List<String> getTaskStatusList(){
        if(taskStatusList == null){
            taskStatusList = Status.createTaskStatusList();
        }
        return taskStatusList;
    }
}
