package ba.bitcamp.android.addperson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * PersonModel class represents the data model being displayed by the RecyclerView
 * Created by Emina on 21.10.2015.
 */
public class PersonModel {

    public String mName;
    public String mSurname;
    public Date mDate;
    public UUID mUUID;

    public PersonModel(String name, String surname) {
        mName = name;
        mSurname = surname;
        mDate = Calendar.getInstance().getTime();
        mUUID = UUID.randomUUID();
    }

    public PersonModel(){
        mUUID = UUID.randomUUID(); // to generate ID
        mDate = new Date(); //get current date and time
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public static List<PersonModel> createPersonList(int numPerson){
        List<PersonModel> persons = new ArrayList<>();

    for(int i = 0; i < numPerson; i++){
        persons.add(new PersonModel());
    }

        return persons;
    }
}
