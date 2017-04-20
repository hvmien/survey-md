package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class User {
    @SerializedName("username")
    public String username;

    public String password;

    @SerializedName("fullname")
    public String fullname;

    @SerializedName("deviceid")
    public String devicesid;
}
