package ba.leftor.exercises.leftortest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18.1.2016.
 */
public class Priority {

    public static final String LOW = "LOW";
    public static final String MEDIUM = "MEDIUM";
    public static final String HIGH = "HIGH";
    public static final String VERY_HIGH = "VERY HIGH";

    private Long priorityId;
    private String priorityName;

    private List<String> taskPriorityList;


    public Priority() {
    }

    public Priority(String priorityName) {
        this.priorityId = priorityId;
        this.priorityName = priorityName;
    }

    public Priority(List<String> taskPriorityList) {
        this.taskPriorityList = new ArrayList<>();
    }

    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public static List<String> createTaskPriorityList(){
        List<String> priorities = new ArrayList<>();
        priorities.add(Priority.LOW);
        priorities.add(Priority.MEDIUM);
        priorities.add(Priority.HIGH);
        priorities.add(Priority.VERY_HIGH);
        return priorities;
    }

    @Override
    public String toString(){
        return this.priorityName;
    }
}
