package com.example.energyaus.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uday on 04/07/2019.
 */

public class Bands
{

    @SerializedName("name")
    private String name;
    private String recordLabel;

    public Bands(String name, String recordLabel) {
        this.name = name;
        this.recordLabel = recordLabel;
    }

    public String getName() {
        return name;
    }
    public String getRecordLabel() {
        return recordLabel;
    }

}
