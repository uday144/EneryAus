package com.example.energyaus;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uday on 04/07/2019.
 */

public class EnergyAus {

    @SerializedName("name")
    private String name;


    public EnergyAus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
