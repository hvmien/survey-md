package com.example.mienhv1.survey.ui.adapter.subject;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datasource.model.Subject;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewHolder;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Forev on 17/04/17.
 */

public class SubjectViewHolder extends BaseViewHolder<Subject> {
    TextView titleSubject;
    TextView titleExpire;
    TextView titlePoint;

    ImageView ivThumb;


    public SubjectViewHolder(Context ctx, View itemView) {
        super(ctx, itemView);
        CardView cv = (CardView) itemView;
        cv.setPreventCornerOverlap(false);
    }

    @Override
    protected void bindView() {
        titleSubject = (TextView) itemView.findViewById(R.id.txt_title_subject_survey);
        titleExpire = (TextView) itemView.findViewById(R.id.title_time_expire);
        titlePoint = (TextView) itemView.findViewById(R.id.txt_point);

        ivThumb = (ImageView) itemView.findViewById(R.id.iv_survey_banner);
    }

    @Override
    public void bind(final int position, Subject data, final RecyclerViewItemListener listener) {
        titleSubject.setText(data.title);
        titleExpire.setText(data.time_expire);
        titlePoint.setText(data.point);

        if (!TextUtils.isEmpty(data.thumb_banner))
            Picasso.with(mContext).load(data.thumb_banner).into(ivThumb);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }
}
