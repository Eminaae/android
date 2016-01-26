package ba.leftor.exercises.leftortest.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 15.1.2016.
 */
public class Task {

    private String name;
    private String description;
    private String status;
    private String priority;
    private int group_id;
    private String date;

    public Task(String name, String description, String status, String priority, int group_id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.group_id = group_id;
    }

    public Task(String name, String description, String status, String priority, Date date, int group_id) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.group_id = group_id;
        this.status = status;
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static List<Task> createTaskList(int taskNum) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskNum; i++) {
            Task task = new Task("Math", "Kolokvij II", Status.IN_PROGRESS, Priority.MEDIUM, 1);
            tasks.add(task);
        }
        return tasks;
    }
}
