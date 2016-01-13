package ba.bitcamp.bittracking.android.todo.controllers;

import android.support.v4.app.Fragment;

/**
 * Controller
 * Created by Emina on 9.1.2016.
 */
public class ToDoListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ToDoListFragment();
    }
}
