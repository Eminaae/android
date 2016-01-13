package ba.bitcamp.bittracking.android.todo.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ba.bitcamp.bittracking.android.todo.R;
import ba.bitcamp.bittracking.android.todo.models.Task;
import ba.bitcamp.bittracking.android.todo.models.TaskLab;

/**
 * Controller
 * Created by Emina on 9.1.2016.
 */
public class ToDoListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mToDoRecyclerView;
    private TaskAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        mToDoRecyclerView = (RecyclerView)view.findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI(); //triggers a call to updateUI() to reload the list
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    /**
     * onCreateOptionsMenu method will inflate menu define in fragment_task_list
     * @param menu
     * @param inflater method with resource id and menu file, that populates the Menu instance with the items defined in file
     */
    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task_list, menu);
        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }

    }

    /**
     * updateSubtitle method sets the subtitle of the toolbar
     */
    private void updateSubtitle(){
        TaskLab taskLab = TaskLab.get(getActivity());
        int taskCount = taskLab.getTasks().size();
        String subtitle = getString(R.string.subtitle_format, taskCount); //generates the subtitle string
        if(!mSubtitleVisible){
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * onOptionsItemSelected(MenuItem) method to respond to selection of the Menu Item.
     * @param menuItem
     * @return true to indicate that no further processing is necessary
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.menu_item_new_task:
                Task task = new Task();
                TaskLab.get(getActivity()).addTask(task);
                Intent intent = ToDoPagerActivity.newIntent(getActivity(), task.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void updateUI(){
        TaskLab taskLab = TaskLab.get(getActivity());
        List<Task>tasks = taskLab.getTasks();
        if(mAdapter == null){
            mAdapter = new TaskAdapter(tasks);
            mToDoRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();

    }

    /**
     * TaskHoler Inner class in ToDoListFragment class
     */
    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mCompletedCheckBox;
        private Task mTask;

        public TaskHolder(View taskView){
            super(taskView);
            taskView.setOnClickListener(this);
            mTitleTextView = (TextView)taskView.findViewById(R.id.list_task_todo_title_text_view);
            mDateTextView = (TextView)taskView.findViewById(R.id.list_task_todo_date_text_view);
            mCompletedCheckBox = (CheckBox)taskView.findViewById(R.id.list_task_todo_completed_check_box);
        }

        /**
         * Method to update state of the task
         * @param task
         */
        public void bindTask(Task task){
            mTask = task;
            mTitleTextView.setText(mTask.getTaskTitle());
            mDateTextView.setText(mTask.getDate().toString());
            mCompletedCheckBox.setChecked(mTask.isCompleted());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mTask.getTaskTitle() + "clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(),ToDoActivity.class);
            //Intent intent = ToDoActivity.newIntent(getActivity(), mTask.getId());
            Intent intent = ToDoPagerActivity.newIntent(getActivity(), mTask.getId());
            startActivity(intent);

        }
    }

    /**
     * TaskAdapter
     */
    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> mTasks;

        public TaskAdapter(List<Task>tasks){
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_task_todo, parent, false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task); //connect TaskAdapter to TaskHolder

        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

}
