package ba.leftor.exercises.leftortest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

/**
 * Created by USER on 18.1.2016.
 */
public class ToDoDialogActivity extends AppCompatActivity implements ToDoDialogFragment.TaskListener, View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        TextView textView = (TextView)findViewById(R.id.new_task);
        textView.setOnClickListener(this);

    }
    @Override
    public void onFinishTaskDialog(String task) {
        Toast.makeText(this, "Task, " + task, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView)findViewById(R.id.new_task);
        Intent intent = new Intent(this, ToDoDialogFragment.class);
        startActivity(intent);
    }
}
