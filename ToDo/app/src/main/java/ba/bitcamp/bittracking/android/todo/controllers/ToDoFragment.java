package ba.bitcamp.bittracking.android.todo.controllers;

/**
 * Created by Emina on 8.1.2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import ba.bitcamp.bittracking.android.todo.R;
import ba.bitcamp.bittracking.android.todo.models.Task;
import ba.bitcamp.bittracking.android.todo.models.TaskLab;

/**
 *Controller that interacts with Task Model and view objects. Its job is to present the
 details of a specific task and update those details as the user changes them.
 */
public class ToDoFragment extends Fragment{

    private static final String ARG_TASK_ID = "task_id";
    private static final String DIALOG_DATE = "dialog_date";
    private static final int REQUEST_DATE = 0;

    private Task mTask;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mCompletedCheckBox;

    /**
     * newInstance method creates an arguments bundle, creates fragment instance and than attaches arguments to the fragment
     * @param taskId - uuid
     * @return fragment
     */
    public static ToDoFragment newInstance(UUID taskId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);
        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID)getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskLab.get(getActivity()).getTask(taskId);
    }


    /**
     * This method will inflate layout for the fragments view and return the inflated view to the hosting activity.
     * @param inflater - parameter necessary to inflate the layout
     * @param container - needed to cnfigure the widgets properly
     * @param savedInstanceState - contains data that this method can use to recreate the view from a saved state
     * @return inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //explicitly inflate the fragment’s view by calling LayoutInflater.inflate(…) and passing in the layout resource ID.
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        mTitleField = (EditText)view.findViewById(R.id.todo_title);
        mTitleField.setText(mTask.getTaskTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * In onTextChanged(…), we call toString() on the CharSequence that is the user’s input. This method
             returns a string, which we then use to set the Task’s title.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setTaskTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton =(Button)view.findViewById(R.id.task_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mTask.getDate());
                dialog.setTargetFragment(ToDoFragment.this, REQUEST_DATE);
                dialog.show(fragmentManager, DIALOG_DATE);
            }
        });

        mCompletedCheckBox = (CheckBox)view.findViewById(R.id.task_completed);
        mCompletedCheckBox.setChecked(mTask.isCompleted());
        mCompletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setCompleted(isChecked);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTask.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mTask.getDate().toString());
    }

    private String getTaskReport(){
        String completedString = null;
        if(mTask.isCompleted()){
            completedString = getString(R.string.task_report_completed);
        }else{
            completedString = getString(R.string.task_report_uncompleted);
        }
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat,mTask.getDate()).toString();

        String report = getString(R.string.task_report, mTask.getTaskTitle(), dateString, completedString,null);
        return report;
    }
}
