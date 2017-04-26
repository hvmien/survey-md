package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class SurveyTableModel {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("title")
    public String title;
    @SerializedName("type")
    public int type;
}
