package com.example.musicfestival.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uday on 04/07/2019.
 */

public class FestBand
{

    private String bandName;
    private String festName;
    private String recordLabel;

    public FestBand(String bandName, String recordLabel, String festName) {
        this.bandName = bandName;
        this.recordLabel = recordLabel;
        this.festName = festName;
    }

    public String getBandName() {
        return bandName;
    }
    public String getFestName() {
        return festName;
    }
    public String getRecordLabel() {
        return recordLabel;
    }

}
