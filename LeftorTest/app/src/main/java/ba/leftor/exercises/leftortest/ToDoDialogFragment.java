package ba.leftor.exercises.leftortest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by USER on 18.1.2016.
 */
public class ToDoDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText editTask;
    private EditText editDescription;
    private EditText editGroup;

    public ToDoDialogFragment() {
    }

    public interface TaskListener{
        void onFinishTaskDialog(String task);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.fragment_task_group_dialog, container);
        editTask.setOnEditorActionListener(this);
        editTask.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter new task/group");
        editDescription.setOnEditorActionListener(this);
        editGroup.setOnEditorActionListener(this);
        return view;
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        TaskListener activity = (TaskListener)getActivity();
        activity.onFinishTaskDialog(editTask.getText().toString());
        this.dismiss();
        return true;
    }
}
