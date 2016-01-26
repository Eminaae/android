package ba.leftor.exercises.leftortest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 15.1.2016.
 */
public class TaskGroup {

    private String name;
    private int group_id;

    List<Task> tasks = new ArrayList<>();

    public List<Task> getTaskList() {
        return tasks;
    }

    public void setTaskList(List<Task> taskList) {
        tasks = taskList;
    }

    public TaskGroup(String name, int group_id) {
        this.name = name;
        this.group_id = group_id;
        this.tasks = new ArrayList<>();
    }

    public TaskGroup() {
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
        tasks.add(new Task("Usisati", "Iza kauca", Status.COMPLETED, Priority.LOW, kuca.getGroup_id()));
        tasks.add(new Task("22", "Iza kauca", Status.IN_PROGRESS, Priority.HIGH, kuca.getGroup_id()));
        tasks.add(new Task("Us22i3232323sati", "Iza kauca", Status.QUIT, Priority.MEDIUM, kuca.getGroup_id()));
        tasks.add(new Task("Usis34ati", "Iza kauca", Status.QUIT, Priority.MEDIUM, kuca.getGroup_id()));

        kuca.add(tasks);

        TaskGroup fakultet = new TaskGroup("Fakultet", 1);
        tasks.add(new Task("Math", "Kolokvij II", Status.QUIT, Priority.VERY_HIGH, fakultet.getGroup_id()));
        tasks.add(new Task("Fizika", "Kolokvij II", Status.IN_PROGRESS, Priority.MEDIUM, fakultet.group_id));
        fakultet.add(tasks);
        TaskGroup posao = new TaskGroup("Posao", 2);
        TaskGroup birtija = new TaskGroup("Birtija", 3);
        TaskGroup trening = new TaskGroup("Trening", 4);

        groups.add(kuca);
        groups.add(fakultet);
        groups.add(posao);
        groups.add(birtija);
        groups.add(trening);
        return groups;
    }

    private void addAll(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void add(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public static List<TaskGroup> createTaskGroup(int groupNum) {
        List<TaskGroup> taskGroups = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            taskGroups.add(new TaskGroup());
        }
        return taskGroups;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
