package com.example.mienhv1.survey.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * findViewById without cast type
     * @param id        id view need to find
     * @return          View with generic type
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(int id){
        return (T)getActivity().findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getResourcesLayout(), container, false);
    }

    protected abstract int getResourcesLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView(view);
        initData();
    }
    protected abstract void mapView(View view);
    protected abstract void initData();
    protected abstract void destroyView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
    }

    protected void showFooterMessage(View parentView, String message) {
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).show();
    }
}
