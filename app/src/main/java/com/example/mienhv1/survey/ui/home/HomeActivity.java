package com.example.mienhv1.survey.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.store.StoreActivity;
import com.example.mienhv1.survey.utils.Utils;

public class HomeActivity extends BaseActivity implements ItemBaseSurveyFragment.OnFragmentInteractionListener,HomeFragment.OnCallbackEndSurvey{

    public static final String ID_TOPIC = "IDTOPIC";
    public static int ID;

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
        Log.d("mien123","home activity");
        Bundle extras = getIntent().getExtras();
        int id_topic=  extras.getInt(ID_TOPIC);
        Toast.makeText(this, id_topic+"", Toast.LENGTH_SHORT).show();
        ID= id_topic;
        Bundle bundle = new Bundle();
        bundle.putInt("idtopic", id_topic);
// set Fragmentclass Arguments
        HomeFragment fragobj = new HomeFragment();
        fragobj.setArguments(bundle);
        Utils.replaceFragmentToActivity(getSupportFragmentManager(), fragobj,
                R.id.fragment_home, HomeFragment.TAG);
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
                        //HomeActivity.super.onBackPressed();
                        onCallbacEndSurvey();
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

    @Override
    public void onCallbacEndSurvey() {
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
