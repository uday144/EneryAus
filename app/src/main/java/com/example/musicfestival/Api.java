package com.example.musicfestival;

import com.example.musicfestival.model.MusicFestival;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Uday on 04/07/2019.
 */

public interface Api {

    String BASE_URL = "http://eacodingtest.digital.energyaustralia.com.au";

    @GET("/api/v1/festivals")
    Call<List<MusicFestival>> getMusicFestivals();
}
