package ba.leftor.exercises.leftortest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private long timeout = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        TextView textView = (TextView)findViewById(R.id.welcome);
        textView.setOnClickListener(this);
        new Handler().postDelayed(this,timeout);
    }

    @Override
    public void onClick(View view){
        TextView textView = (TextView)findViewById(R.id.welcome);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, ToDoListActivity.class);
        startActivity(intent);

    }


    @Override
    public void run() {
        Intent intent = new Intent(this, ToDoListActivity.class);
        startActivity(intent);
    }
}
