package ba.leftor.exercises.leftortest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by USER on 15.1.2016.
 */
public class ToDoListActivity extends AppCompatActivity implements ToDoGroupFragment.OnFragmentInteractionListener {
    MyAdapter mMyAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabs);

        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mMyAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyAdapter);
        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ToDoGroupFragment.newInstance("22","333");
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "fragment" + position;
        }
    }

}
