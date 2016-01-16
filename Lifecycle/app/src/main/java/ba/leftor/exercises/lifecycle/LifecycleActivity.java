package ba.leftor.exercises.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LifecycleActivity extends TracerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, SecondLifecycleActivity.class);
        startActivity(intent);
    }



}
