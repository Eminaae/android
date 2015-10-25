package ba.bitcamp.geoguizapp.android.criminal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by USER on 25.10.2015.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab; //"s" stands for static variable
    private List<CrimeModel> mCrimeList;

    //If the instance already exists, then get() simply returns the instance. If the instance does not exist yet, then get()
    //will call the constructor to create it.
    public static CrimeLab get(Context context) {
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimeList = new ArrayList<>();
        for (int i = 0; i < mCrimeList.size(); i++){
            CrimeModel crimeModel = new CrimeModel();
            crimeModel.setTitle("Crime #" + i);
            crimeModel.setSolved(i%2 == 0);//every other crime is solved
            mCrimeList.add(crimeModel);
        }
    }

    public List<CrimeModel> getCrimeList() {
        return mCrimeList;
    }

    //getCrime(UUID) returns the Crime with the given ID
    public CrimeModel getCrime(UUID id){
        for(CrimeModel crimeModel : mCrimeList){
            if(crimeModel.getId().equals(id)){
                return crimeModel;
            }
        }
        return null;
    }
}
