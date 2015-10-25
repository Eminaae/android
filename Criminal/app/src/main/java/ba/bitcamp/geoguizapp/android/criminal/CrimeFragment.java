package ba.bitcamp.geoguizapp.android.criminal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by USER on 25.10.2015.
 */
public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";

    private CrimeModel mCrime;//a member variable for the Crime instance
    private EditText mEditTitle;
    private Button mDateButton;
    private CheckBox mSolvedChkBox;

    /**
     * CrimeActivity should call CrimeFragment.newInstance(UUID) when it needs to create a
     CrimeFragment. It will pass in the UUID it retrieved from its extra. Return to CrimeActivity and, in
     createFragment(), retrieve the extra from CrimeActivity’s intent and pass it into
     CrimeFragment.newInstance(UUID).
     */
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Fragment lifecycle methods must be public because they will be called by whatever activity is hosting
     the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCrime = new CrimeModel();
       // UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        mEditTitle = (EditText)view.findViewById(R.id.crime_title);
        mEditTitle.setText(mCrime.getTitle());
        mEditTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO
            }

            /**
             * call toString() on the CharSequence that is the user’s input. This method returns a string used to set the Crime’s title.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //et a reference to the new button, set its text as the date of the crime, and disable it
        mDateButton = (Button)view.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        //CheckBox get a reference and set a listener that will update the mSolved field of the Crime
        mSolvedChkBox = (CheckBox)view.findViewById(R.id.crime_solved);
        mSolvedChkBox.setChecked(mCrime.isSolved());
        mSolvedChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO
                mCrime.setSolved(isChecked);
            }
        });
        return view;
    }


}
