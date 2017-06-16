package com.example.mienhv1.survey.ui.home;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;

public class HomeActivity extends BaseActivity implements ItemBaseSurveyFragment.OnFragmentInteractionListener{


    @Override
    protected void setScreenOrientation(boolean isTablet) {

    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void mapView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showDialogQuestionUserExitApp();
    }

    public void showDialogQuestionUserExitApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage(getResources().getString(R.string.stop_stuck_question_exit_survey));
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.super.onBackPressed();
                        finish();
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onFragmentInteraction(ItemQuestionModel model,int id) {
       // Toast.makeText(this, "radio"+id, Toast.LENGTH_SHORT).show();
    }
}
