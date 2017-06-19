package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;

/**
 * Created by Forev on 17/04/20.
 */

public class CSRadioGroup extends RadioGroup {

    public CSRadioGroup(Context context) {
        super(context);
    }

    public CSRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(BaseViewAdapter adapter) {
        for (int i = 0; i < adapter.getCount(); i++) {

            View view = (View) adapter.getViews().get(i);
            this.addView(view);
        }
        invalidate();
    }

}
