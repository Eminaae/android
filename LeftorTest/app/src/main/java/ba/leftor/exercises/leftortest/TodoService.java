package ba.leftor.exercises.leftortest;

import java.util.List;

import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by jasminsuljic on 15/01/16.
 */
public class TodoService {

    List<TaskGroup> taskGroups;

    public TodoService() {

    }

    public List<TaskGroup> getTaskGroups() {
        if (taskGroups == null ){
            taskGroups = TaskGroup.mock();
        }
        return taskGroups;
    }
}
