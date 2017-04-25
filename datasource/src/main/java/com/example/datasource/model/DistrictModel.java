package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HVM on 4/25/2017.
 */

public class DistrictModel  extends AddressModel{

    @SerializedName("districtid")
    public String districtid;
    @SerializedName("name")
    public String name;
    @SerializedName("type")
    public String type;
    @SerializedName("location")
    public String location;
    @SerializedName("provinceid")
    public int provinceid;
}
