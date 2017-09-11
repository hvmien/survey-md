package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MienHV1 on 6/16/2017.
 */

public class AnswerModel<T> {
    @SerializedName("id")
    public int idTypeQuestion;
    @SerializedName("modelQuestion")
    public T modelQuestion;

}
