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
        this.mTaskList = new ArrayList<>();
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

    public static List<TaskGroup> mock() {
        List<TaskGroup> groups = new ArrayList<>();

        TaskGroup kuca = new TaskGroup("Kuca", 0);

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Usisati","Iza kauca",1,0,kuca.getGroup_id()));
        tasks.add(new Task("Usisati","Iza kauca",1,0,kuca.getGroup_id()));
        tasks.add(new Task("Usisati","Iza kauca",1,0,kuca.getGroup_id()));
        tasks.add(new Task("Usisati","Iza kauca",1,0,kuca.getGroup_id()));

        kuca.add(tasks);


        TaskGroup fakultet = new TaskGroup("Fakultet", 1);



        TaskGroup posao = new TaskGroup("Posao", 2);
        TaskGroup birtija = new TaskGroup("Birtija", 3);
        groups.add(kuca);
        groups.add(fakultet);
        groups.add(posao);
        groups.add(birtija);
        return groups;
    }

    private void addAll(List<Task> tasks) {
        this.mTaskList.addAll(tasks);
    }

    private void add(List<Task> tasks){
        this.mTaskList.addAll(tasks);
    }

    private void add(Task task){
        this.mTaskList.add(task);
    }
}
