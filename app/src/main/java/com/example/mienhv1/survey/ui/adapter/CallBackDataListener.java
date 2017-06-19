package com.example.mienhv1.survey.ui.adapter;

import com.example.datasource.model.ItemQuestionModel;

/**
 * Created by HVM on 6/14/2017.
 */

public interface CallBackDataListener {
    void onItemClick(ItemQuestionModel item,int id,boolean b);
}
