package com.example.musicfestival;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.energyaus.R;
import com.example.musicfestival.model.Bands;
import com.example.musicfestival.model.FestBand;
import com.example.musicfestival.model.MusicFestival;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.*;
import java.util.stream.Collectors;

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
                List<FestBand> festBandsList = new ArrayList<>();
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
                         String rcLabel = bands[i].get(j).getRecordLabel() == null?"":bands[i].get(j).getRecordLabel();
                         festBandsList.add(new FestBand(bands[i].get(j).getName(), rcLabel, musicFestivalName[i]));
                     }
                    }
                final TextView title = new TextView(MainActivity.this);
                title.setTypeface(null, Typeface.BOLD);
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
                title.setPadding(20, 0, 0, 20);
                title.setText("=======Result=======");

                Collections.sort(festBandsList, new Comparator<FestBand>() {
                    @Override
                    public int compare(FestBand lhs, FestBand rhs) {
                        return lhs.getRecordLabel().compareTo(rhs.getRecordLabel());
                    }
                });
                String temp = "temprecord";
                container.addView(title);
                for (int i = 0; i < festBandsList.size(); i++){
                    if(!temp.equals(festBandsList.get(i).getRecordLabel())) {
                        temp = festBandsList.get(i).getRecordLabel();
                        final TextView tv = new TextView(MainActivity.this);
                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        tv.setPadding(10, 0, 0, 10);
                        // set some properties of rowTextView or something
                        tv.setText("Record Label: " + festBandsList.get(i).getRecordLabel());
                        container.addView(tv);
                    }
                    final TextView tv2 = new TextView(MainActivity.this);
                    final TextView tv3 = new TextView(MainActivity.this);
                    tv2.setPadding(20, 5, 0, 5);
                    tv3.setPadding(40, 5, 0, 5);
                    // set some properties of rowTextView or something
                    tv2.setText("Band Name: "+festBandsList.get(i).getBandName());
                    tv3.setText("Festival Name: "+festBandsList.get(i).getFestName());

                    container.addView(tv2);
                    container.addView(tv3);
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
