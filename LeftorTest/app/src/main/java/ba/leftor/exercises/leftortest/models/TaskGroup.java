package ba.leftor.exercises.leftortest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 15.1.2016.
 */
public class TaskGroup {

    private String name;
    private int group_id;
    List<Task> mTaskList = new ArrayList<>();
    
    public List<Task> getTaskList() {
        return mTaskList;
    }

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    public TaskGroup(String name, int group_id) {
        this.name = name;
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
