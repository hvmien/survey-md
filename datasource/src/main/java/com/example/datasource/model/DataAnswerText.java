package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HVM on 6/17/2017.
 */

public class DataAnswerText {
    @SerializedName("UserID")
    public int userid;

    @SerializedName("AnswerList")
    public ArrayList<AnswerModel> answerModelArrayList;
}
