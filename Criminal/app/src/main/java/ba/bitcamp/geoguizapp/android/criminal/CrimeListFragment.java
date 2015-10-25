package ba.bitcamp.geoguizapp.android.criminal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by USER on 25.10.2015.
 */
public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//RecyclerView requires a LayoutManager to work.
        updateUI();
        return view;
    }

    //to update the RecyclerView
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<CrimeModel> crimes = crimeLab.getCrimeList();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Inner class Crime Holder maintains a reference to a single view: the Title TextView
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CrimeModel mCrimeModel;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        /**
         * When given a Crime, CrimeHolder will now update the title TextView, date TextView, and solved
         CheckBox to reflect the state of the Crime
         * @param crime
         */
        public void bindCrime(CrimeModel crime) {
            mCrimeModel = crime;
            mTitleTextView.setText(mCrimeModel.getTitle());
            mDateTextView.setText(mCrimeModel.getDate().toString());
            mSolvedCheckBox.setChecked(mCrimeModel.isSolved());
        }


        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mCrimeModel.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(),CrimeActivity.class);//Here CrimeListFragment creates an explicit intent that names the CrimeActivity class.
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrimeModel.getId());
            startActivity(intent);
        }
    }


    /**
     * The RecyclerView will communicate with this adapter when a ViewHolder needs to be created or connected with a Crime object.
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<CrimeModel> mCrimesList;

        public CrimeAdapter(List<CrimeModel> crimesList){
            mCrimesList = crimesList;
        }


        /**
         * onCreateViewHolder is called by the RecyclerView when it needs a new View to display an item. In
         this method, you create the View and wrap it in a ViewHolder.
         For the View, you inflate a layout from the Android standard library called simple_list_item_1.
         */
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        /**
         * This method will bind a ViewHolder’s View to your model object. It receives
         the ViewHolder and a position in your data set. To bind your View, you use that position to find the
         right model data. Then you update the View to reflect that model data.
         */
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            CrimeModel crimeModel = mCrimesList.get(position);
            holder.bindCrime(crimeModel);

        }

        @Override
        public int getItemCount() {
            return mCrimesList.size();
        }
    }


}
