package com.example.mienhv1.survey.ui.adapter.subject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.Subject;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseAdapter;
import com.example.mienhv1.survey.ui.adapter.BaseViewHolder;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;

import java.util.zip.Inflater;

/**
 * Created by Forev on 17/04/17.
 */

public class SubjectAdapter extends BaseAdapter<Subject,SubjectViewHolder> {
    public SubjectAdapter(Context ctx, RecyclerViewItemListener l) {
        super(ctx, l);
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_subject_survey_view, parent, false);
        return new SubjectViewHolder(mContext, view);
    }
}
