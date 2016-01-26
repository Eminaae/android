package ba.leftor.exercises.leftortest;


import java.util.List;

import ba.leftor.exercises.leftortest.models.TaskGroup;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jasminsuljic on 26/01/16.
 */
public interface Api {

    @GET("/taskGroups")
    void getTaskGroups(Callback<BaseResponse<List<TaskGroup>>> callback);

//    @GET("/menuGroup")
//    void getTaskGroup(@Query("id") int id, Callback callback);

}
