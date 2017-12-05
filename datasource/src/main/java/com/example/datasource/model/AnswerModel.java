package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by MienHV1 on 6/16/2017.
 */

public class AnswerModel<T> {
    @SerializedName("id_question")
    public int idQuestion;
    @SerializedName("answer_meta")
    public ArrayList<AnswerMetaModel> modelAnswerMeta;

}
