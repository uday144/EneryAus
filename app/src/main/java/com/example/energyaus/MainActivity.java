package com.example.energyaus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.energyaus.model.EnergyAus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewHeroes);

        //calling the method to display the heroes
        getHeroes();
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<EnergyAus>> call = api.getData();

        call.enqueue(new Callback<List<EnergyAus>>() {
            @Override
            public void onResponse(Call<List<EnergyAus>> call, Response<List<EnergyAus>> response) {
                List<EnergyAus> dataList = response.body();

                //Creating an String array for the ListView
                String[] data = new String[dataList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < dataList.size(); i++) {
                    if(dataList.get(i).getName()!=null){
                        data[i] = dataList.get(i).getName();
                    }
                }
              //  Toast.makeText(getApplicationContext(), heroes.length, Toast.LENGTH_SHORT).show();

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data));

            }

            @Override
            public void onFailure(Call<List<EnergyAus>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
