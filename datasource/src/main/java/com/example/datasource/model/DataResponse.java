package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/17.
 */

public class DataResponse<T> {

    @SerializedName("error")
    public int error;

    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public ArrayList<T> data;
}
