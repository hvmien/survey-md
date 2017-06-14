package com.example.mienhv1.survey.ui.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class DrawerMenuFragment extends BaseFragment implements View.OnClickListener {

    public void setLogoutlayoutPress(OnCallbackDataFromNavi mListerner) {
        this.mListerner = mListerner;
    }

    private OnCallbackDataFromNavi mListerner;
    private RelativeLayout logoutlayoutClick;

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_drawer;
    }

    @Override
    protected void mapView(View view) {
        logoutlayoutClick = (RelativeLayout) view.findViewById(R.id.logout_btn_layout);
        logoutlayoutClick.setOnClickListener(this);

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.logout_btn_layout){
            mListerner.onLogoutPress();
        }
    }

   public interface OnCallbackDataFromNavi{
        void onLogoutPress();
    }
}
