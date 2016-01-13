package ba.bitcamp.bittracking.android.todo.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Emina on 8.1.2016.
 */
public class Task {
    private UUID mId;
    private String taskTitle;
    private Date mDate;
    private boolean mCompleted;
    private Category mCategory;

    /**
     * Model layer for tasks todo
     */
    public Task(){
        // Generate unique identifier
        this(UUID.randomUUID());
    }
    public Task(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
