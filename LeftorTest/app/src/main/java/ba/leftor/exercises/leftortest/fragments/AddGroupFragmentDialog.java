package ba.leftor.exercises.leftortest.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import ba.leftor.exercises.leftortest.R;
import ba.leftor.exercises.leftortest.models.TaskGroup;

/**
 * Created by USER on 19.1.2016.
 */
public class AddGroupFragmentDialog extends DialogFragment {


    private TaskGroup taskGroup;
    private List<TaskGroup> taskGroups;
    private OnInteractionListener listener;
    private Button addGroupBtn;
    private Button quitBtn;
    private EditText groupName;

    public AddGroupFragmentDialog() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static AddGroupFragmentDialog newInstance(TaskGroup taskGroup, List<TaskGroup> taskGroups) {
        AddGroupFragmentDialog fragment = new AddGroupFragmentDialog();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_group_dialog, container, false);
        this.groupName = (EditText) view.findViewById(R.id.new_group_name);
        addGroupBtn = (Button) view.findViewById(R.id.group_dialog_add_group_btn);
        quitBtn = (Button) view.findViewById(R.id.group_dialog_quit_group_btn);

        addGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    /**
                     * Koristeci {@link Spinner#getSelectedItem()} dohvatimo selektiranu grupu
                     */
//                    EditText desc = (EditText) view.findViewById(R.id.new_task_description);
                    /**
                     * Inicijalizacija novog taska, postavljanje vrijednosti
                     */
                    TaskGroup taskGroup = new TaskGroup();
                    taskGroup.setName(groupName.getText().toString());
                    /**
                     * Koristeci {@link listener} obavijestimo zainteresiranu aktivnost da se save desio
                     */
                    listener.save(taskGroup);

                    /**
                     * Zatvorimo dijalog
                     */
                    dismiss();
                }
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
         * @param taskGroup Grupa kojoj task pripada
         */
        void save(TaskGroup taskGroup);
    }
}
