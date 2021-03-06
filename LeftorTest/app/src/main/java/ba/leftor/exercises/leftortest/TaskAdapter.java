package ba.leftor.exercises.leftortest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ba.leftor.exercises.leftortest.models.Priority;
import ba.leftor.exercises.leftortest.models.Task;

/**
 * Created by USER on 18.1.2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private RecyclerView mRecyclerView;
    private List<String> taskPriorityList;

    public TaskAdapter(List<Task> tasks){
        this.taskList = tasks == null ? new ArrayList<Task>() : tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewHolder = inflater.inflate(R.layout.recycler_task, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bindTaskModel(task);
    }

    @Override
    public int getItemCount() {
        return taskList == null ? 0 : taskList.size();
    }

    public void add(int position, Task task){
        taskList.add(position, task);
        notifyItemInserted(position);
    }

    public void remove(int position){
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView taskTitle;
        public TextView taskDescription;
        public TextView taskDueToDate;
        public TextView taskPriority;
        public TextView taskStatus;
        private Task mTask;

        public ViewHolder(View itemView) {
            super(itemView);
            taskTitle = (TextView)itemView.findViewById(R.id.task_title);
            taskDescription = (TextView)itemView.findViewById(R.id.task_description);
            //taskDueToDate = (TextView) itemView.findViewById(R.id.due_date_edit_txt);
            taskPriority = (TextView) itemView.findViewById(R.id.priority_txt_view);
            taskStatus = (TextView) itemView.findViewById(R.id.status_txt_view);
        }

        public void bindTaskModel(Task task){
            mTask = task;
            taskTitle.setText(mTask.getName());
            taskDescription.setText(mTask.getDescription());
            //taskDueToDate.setText(mTask.getDate().toString());

            taskStatus.setText(mTask.getPriority());
            taskPriority.setText(mTask.getStatus());
        }
    }
}
