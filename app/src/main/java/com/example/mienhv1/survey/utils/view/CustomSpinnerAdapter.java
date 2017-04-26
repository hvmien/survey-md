package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HVM on 4/26/2017.
 */

public class CustomSpinnerAdapter<String> extends ArrayAdapter<String> {
    public CustomSpinnerAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public CustomSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        if (position == 0) {
            TextView tv = new TextView(getContext());
            tv.setHeight(0);
            tv.setVisibility(View.GONE);
            v = tv;
        }
        else {
            v = super.getDropDownView(position, null, parent);
        }
        parent.setVerticalScrollBarEnabled(false);
        return v;
    }
}
