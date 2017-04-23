package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Forev on 17/04/23.
 */

public class CSViewPageNoScroll extends ViewPager {

    boolean allowScroll = true;

    public CSViewPageNoScroll(Context context) {
        super(context);
    }

    public CSViewPageNoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrolling(boolean isScrolling) {
        this.allowScroll = isScrolling;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.allowScroll) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.allowScroll) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }
}
