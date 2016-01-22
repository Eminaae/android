package ba.leftor.exercises.leftortest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18.1.2016.
 */
public class Status {

    public static final String NOT_STARTED = "NOT STARTED";
    public static final String IN_PROGRESS = "IN PROGRES";
    public static final String QUIT = "QUIT";
    public static final String COMPLETED = "COMPLETED";

    private long statusId;
    private String statusName;

    private List<String> taskStatusList;

    public Status(long statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Status(long statusId) {
        this.statusId = statusId;
    }

    public Status() {
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public static List<String> createTaskStatusList(){
        List<String> taskStatusList = new ArrayList<>();
        taskStatusList.add(Status.NOT_STARTED);
        taskStatusList.add(Status.IN_PROGRESS);
        taskStatusList.add(Status.COMPLETED);
        taskStatusList.add(Status.QUIT);
        return taskStatusList;
    }

    @Override
    public String toString() {
        return this.statusName;
    }


}
