package ba.leftor.exercises.leftortest.models;

/**
 * Created by USER on 15.1.2016.
 */
public class Task {

    private String name;
    private String description;
    private int status;
    private int priority;
    private int group_id;

    public Task(String name, String description, int status, int priority, int group_id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.group_id = group_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
