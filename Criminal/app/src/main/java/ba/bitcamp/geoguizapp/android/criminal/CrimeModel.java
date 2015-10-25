package ba.bitcamp.geoguizapp.android.criminal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by USER on 25.10.2015.
 */
public class CrimeModel {
    private UUID mId;
    private String mTitle;
    private Date mDate;//the date a crime occurred
    private boolean mSolved; //represents whether the crime has been solved

    public CrimeModel() {
        mId = UUID.randomUUID();//generate unique id
        mDate = new Date();//sets mDate to the current date
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
