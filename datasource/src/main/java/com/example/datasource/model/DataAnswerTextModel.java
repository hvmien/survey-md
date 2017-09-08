package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MienHV1 on 9/8/2017.
 */

public class DataAnswerTextModel {
    public DataAnswerTextModel() {
        data = new DataAnswerText();
    }

    @SerializedName("dataanwsertext")
   public DataAnswerText data;
}
