package ba.bitcamp.bittracking.android.todo.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Emina on 9.1.2016.
 */
public class TaskLab {
    private static TaskLab sTaskLab;
    //list of tasks
    private List<Task> mTasks;

    public static TaskLab get(Context context){
        if(sTaskLab == null){
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
        }

    /**
     * private constructor
     * @param context
     */
    private TaskLab(Context context){
        mTasks = new ArrayList<>();
    }

    /**
     * This method will add a new task to the list of tasks
     * @param task - task to add
     */
    public void addTask(Task task){
        mTasks.add(task);
    }
    /**
     * Method that returns the List
     * @return - returns list of tasks
     */
    public List<Task> getTasks(){
        return mTasks;
    }

    /**
     * Method that returns the Task with the given ID.
     * @param id - task's id
     * @return - task with the given id
     */
    public Task getTask(UUID id){
        for(Task task : mTasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

}
