package com.example.mienhv1.survey.ui.home;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.base.BaseView;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public interface HomeView extends BaseView {
    void navigateToLoginPage();
    void getListQuestion(DataResponse<ItemQuestionModel> data);
}
