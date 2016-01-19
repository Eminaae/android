package ba.leftor.exercises.leftortest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ba.leftor.exercises.leftortest.models.Priority;
import ba.leftor.exercises.leftortest.models.Status;
import ba.leftor.exercises.leftortest.models.Task;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by USER on 15.1.2016.
 */
public class ToDoListActivity extends AppCompatActivity implements ToDoGroupFragment.OnFragmentInteractionListener {
    MyAdapter mMyAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    TextView newTask;
    TextView taskGroup;
    TaskAdapter mTaskAdapter;
    RecyclerView mRecyclerView;
    List<Task>taskList = new LinkedList<>();
    final Context context = this;
    private Button dialogBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        newTask = (TextView)findViewById(R.id.new_task);
        taskGroup = (TextView)findViewById(R.id.new_group);
        dialogBtn = (Button)findViewById(R.id.save_btn_id);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.fragment_task_group_dialog);
                dialog.setTitle("Add new task/group");
                dialog.show();
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TodoService todoService = new TodoService();
        mMyAdapter = new MyAdapter(getSupportFragmentManager(), todoService.getTaskGroups());
        mViewPager.setAdapter(mMyAdapter);

        //mRecyclerView = (RecyclerView)findViewById(R.id.todo_recycler_view);
       // mTaskAdapter = new TaskAdapter(taskList);
       // mRecyclerView.setAdapter(mTaskAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyAdapter extends FragmentStatePagerAdapter {

        private final List<TaskGroup> taskGroups;

        // TODO: 15/01/16 poslati podatke
        public MyAdapter(FragmentManager fm, List<TaskGroup> taskGroups) {
            super(fm);
            this.taskGroups = taskGroups == null ? new ArrayList<TaskGroup>() : taskGroups;
        }

        @Override
        public Fragment getItem(int position) {
            return ToDoGroupFragment.newInstance(taskGroups.get(position));
        }

        @Override
        public int getCount() {
            return taskGroups == null ? 0 : taskGroups.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return taskGroups.get(position).getName();
        }
    }

}
