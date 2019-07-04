package com.example.energyaus.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Uday on 04/07/2019.
 */

public class EnergyAus {

    @SerializedName("name")
    private String name;
    private List<Bands> bands;


    public EnergyAus(String name, List<Bands> bands) {
        this.name = name;
        this.bands = bands;
    }

    public String getName() {
        return name;
    }
    public List<Bands> getBands() {
        return bands;
    }

}
