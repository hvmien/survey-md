package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;

/**
 * Created by HVM on 4/23/2017.
 */

public class CSGroupCheckboxEdittext extends LinearLayout {
    private CSGroupCheckbox groupCheckbox;
    private CSEditText csEditText;

    public CSGroupCheckboxEdittext(Context context) {
        super(context);
//        this.setOrientation(VERTICAL);
//        LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        this.setLayoutParams(LLParams);
//        invalidate();
    }

    public CSGroupCheckboxEdittext(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(LLParams);
        mapView();

    }

    private void mapView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root= inflater.inflate(R.layout.cs_item_survey_checkbox_edittext,this,true);
        groupCheckbox = (CSGroupCheckbox) root.findViewById(R.id.gr_checkbox);
        csEditText = (CSEditText) root.findViewById(R.id.cs_edittext);
    }

    public void setOtherText(String text){

    }


    public void setAdapter(BaseViewAdapter adapter) {
        for (int i = 0; i < adapter.getCount(); i++) {

            View view = (View) adapter.getViews().get(i);
            this.addView(view);
        }
        invalidate();
    }
}
