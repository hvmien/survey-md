package com.example.mienhv1.survey.ui.adapter;

import android.widget.ProgressBar;

/**
 * Created by ThangNTX on 10/28/2016.
 */

public interface OnposItemRecyclerClickListener<T> {
    void onItemClick(T t, int position);
    void onElementItemClick(ProgressBar progress, String string, int position);
}
