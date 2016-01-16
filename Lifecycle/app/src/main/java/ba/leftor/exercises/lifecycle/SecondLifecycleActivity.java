package ba.leftor.exercises.lifecycle;

import android.os.Bundle;

/**
 * Created by USER on 16.1.2016.
 */
public class SecondLifecycleActivity extends TracerActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.second_lifecycle);
    }
}
