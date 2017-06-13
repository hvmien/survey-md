package com.example.mienhv1.survey.ui.adapter.upload;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.datasource.model.PickImageModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseCSAdapter;
import com.example.mienhv1.survey.utils.view.CSButton;
import com.example.mienhv1.survey.utils.view.CSTextView;

/**
 * Created by Forev on 17/04/28.
 */

public class UploadImageAdapter extends BaseCSAdapter<PickImageModel, UploadImageAdapter.UploadViewHolder> implements View.OnClickListener {


    private final Context mContext;

    public UploadImageAdapter(Context ctx) {
        super();
        mContext = ctx;

    }

    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pick_images, parent, false);
        return new UploadViewHolder(view, parent.getContext());
    }

    UploadViewHolder mHolder;

    @Override
    public void onBindViewHolder(final UploadViewHolder holder, final int position) {
        mHolder = holder;
        if (mItems != null) {
            final PickImageModel pickImageModel = mItems.get(position);
            if (pickImageModel != null) {
                holder.txtTitle.setText(position + 1 + ". " + pickImageModel.title);
                holder.cameraonly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mPosListener != null) {
                            mPosListener.onElementItemClick(holder.itemProgress,Constants.CHOOSE_OPTION_CAMERA_ONLY, position);

                        }
                        if (mListener != null) {
                            mListener.onItemClickImage(pickImageModel.title,holder.image,position);
                        }
                    }
                });
            }

        }
    }

    public void updateImage(Bitmap bitmap) {
        mHolder.image.setImageBitmap(bitmap);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pick_image_upload) {

        }
        if (view.getId() == R.id.camera_only_upload) {
        }
    }

    class UploadViewHolder extends RecyclerView.ViewHolder {
        private CSButton pickImage;
        private CSButton cameraonly;
        private CSTextView txtTitle;
        private CardView cvWrapContent;
        private ImageView image;
        private ProgressBar itemProgress;

        public UploadViewHolder(View itemView, Context context) {
            super(itemView);
            bindView();
        }

        public ImageView getImage() {
            return image;
        }

        private void bindView() {
            txtTitle = (CSTextView) itemView.findViewById(R.id.txt_title_item_upload_image);
            image = (ImageView) itemView.findViewById(R.id.item_pick_image);
            itemProgress = (ProgressBar) itemView.findViewById(R.id.item_recyc_progress_bar);
            pickImage = (CSButton) itemView.findViewById(R.id.pick_image_upload);
            cameraonly = (CSButton) itemView.findViewById(R.id.camera_only_upload);
            cvWrapContent = (CardView) itemView.findViewById(R.id.cv_wrap_content_upload);
            cvWrapContent.setPreventCornerOverlap(true);
        }
    }
}
