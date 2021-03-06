package ba.leftor.exercises.leftortest.fragments;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ba.leftor.exercises.leftortest.R;
import ba.leftor.exercises.leftortest.models.Task;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragmentDialog extends DialogFragment implements View.OnClickListener{

    private TaskGroup taskGroup;
    private List<TaskGroup> taskGroups;
    private Spinner taskGroupSpinner;
    private OnInteractionListener listener;
    private Button addTaskBtn;
    private Button quitBtn;
    private EditText description;
    private EditText name;
    private ArrayAdapter<TaskGroup> spinnerAdapter;

    private EditText dueDateText;
    private DatePickerDialog dueDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    private Spinner taskPrioritySpinner;
    private List<String> priorityList;
    private ArrayAdapter<String> priorityArrayAdapter;
    private String taskPriority;

    private Spinner taskStatusSpinner;
    private List<String> statusList;
    private ArrayAdapter<String> statusArrayAdapter;
    private String taskStatus;



    public AddTaskFragmentDialog() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static AddTaskFragmentDialog newInstance(TaskGroup taskGroup, List<TaskGroup> taskGroups, List<String> priorities,List<String> statusList) {
        AddTaskFragmentDialog fragment = new AddTaskFragmentDialog();
        fragment.taskGroup = taskGroup;
        fragment.taskGroups = taskGroups;
        fragment.priorityList = priorities;
        fragment.statusList = statusList;
        return fragment;
    }

    public static AddTaskFragmentDialog newInstance(TaskGroup taskGroup, List<TaskGroup> taskGroups, String priority, List<String> priorities, String status, List<String> statusList) {
        AddTaskFragmentDialog fragment = new AddTaskFragmentDialog();
        fragment.taskGroup = taskGroup;
        fragment.taskGroups = taskGroups;
        fragment.taskPriority = priority;
        fragment.priorityList = priorities;
        fragment.taskStatus = status;
        fragment.statusList = statusList;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_task_group_dialog, container, false);
        this.taskGroupSpinner = (Spinner) view.findViewById(R.id.taskGroupsSpinner);
        this.description = (EditText) view.findViewById(R.id.new_task_description);
        this.name = (EditText) view.findViewById(R.id.new_task_title);

        this.dateFormatter = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        dueDateText = (EditText) view.findViewById(R.id.due_date_edit_txt);
        dueDateText.setInputType(InputType.TYPE_NULL);
        dueDateText.requestFocus();
        setDateTimeFields();

        /**
         * Inicijalizira array adapter koristeci {@link taskGroups}
         * Na svakom od {@link taskGroups} itema {@link TaskGroup} pozove {@link TaskGroup#toString()}
         */
        spinnerAdapter = new ArrayAdapter<TaskGroup>(getActivity(), android.R.layout.simple_spinner_item, taskGroups);
        spinnerSort();
        this.taskGroupSpinner.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();
        /**
         * {@link Spinner#setSelection(int)} prima poziciju itema kojeg treba selektovati.
         * KOristenje {@link List#indexOf(Object)} dobijemo poziciju na kojoj se nalazi {@link taskGroup} unutar
         * {@link taskGroups}
         */
        this.taskGroupSpinner.setSelection(taskGroups.indexOf(taskGroup));

        this.taskPrioritySpinner = (Spinner) view.findViewById(R.id.taskPrioritySpinner);
        priorityArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, priorityList);
        this.taskPrioritySpinner.setAdapter(priorityArrayAdapter);

        this.taskStatusSpinner = (Spinner) view.findViewById(R.id.taskStatusSpinner);
        statusArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, statusList);

        this.taskStatusSpinner.setAdapter(statusArrayAdapter);




        addTaskBtn = (Button) view.findViewById(R.id.task_dialog_add_task_btn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {

                    /**
                     * Koristeci {@link Spinner#getSelectedItem()} dohvatimo selektiranu grupu
                     */
                    TaskGroup selectedGroup = (TaskGroup) taskGroupSpinner.getSelectedItem();
                    String selectedPriority = (String) taskPrioritySpinner.getSelectedItem();
                    String selectedStatus = (String) taskStatusSpinner.getSelectedItem();
//                    EditText desc = (EditText) view.findViewById(R.id.new_task_description);
                    /**
                     * Inicijalizacija novog taska, postavljanje vrijednosti
                     */
                    Task task = new Task();
                    task.setDescription(description.getText().toString());
                    task.setName(name.getText().toString());
                    task.setGroup_id(selectedGroup.getGroup_id());
                    task.setPriority(selectedPriority);
                    task.setStatus(selectedStatus);
                    task.setDate(AddTaskFragmentDialog.dateFromString(dueDateText.getText().toString(), "dd-MM-yy"));
                    /**
                     * Koristeci {@link listener} obavijestimo zainteresiranu aktivnost da se save desio
                     */
                    listener.save(task, selectedGroup, String.valueOf(selectedPriority),String.valueOf(selectedStatus));
                    /**
                     * Zatvorimo dijalog
                     */
                    dismiss();
                }
            }
        });
        quitBtn = (Button) view.findViewById(R.id.task_dialog_quit_task_btn);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    public static String dateFromString(String dateString, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
//        System.out.println("Util.dateFromString: " + dateString + ", dateFormat: " + dateFormat);
//        String convertedDate = new Date();
//        try {
//            convertedDate = simpleDateFormat.parse(dateString);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return "";
    }

    private void setDateTimeFields() {
        dueDateText.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        dueDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dueDateText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {

        if(v == dueDateText){
            dueDatePickerDialog.show();
        }
    }


    private void spinnerSort() {
        spinnerAdapter.sort(new Comparator<TaskGroup>() {
            @Override
            public int compare(TaskGroup a, TaskGroup b) {
                return a.getName().toString().compareTo(b.getName().toString());
            }
        });
    }

    public AddTaskFragmentDialog setListener(OnInteractionListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnInteractionListener {
        /**
         * @param task      Task koji snimamo
         * @param taskGroup Grupa kojoj task pripada
         * @param selectedPriority
         */
        void save(Task task, TaskGroup taskGroup, String selectedPriority, String selectedStatus);
    }

}
