package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Forev on 17/04/18.
 */

public class StoreSystem {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name = "VinMart";
    @SerializedName("title")
    public String titile;
    @SerializedName("thumb")
    public String img_thumb;
}
