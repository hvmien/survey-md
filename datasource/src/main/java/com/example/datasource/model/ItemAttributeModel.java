package com.example.datasource.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Forev on 17/04/26.
 */

public class ItemAttributeModel {
    @SerializedName("id")
    public int id_question_meta;
    @SerializedName("name_colume")
    public String name_column;
    @SerializedName("nam_display")
    public String name_display;
    @SerializedName("id_question")
    public String is_question;
    @SerializedName("position_in_question")
    public String position_in_question;

}
