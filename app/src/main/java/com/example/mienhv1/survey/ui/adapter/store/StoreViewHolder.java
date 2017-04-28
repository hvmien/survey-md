package com.example.mienhv1.survey.ui.adapter.store;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datasource.model.StoreSystem;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewHolder;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Forev on 17/04/18.
 */

public class StoreViewHolder extends BaseViewHolder<StoreSystem> {
    ImageView ivThumb;
    TextView txtNameStore;
    CardView cvWrapContent;

    public StoreViewHolder(Context ctx, View itemView) {
        super(ctx, itemView);

    }

    @Override
    protected void bindView() {
        ivThumb = (ImageView) itemView.findViewById(R.id.iv_thumb);
        txtNameStore = (TextView) itemView.findViewById(R.id.txt_name);
        cvWrapContent = (CardView) itemView.findViewById(R.id.cv_wrap_content);
        cvWrapContent.setPreventCornerOverlap(true);
    }

    @Override
    public void bind(final int position, StoreSystem data, final RecyclerViewItemListener listener) {
        txtNameStore.setText(data.name);
        if (!TextUtils.isEmpty(data.img_thumb))
            Picasso.with(mContext).load(data.img_thumb).into(ivThumb);
        cvWrapContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }
}
