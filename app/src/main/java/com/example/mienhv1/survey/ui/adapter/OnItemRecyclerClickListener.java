package com.example.mienhv1.survey.ui.adapter;

import android.widget.ImageView;

/**
 * Created by ThangNTX on 10/28/2016.
 */

public interface OnItemRecyclerClickListener<T> {
    void onItemClick(T t);
    void onItemClickImage(String title,ImageView t,int pos);
}
