package ba.bitcamp.android.addperson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private EditText mAddName;
    private EditText mAddSurname;
    private Button mAddButton;
    private Button mDeleteButton;
    private List<PersonModel> personsList = new LinkedList<PersonModel>();
    //Adding recycler view to Person Activity to display items
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mAddName = (EditText)findViewById(R.id.edit_name);
        mAddSurname = (EditText)findViewById(R.id.edit_surname);
        mAddButton = (Button) findViewById(R.id.add_button);
        mDeleteButton = (Button)findViewById(R.id.delete_button);
        //Lookup recycler view in activity layout
        recyclerView = (RecyclerView)findViewById(R.id.add_person_recycler_view);
        //Create adapter passing in persons list
        personAdapter = new PersonAdapter(personsList);
        recyclerView.setAdapter(personAdapter);//attach the adapter to the recyclerview to populate items
        //set Layout Manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mAddName.getText().toString();
                String surname = mAddSurname.getText().toString();
                personsList.add(new PersonModel(name, surname));
                personAdapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * PersonHolder provide a direct reference to each of the view within a data item
     * It is used to cache the views within the item Layout for fast access
     */
    private class PersonHolder extends  RecyclerView.ViewHolder{

        //Holder member variable for each view that will be set as row is rendered
        private TextView mRecyclerName;
        private TextView mRecyclerSurname;
        private TextView mRecyclerDate;
        private Button mDeleteButton;
        private PersonModel mRecyclerPerson;

        /**
         * PersonHolder constructor that accepts entire item row
         * @param itemView
         */
        public PersonHolder(View itemView) {
            //stores the itemView in a public final member variable that can be used to acces the context from any PersonHolder instance
            super(itemView);
            mRecyclerName = (TextView)itemView.findViewById(R.id.recycler_name);
            mRecyclerSurname = (TextView)itemView.findViewById(R.id.recycler_surname);
            mRecyclerDate = (TextView)itemView.findViewById(R.id.recycler_date);
            mDeleteButton = (Button)itemView.findViewById(R.id.delete_button);


            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    personsList.remove(mRecyclerPerson);
                    update();
                }
            });
            if(mDeleteButton == null){
                Log.d("person adding", "null deleting");
            }else{
                Log.d("person adding", "not null deleting");
            }
        }

        public void update(){
            List<PersonModel> list = personsList;
            personAdapter = new PersonAdapter(list);
            recyclerView.setAdapter(personAdapter);
        }

        public void bindPersonModel(PersonModel personModel){
            mRecyclerPerson = personModel;
            //Setting item views based on person model
            mRecyclerName.setText(mRecyclerPerson.getName());
            mRecyclerSurname.setText(mRecyclerPerson.getSurname());
            mRecyclerDate.setText(mRecyclerPerson.getDate().toString());
        }
    }

    /**
     *PersonAdapter class extends RecyclerView.Adapter used to handle data collection and bind it to the view
     * PersonHolder is specified to give us access to the views
     */
    private class PersonAdapter extends RecyclerView.Adapter<PersonHolder>{

        //Store a member variable
        private List<PersonModel> mPersonModelList;

       public PersonAdapter(List<PersonModel> personModelList){
           mPersonModelList = personModelList;
       }
        //Involves inflation a layout from xml file and returns holder
        @Override
        public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(PersonActivity.this);
            View holderView = inflater.inflate(R.layout.recycler_person, parent, false); //inflate the custom Layout
            return new PersonHolder(holderView);//returns a new PersonHolder instance
        }

        //Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(PersonHolder holder, int position) {
            //get person model based on position
            PersonModel personModel = mPersonModelList.get(position);
            holder.bindPersonModel(personModel);
        }

        @Override
        public int getItemCount() {
            return mPersonModelList.size();//returns total count of items
        }
    }
}
