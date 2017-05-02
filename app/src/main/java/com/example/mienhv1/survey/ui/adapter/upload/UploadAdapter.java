package com.example.mienhv1.survey.ui.adapter.upload;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.PickImageModel;
import com.example.datasource.utils.Constant;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseAdapter;
import com.example.mienhv1.survey.ui.adapter.BaseViewHolder;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemElementListener;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.ui.fragment.upload.UploadFragment;
import com.example.mienhv1.survey.utils.view.CSButton;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/28.
 */

public class UploadAdapter extends BaseAdapter<PickImageModel, UploadAdapter.UploadViewHolder> implements View.OnClickListener {


    public UploadAdapter(Context ctx, RecyclerViewItemListener l) {
        super(ctx, l);

    }

    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UploadViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_pick_images, parent, false));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pick_image_upload) {

        }
        if (view.getId() == R.id.camera_only_upload) {
        }
    }

    class UploadViewHolder extends BaseViewHolder<PickImageModel> {
        CSButton pickImage;
        CSButton cameraonly;
        CSTextView txtTitle;
        CardView cvWrapContent;


        public UploadViewHolder(Context ctx, View itemView) {
            super(ctx, itemView);

        }

        @Override
        protected void bindView() {
            txtTitle = (CSTextView) itemView.findViewById(R.id.txt_title_item_upload_image);
            pickImage = (CSButton) itemView.findViewById(R.id.pick_image_upload);
            cameraonly = (CSButton) itemView.findViewById(R.id.camera_only_upload);
            cvWrapContent = (CardView) itemView.findViewById(R.id.cv_wrap_content_upload);
            cvWrapContent.setPreventCornerOverlap(true);
        }

        @Override
        public void bind(final int position, PickImageModel data, final RecyclerViewItemListener listener) {
            txtTitle.setText(position + 1 + ". " + data.title);
            cvWrapContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(position);
                }
            });

            pickImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickElement(Constants.CHOOSE_OPTION_PICK_IMAGE,position);
                }
            });
            cameraonly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickElement(Constants.CHOOSE_OPTION_CAMERA_ONLY,position);
                }
            });


        }
    }
}
