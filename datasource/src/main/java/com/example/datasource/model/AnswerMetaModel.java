package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HVM on 9/12/2017.
 */

public class AnswerMetaModel<T> {
    @SerializedName("id_question_meta")
    public int idQuestionMeta;
    @SerializedName("answer_value")
    public T answerValue;
}
