package ba.bitcamp.bittracking.android.todo.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import ba.bitcamp.bittracking.android.todo.R;
import ba.bitcamp.bittracking.android.todo.models.Task;
import ba.bitcamp.bittracking.android.todo.models.TaskLab;

/**
 * ToDoPagerActivity class creates and manages the ViewPager
 * Created by Emina on 10.1.2016.
 */
public class ToDoPagerActivity extends AppCompatActivity {
    private static final String EXTRA_TASK_ID = "task_id";

    private ViewPager mViewPager;
    private List<Task> mTasks;

    public static Intent newIntent(Context packageContext, UUID taskId){
        Intent intent = new Intent(packageContext, ToDoPagerActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInastanceState){
        super.onCreate(savedInastanceState);
        setContentView(R.layout.activity_todo_pager);

        UUID taskId = (UUID)getIntent().getSerializableExtra(EXTRA_TASK_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_todo_pager_view_pager);
        mTasks = TaskLab.get(this).getTasks();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Task task = mTasks.get(position);
                return ToDoFragment.newInstance(task.getId());
            }

            @Override
            public int getCount() {
                return mTasks.size();
            }
        });

        for(int i =  0; i < mTasks.size(); i++){
            if(mTasks.get(i).getId().equals(taskId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}


