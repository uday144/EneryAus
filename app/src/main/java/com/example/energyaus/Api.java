package com.example.energyaus;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Uday on 04/07/2019.
 */

public interface Api {

    String BASE_URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/";

    @GET("festivals")
    Call<List<EnergyAus>> getData();
}
