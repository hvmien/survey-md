package com.example.datasource.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxTextFieldModel {
   public ArrayList<CheckboxModel> mList;
   public String text;

    public CheckboxTextFieldModel(ArrayList<CheckboxModel> mList, String text) {
        this.mList = mList;
        this.text = text;
    }
}
