package com.example.datasource.model;

import java.util.ArrayList;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class RadioButtonTexFieldModel {
    public ArrayList<RadiobuttonModel> mList;
    public String text;

    public RadioButtonTexFieldModel(ArrayList<RadiobuttonModel> mList, String text) {
        this.mList = mList;
        this.text = text;
    }
}
