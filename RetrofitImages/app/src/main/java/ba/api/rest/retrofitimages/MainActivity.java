package ba.api.rest.retrofitimages;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import ba.api.rest.retrofitimages.model.Flower;
import ba.api.rest.retrofitimages.network.Api;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ListActivity {

    List<Flower> flowerList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // listView = (ListView) findViewById(R.id.list);

        final RestAdapter restadapter = new RestAdapter.Builder()
                .setEndpoint("http://services.hanselandpetal.com")
                .build();

        Api flowerapi = restadapter.create(Api.class);

        flowerapi.getData(new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> flowers, Response response) {
                flowerList = flowers;
                Adapter adapt = new Adapter(getApplicationContext(),R.layout.item_file,flowerList);
              //  ListView listView = (ListView) findViewById(R.id.list);
                setListAdapter(adapt);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
