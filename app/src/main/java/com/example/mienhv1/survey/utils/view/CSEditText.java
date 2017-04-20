package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.utils.font.FontCache;


/**
 * Created by Forev on 9/9/2016.
 */
public class CSEditText extends android.support.v7.widget.AppCompatEditText {
    TouchDrawableEvent touchDrawableEvent;

    private void applyFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CSFontView
        );

        String fontName = attributeArray.getString(R.styleable.CSFontView_font);
        Typeface typeface = FontCache.getTypeface(fontName, context);
        setTypeface(typeface);

        attributeArray.recycle();
    }

    public CSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            applyFont(context, attrs);
        }

        setOnTouchListener(touchListener);
    }

    public CSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            applyFont(context, attrs);
        }
        setOnTouchListener(touchListener);
    }

    OnTouchListener touchListener = new OnTouchListener() {
        public int mAction = -1;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mAction == event.getAction())
                return false;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mAction = event.getAction();

                if (event.getRawX() >= CSEditText.this.getRight() - CSEditText.this.getTotalPaddingRight()) {
                    if (touchDrawableEvent != null)
                        touchDrawableEvent.onRightTouch();
                    return true;
                }
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                mAction = event.getAction();
                return false;
            }
            return false;
        }
    };


    public void setTouchDrawableEvent(TouchDrawableEvent touchDrawableEvent) {
        this.touchDrawableEvent = touchDrawableEvent;
    }

    public interface TouchDrawableEvent {
        void onRightTouch();
    }
}
