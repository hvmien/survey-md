package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;

/**
 * Created by Forev on 17/04/23.
 */

public class CSGroupCheckbox extends LinearLayout {

    public CSGroupCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CSGroupCheckbox(Context context) {
        super(context);

        this.setOrientation(VERTICAL);
        LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(LLParams);
        invalidate();
    }

    public void setAdapter(BaseViewAdapter adapter) {
        for (int i = 0; i < adapter.getCount(); i++) {

            View view = (View) adapter.getViews().get(i);
            this.addView(view);
        }
        invalidate();
    }
}
