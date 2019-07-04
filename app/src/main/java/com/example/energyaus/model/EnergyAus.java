package com.example.energyaus.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uday on 04/07/2019.
 */

public class EnergyAus {

    @SerializedName("name")
    private String name;
    private Bands bands[];


    public EnergyAus(String name, Bands bands[]) {
        this.name = name;
        this.bands = bands;
    }

    public String getName() {
        return name;
    }
    public Bands[] getBands() {
        return bands;
    }

}
