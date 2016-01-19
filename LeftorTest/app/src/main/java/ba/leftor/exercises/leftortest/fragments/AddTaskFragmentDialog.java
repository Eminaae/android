package ba.leftor.exercises.leftortest.fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

import ba.leftor.exercises.leftortest.R;
import ba.leftor.exercises.leftortest.models.Task;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragmentDialog extends DialogFragment {


    private TaskGroup taskGroup;
    private List<TaskGroup> taskGroups;
    private Spinner taskGroupSpinner;
    private OnInteractionListener listener;
    private Button addTaskBtn;
    private EditText description;
    private EditText name;

    public AddTaskFragmentDialog() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static AddTaskFragmentDialog newInstance(TaskGroup taskGroup, List<TaskGroup> taskGroups) {
        AddTaskFragmentDialog fragment = new AddTaskFragmentDialog();
        fragment.taskGroup = taskGroup;
        fragment.taskGroups = taskGroups;
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

        /**
         * Inicijalizira array adapter koristeci {@link taskGroups}
         * Na svakom od {@link taskGroups} itema {@link TaskGroup} pozove {@link TaskGroup#toString()}
         */
        ArrayAdapter<TaskGroup> spinnerAdapter = new ArrayAdapter<TaskGroup>(getActivity(), android.R.layout.simple_spinner_item, taskGroups);

        this.taskGroupSpinner.setAdapter(spinnerAdapter);

        /**
         * {@link Spinner#setSelection(int)} prima poziciju itema kojeg treba selektovati.
         * KOristenje {@link List#indexOf(Object)} dobijemo poziciju na kojoj se nalazi {@link taskGroup} unutar
         * {@link taskGroups}
         */
        this.taskGroupSpinner.setSelection(taskGroups.indexOf(taskGroup));

        addTaskBtn = (Button) view.findViewById(R.id.task_dialog_add_task_btn);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {

                    /**
                     * Koristeci {@link Spinner#getSelectedItem()} dohvatimo selektiranu grupu
                     */
                    TaskGroup selectedGroup = (TaskGroup) taskGroupSpinner.getSelectedItem();
//                    EditText desc = (EditText) view.findViewById(R.id.new_task_description);
                    /**
                     * Inicijalizacija novog taska, postavljanje vrijednosti
                     */
                    Task task = new Task();
                    task.setDescription(description.getText().toString());
                    task.setName(name.getText().toString());
                    task.setGroup_id(selectedGroup.getGroup_id());


                    /**
                     * Koristeci {@link listener} obavijestimo zainteresiranu aktivnost da se save desio
                     */
                    listener.save(task, selectedGroup);



                    /**
                     * Zatvorimo dijalog
                     */
                    dismiss();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {

        try {
            this.listener = (OnInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new RuntimeException("Da bi koristio AddTaskFragment moras implementirati: " + OnInteractionListener.class.getSimpleName().toString());
        }

        super.onAttach(activity);
    }

    public interface OnInteractionListener {
        /**
         * @param task      Task koji snimamo
         * @param taskGroup Grupa kojoj task pripada
         */
        void save(Task task, TaskGroup taskGroup);
    }

}
