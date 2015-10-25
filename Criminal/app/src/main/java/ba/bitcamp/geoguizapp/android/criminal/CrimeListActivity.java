package ba.bitcamp.geoguizapp.android.criminal;

import android.support.v4.app.Fragment;

/**
 * Created by USER on 25.10.2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
