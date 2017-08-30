package com.example.mienhv1.survey.ui.fragment.upload;

import com.example.mienhv1.survey.base.BaseView;

/**
 * Created by HVM on 4/29/2017.
 */

public interface UploadView extends BaseView {
    void showProgressItemRecyc();
    void upload();
    void onSuccessUploadImage(String msg);
    void onErrorUploadImage(String error);
}
