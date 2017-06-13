package com.example.mienhv1.survey.ui.adapter;

/**
 * Created by ThangNTX on 10/28/2016.
 */

public interface OnposItemRecyclerClickListener<T> {
    void onItemClick(T t, int position);
    void onElementItemClick(String string, int position);
}
