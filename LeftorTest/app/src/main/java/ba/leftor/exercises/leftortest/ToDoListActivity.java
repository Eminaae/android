package ba.leftor.exercises.leftortest;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ba.leftor.exercises.leftortest.fragments.AddTaskFragmentDialog;
import ba.leftor.exercises.leftortest.models.Task;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by USER on 15.1.2016.
 */
public class ToDoListActivity extends AppCompatActivity implements ToDoGroupFragment.OnFragmentInteractionListener, AddTaskFragmentDialog.OnInteractionListener {
    TaskGroupsAdapter mMyAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    Button newTask;
    Button taskGroup;
    TaskAdapter mTaskAdapter;
    RecyclerView mRecyclerView;
    List<Task> taskList = new LinkedList<>();
    final Context context = this;
    private Button dialogBtn;
    private List<TaskGroup> taskGroups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        newTask = (Button) findViewById(R.id.new_task);
        taskGroup = (Button) findViewById(R.id.new_group);
        dialogBtn = (Button) findViewById(R.id.task_dialog_add_task_btn);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);

                /**
                 * Vrati model trenutno selektirane grupe taskova
                 */
                TaskGroup group = taskGroups.get(mViewPager.getCurrentItem());

                AddTaskFragmentDialog fragmentDialog = AddTaskFragmentDialog.newInstance(group, taskGroups);

                fragmentDialog.show(getFragmentManager(), fragmentDialog.getClass().getSimpleName().toString());

//                dialog.setContentView(R.layout.fragment_task_group_dialog);
//                dialog.setTitle("Add new task/group");
//                dialog.show();
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TodoService todoService = new TodoService();
        taskGroups = todoService.getTaskGroups();
        mMyAdapter = new TaskGroupsAdapter(getSupportFragmentManager(), taskGroups);
        mViewPager.setAdapter(mMyAdapter);

        //mRecyclerView = (RecyclerView)findViewById(R.id.todo_recycler_view);
        // mTaskAdapter = new TaskAdapter(taskList);
        // mRecyclerView.setAdapter(mTaskAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(content, "FAB Clicked", Snackbar.LENGTH_SHORT).show();
                Toast.makeText(ToDoListActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * @param task      Task koji snimamo
     * @param taskGroup Grupa kojoj task pripada
     */
    @Override
    public void save(Task task, TaskGroup taskGroup) {
        /**
         * Dodamo u task group task
         */
        taskGroup.add(task);

        /**
         * Obavijesti promjene na podacima
         */
        mMyAdapter.notifyDataSetChanged();

        /**
         *
         */
        mViewPager.setCurrentItem(taskGroups.indexOf(taskGroup), true);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO: 19/01/16 snimiti bitne stvari
        outState.putInt("UserRATING", 3);
    }

    public class TaskGroupsAdapter extends FragmentStatePagerAdapter {

        private final List<TaskGroup> taskGroups;

        // TODO: 15/01/16 poslati podatke
        public TaskGroupsAdapter(FragmentManager fm, List<TaskGroup> taskGroups) {
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
