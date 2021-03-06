package ba.leftor.exercises.leftortest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ba.leftor.exercises.leftortest.app.TaskApp;
import ba.leftor.exercises.leftortest.fragments.AddGroupFragmentDialog;
import ba.leftor.exercises.leftortest.fragments.AddTaskFragmentDialog;
import ba.leftor.exercises.leftortest.models.Priority;
import ba.leftor.exercises.leftortest.models.Task;
import ba.leftor.exercises.leftortest.models.TaskGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToDoGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToDoGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoGroupFragment extends Fragment implements AddTaskFragmentDialog.OnInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TaskGroup taskGroup;
    private Task task;
    private RecyclerView mRecyclerView;
    public List<Task> taskList;

    private TextView newTask;
    private TextView newTaskGroup;


    private Priority taskPriority;
    private Button taskGroupBtn;
    private TaskAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToDoGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDoGroupFragment newInstance(String param1, String param2) {
        ToDoGroupFragment fragment = new ToDoGroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ToDoGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do_group, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.todo_recycler_view);
        /**
         * Za konkretnu {@link TaskGroup} dohvatamo {@link TaskGroup#getTaskList()} listu taskova
         */
        this.adapter = new TaskAdapter(this.taskGroup.getTaskList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

        newTask = (Button) view.findViewById(R.id.new_task);
        taskGroupBtn = (Button) view.findViewById(R.id.new_group);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Vrati model trenutno selektirane grupe taskova
                 */

                TaskApp app = TaskApp.getInstance();

                AddTaskFragmentDialog fragmentDialog = AddTaskFragmentDialog.newInstance(taskGroup, app.getTaskGroups(), app.getTaskPriorities(), app.getTaskStatuses());
                fragmentDialog.setListener(ToDoGroupFragment.this);
                fragmentDialog.show(getActivity().getFragmentManager(), fragmentDialog.getClass().getSimpleName().toString());
            }
        });

        taskGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAddTaskGroup();
                }
            }
        });

//        mRecyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Kreira fragment i dodijeli task group.
     *
     * @param taskGroup
     * @return
     */
    public static Fragment newInstance(TaskGroup taskGroup, Priority taskPriority) {
        ToDoGroupFragment fragment = new ToDoGroupFragment();
        fragment.taskGroup = taskGroup;
        fragment.taskPriority = taskPriority;
        return fragment;
    }

    public static Fragment newInstance(TaskGroup taskGroup) {
        ToDoGroupFragment fragment = new ToDoGroupFragment();
        fragment.taskGroup = taskGroup;
        return fragment;
    }

    public static Fragment newInstance(Priority taskPriority) {
        ToDoGroupFragment fragment = new ToDoGroupFragment();
        fragment.taskPriority = taskPriority;
        return fragment;
    }

    @Override
    public void save(Task task, TaskGroup taskGroup, String selectedPriority, String selectedStatus) {
        /**
         * Dodamo u task group task
         */
        taskGroup.add(task);
        this.adapter.notifyItemInserted(taskGroup.getTaskList().indexOf(task));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);

        void onAddTaskGroup();
    }

}
