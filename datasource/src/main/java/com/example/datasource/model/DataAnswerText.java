package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HVM on 6/17/2017.
 */

public class DataAnswerText {
    @SerializedName("survey_pre")
    public SurveyPreModel preSurvey;

    @SerializedName("answerlist")
    public ArrayList<AnswerModel> answerModelArrayList;
}
