package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HVM on 4/21/2017.
 */

public class DataLoginResponse<T> {
    @SerializedName("error")
    public int error;

    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public T data;
}
