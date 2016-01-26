package ba.leftor.exercises.leftortest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ba.leftor.exercises.leftortest.models.Priority;
import ba.leftor.exercises.leftortest.models.Status;
import ba.leftor.exercises.leftortest.models.TaskGroup;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by jasminsuljic on 15/01/16.
 */
public class TodoService {

    private final Api api;
    List<TaskGroup> taskGroups;
    List<String> taskPriorityList;
    List<String> taskStatusList;

    public TodoService() {
        String url = "http://192.168.0.109:4000";
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(120, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder().create(); //;.setExclusionStrategies(new AnnotationExclusionStrategy()).create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson)) //xml converter
//                .setErrorHandler(new ApiErrorHandler())
//                .setRequestInterceptor(requestInterceptor)
//                .setLogLevel(Deplast.isDebug ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.BASIC)
                .build();

        api = restAdapter.create(Api.class);
    }

    public Api getApi() {
        return api;
    }

    public List<TaskGroup> getTaskGroups() {
        if (taskGroups == null) {
            taskGroups = TaskGroup.mock();
        }
        return taskGroups;
    }

    public List<String> getTaskPriorityList() {
        if (taskPriorityList == null) {
            taskPriorityList = Priority.createTaskPriorityList();
        }
        return taskPriorityList;
    }

    public List<String> getTaskStatusList() {
        if (taskStatusList == null) {
            taskStatusList = Status.createTaskStatusList();
        }
        return taskStatusList;
    }
}
