package com.example.mienhv1.survey.ui.fragment.upload;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.ui.adapter.upload.UploadAdapter;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;

/**
 * Created by Forev on 17/04/28.
 */

public class UploadFragment extends ItemBaseSurveyFragment {

    RecyclerView rcUploads;

    public static UploadFragment newInstance(ItemQuestionModel model) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, model);
        UploadFragment fragment = new UploadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.frgament_uploads;
    }

    @Override
    protected void mapView(View view) {
        rcUploads = (RecyclerView) view.findViewById(R.id.rc_uploads);
        rcUploads.setLayoutManager(new GridLayoutManager(getContext(),3));

        UploadAdapter adapter = new UploadAdapter(getContext(), new RecyclerViewItemListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        rcUploads.setAdapter(adapter);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Uploads;
    }
}
