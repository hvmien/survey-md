package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HVM on 4/25/2017.
 */

public class ProvinceModel extends AddressModel {
    @SerializedName("provinceid")
    public String provinceid;
    @SerializedName("name")
    public String name;
    @SerializedName("type")
    public String type;
}
