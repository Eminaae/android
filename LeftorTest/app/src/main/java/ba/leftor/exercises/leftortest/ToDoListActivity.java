package ba.leftor.exercises.leftortest;

import android.animation.AnimatorSet;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ba.leftor.exercises.leftortest.fragments.AddGroupFragmentDialog;
import ba.leftor.exercises.leftortest.fragments.AddTaskFragmentDialog;
import ba.leftor.exercises.leftortest.models.Task;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by USER on 15.1.2016.
 */
public class ToDoListActivity extends AppCompatActivity implements ToDoGroupFragment.OnFragmentInteractionListener, AddTaskFragmentDialog.OnInteractionListener, AddGroupFragmentDialog.OnInteractionListener, View.OnClickListener {
    public static final String TAG_ADD_NEW_TASK = "Add a new task";
    public static final String TAG_ADD_NEW_GROUP = "Add a new task group";

    private TaskGroupsAdapter taskGroupsAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private Button newTask;
    private Button taskGroup;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new LinkedList<>();
    final Context context = this;
    private Button dialogBtn;
    private List<TaskGroup> taskGroups;
    private Button groupDialogBtn;

    private String taskPriority;
    private List<String> taskPriorityList;

    private String taskStatus;
    private List<String > taskStatusList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        newTask = (Button) findViewById(R.id.new_task);
        taskGroup = (Button) findViewById(R.id.new_group);
        dialogBtn = (Button) findViewById(R.id.task_dialog_add_task_btn);
        TodoService todoService = new TodoService();
        taskPriorityList = todoService.getTaskPriorityList();
        taskStatusList = todoService.getTaskStatusList();

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Vrati model trenutno selektirane grupe taskova
                 */
                TaskGroup group = taskGroups.get(viewPager.getCurrentItem());
                AddTaskFragmentDialog fragmentDialog = AddTaskFragmentDialog.newInstance(group, taskGroups, taskPriority, taskPriorityList,taskStatus, taskStatusList);
                fragmentDialog.show(getFragmentManager(), fragmentDialog.getClass().getSimpleName().toString());
            }
        });

        taskGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskGroup taskGroup = taskGroups.get(viewPager.getCurrentItem());
                AddGroupFragmentDialog fragmentDialog = AddGroupFragmentDialog.newInstance(taskGroup, taskGroups);
                fragmentDialog.show(getSupportFragmentManager(), "Task group");
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        taskGroups = todoService.getTaskGroups();
        sortTabs();
        taskGroupsAdapter = new TaskGroupsAdapter(getSupportFragmentManager(), taskGroups);
        viewPager.setAdapter(taskGroupsAdapter);
        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(viewPager);
        taskGroupsAdapter.notifyDataSetChanged();
        buildFAB();

    }

    private void sortTabs() {
        Collections.sort(taskGroups, new Comparator<TaskGroup>() {
            @Override
            public int compare(TaskGroup a, TaskGroup b) {
                return a.getName().toString().compareTo(b.getName().toString());
            }
        });
    }


    /**
     * Creating Floating Action Button
     */
    private void buildFAB() {
        //creating main floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //creating subbuttons
        ImageView iconGroup = new ImageView(this);
        iconGroup.setImageResource(R.drawable.add_circle);
        ImageView iconTask = new ImageView(this);
        iconTask.setImageResource(R.drawable.add_circle);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton newTaskBtn = itemBuilder.setContentView(iconGroup).build();
        SubActionButton newGroupBtn = itemBuilder.setContentView(iconTask).build();
        newTaskBtn.setTag(TAG_ADD_NEW_TASK);
        newGroupBtn.setTag(TAG_ADD_NEW_GROUP);
        newTaskBtn.setOnClickListener(this);
        newGroupBtn.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(newTaskBtn)
                .addSubActionView(newGroupBtn)
                .attachTo(fab)
                .build();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    /**
     * @param task      Task koji snimamo
     * @param taskGroup Grupa kojoj task pripada
     * @param selectedPriority
     */
    @Override
    public void save(Task task, TaskGroup taskGroup, String selectedPriority, String selectedStatus) {
        /**
         * Dodamo u task group task
         */
        taskGroup.add(task);

        /**
         * Obavijesti promjene na podacima
         */
        viewPager.setCurrentItem(taskGroups.indexOf(taskGroup), true);
        taskGroupsAdapter.notifyDataSetChanged();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Grupa koju snimamo i dodajemo na tab
     *
     * @param taskGroup Grupa kojoj task pripada
     */
    @Override
    public void save(TaskGroup taskGroup) {
        taskGroups.add(taskGroup);
        int position = taskGroups.size() - 1;
        taskGroupsAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        sortTabs();
        viewPager.setCurrentItem(position);
    }

    /**
     * On click floating action sub buttons
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getTag().equals(TAG_ADD_NEW_TASK)) {
            /**
             * Vrati model trenutno selektirane grupe taskova
             */
            TaskGroup group = taskGroups.get(viewPager.getCurrentItem());
            String priority = taskPriorityList.get(viewPager.getCurrentItem());
            String status = taskStatusList.get(viewPager.getCurrentItem());

            AddTaskFragmentDialog fragmentDialog = AddTaskFragmentDialog.newInstance(group, taskGroups, priority, taskPriorityList, status, taskStatusList);

            fragmentDialog.show(getFragmentManager(), fragmentDialog.getClass().getSimpleName().toString());
        }
        if (v.getTag().equals(TAG_ADD_NEW_GROUP)) {
            TaskGroup taskGroup = taskGroups.get(viewPager.getCurrentItem());
            AddGroupFragmentDialog fragmentDialog = AddGroupFragmentDialog.newInstance(taskGroup, taskGroups);
            fragmentDialog.show(getSupportFragmentManager(), "hh");
        }

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
