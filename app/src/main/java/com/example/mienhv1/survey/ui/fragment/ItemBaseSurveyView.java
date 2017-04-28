package com.example.mienhv1.survey.ui.fragment;

import com.example.datasource.model.ItemAttributeModel;
import com.example.mienhv1.survey.base.BaseView;

import java.util.ArrayList;

/**
 * Created by MienHV1 on 4/28/2017.
 */

public  interface ItemBaseSurveyView extends BaseView {
    void onGetDataListenner(ArrayList<ItemAttributeModel> data);
}
