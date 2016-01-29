package ba.api.rest.retrofitimages.network;
import android.test.suitebuilder.annotation.LargeTest;


import java.util.List;

import ba.api.rest.retrofitimages.model.Flower;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Gokul Balakrishnan on 4/4/2015.
 */
public interface Api {

    @GET("/feeds/flowers.json")
    public void getData(Callback<List<Flower>> response);


}
