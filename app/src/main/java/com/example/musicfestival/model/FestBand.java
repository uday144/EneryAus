package com.example.musicfestival.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uday on 04/07/2019.
 */

public class FestBand
{

    @SerializedName("name")
    private String bandName;
    private String festName;

    public FestBand(String bandName, String festName) {
        this.bandName = bandName;
        this.festName = festName;
    }

    public String getBandName() {
        return bandName;
    }
    public String getFestName() {
        return festName;
    }

}
