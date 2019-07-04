package com.example.musicfestival;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.energyaus.R;
import com.example.musicfestival.model.Bands;
import com.example.musicfestival.model.MusicFestival;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.container);

        //calling the method to display the Data
        getMusicFestivals();
    }

    private void getMusicFestivals() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<MusicFestival>> call = api.getMusicFestivals();

        call.enqueue(new Callback<List<MusicFestival>>() {
            @Override
            public void onResponse(Call<List<MusicFestival>> call, Response<List<MusicFestival>> response) {
                List<MusicFestival> MusicFestivalList = response.body();

                if(MusicFestivalList == null || MusicFestivalList.size()==0){
                    showError("No Data available");
                    return;
                }
                //Creating an String array for the ListView
                String[] musicFestivalName = new String[MusicFestivalList.size()];
                List<Bands>[] bands = new List[MusicFestivalList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < MusicFestivalList.size(); i++) {
                    musicFestivalName[i] = MusicFestivalList.get(i).getName();
                    final TextView musicFestivalNameTv = new TextView(MainActivity.this);
                    musicFestivalNameTv.setTypeface(null, Typeface.BOLD);
                    musicFestivalNameTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    musicFestivalNameTv.setPadding(10, 0, 0, 10);
                    // set some properties of rowTextView or something
                    musicFestivalNameTv.setText("Festival Name: "+musicFestivalName[i]);

                    // add the textview to the linearlayout
                     container.addView(musicFestivalNameTv);
                     bands[i] = MusicFestivalList.get(i).getBands();
                     for (int j = 0; j < bands[i].size(); j++){
                         final TextView bandTv = new TextView(MainActivity.this);
                         final TextView RecordLabelTv = new TextView(MainActivity.this);
                         bandTv.setPadding(20, 5, 0, 5);
                         RecordLabelTv.setPadding(20, 5, 0, 5);
                         // set some properties of rowTextView or something
                         bandTv.setText("Band Name: "+bands[i].get(j).getName());
                         RecordLabelTv.setText("Record Label: "+bands[i].get(j).getRecordLabel());
                         container.addView(bandTv);
                         container.addView(RecordLabelTv);
                     }
                }

            }

            @Override
            public void onFailure(Call<List<MusicFestival>> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void showError(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
