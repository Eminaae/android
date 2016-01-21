package ba.leftor.exercises.leftortest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18.1.2016.
 */
public class Priority {

    private Long priorityId;
    public static final int NOT_STARTED = 0;
    public static final int IN_PROGRESS = 1;
    public static final int QUIT = 2;
    public static final int COMPLETED = 3;

    private List<Priority> taskPriorityList;


    public Priority() {
    }

    public Priority(long priorityId) {
        this.priorityId = priorityId;
    }

    public Priority(List<Priority> taskPriorityList) {
        this.taskPriorityList = new ArrayList<>();
    }

    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    public static List<Priority> createTaskPriorityList(){
        List<Priority> priorities = new ArrayList<>();

        Priority notStarted = new Priority(0);
        Priority inProgres = new Priority(1);
        Priority quit = new Priority(2);
        Priority completed = new Priority(3);

        priorities.add(completed);
        priorities.add(notStarted);
        priorities.add(inProgres);
        priorities.add(quit);
        return priorities;
    }

    @Override
    public String toString(){
        return String.valueOf(this.priorityId);
    }
}
