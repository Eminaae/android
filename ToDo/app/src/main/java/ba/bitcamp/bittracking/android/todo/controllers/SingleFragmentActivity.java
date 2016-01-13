package ba.bitcamp.bittracking.android.todo.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ba.bitcamp.bittracking.android.todo.R;

/**
 * Created by Emina on 9.1.2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    /**
     * abstract method createFragment() used to instantiate the fragment. Subclasses of SingleFragmentActivity
     * will implement this method to return an instance of the fragment that the activity is hosting.
     * @return
     */
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = new ToDoFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
