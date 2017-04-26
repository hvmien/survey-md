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
 * Created by MienHV1 on 4/26/2017.
 */

public class CSGroupRadiobuttonEdittext extends LinearLayout {
    private CSRadioGroup groupRadio;
    private CSEditText csEditText;

    public CSGroupRadiobuttonEdittext(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CSGroupRadiobuttonEdittext(Context context) {

        super(context);
        this.setOrientation(VERTICAL);
        LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(LLParams);
        mapView();
    }

    private void mapView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root= inflater.inflate(R.layout.cs_item_survey_radiobutton_edittext,this,true);
        groupRadio = (CSRadioGroup) root.findViewById(R.id.cs_gr_radio);
        csEditText = (CSEditText) root.findViewById(R.id.cs_edittext_radio);
    }
    public void setAdapter(BaseViewAdapter adapter) {
        for (int i = 0; i < adapter.getCount(); i++) {

            View view = (View) adapter.getViews().get(i);
            this.addView(view);
        }
        invalidate();
    }
}
