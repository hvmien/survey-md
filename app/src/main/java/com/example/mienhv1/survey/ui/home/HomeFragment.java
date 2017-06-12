package com.example.mienhv1.survey.ui.home;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.SurveyPagerAdapter;
import com.example.mienhv1.survey.utils.view.CSTextView;
import com.example.mienhv1.survey.utils.view.CSViewPageNoScroll;

import java.util.ArrayList;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {

    private HomePresenter mHomePresenter;

    private ProgressBar mProgressbar;
    private CSViewPageNoScroll mViewPager;
    View viewBtnNext;
    View viewBtnPre;

    int curChildPosition = 0;

    CSTextView txtCurPage;
    private ArrayList mListQuestion = new ArrayList();

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void mapView(View view) {
        mProgressbar = (ProgressBar) view.findViewById(R.id.home_progress_bar);
        mViewPager = (CSViewPageNoScroll) view.findViewById(R.id.viewpager);
        viewBtnNext=view.findViewById(R.id.btn_next);
        viewBtnPre=view.findViewById(R.id.btn_prev);
        view.findViewById(R.id.btn_prev).setOnClickListener(this);
        view.findViewById(R.id.btn_next).setOnClickListener(this);
        txtCurPage = (CSTextView) view.findViewById(R.id.txt_cur_page);
    }

    @Override
    protected void initData() {
        mViewPager.setScrolling(false);
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
        mHomePresenter = new HomePresenter(dataRepository, this);

        mHomePresenter.createDatabase();
        if(curChildPosition + 1==1)
        {
            viewBtnPre.setVisibility(View.GONE);
        }

        //add viewpager this here
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void showProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLoginPage() {

    }

    @Override
    public void getListQuestion(DataResponse<ItemQuestionModel> datamodel) {
        mListQuestion = new ArrayList();
        if(datamodel.data!=null) {
            mListQuestion.addAll(datamodel.data);
            SurveyPagerAdapter adapter = new SurveyPagerAdapter(this.getChildFragmentManager(), mListQuestion);
            mViewPager.setAdapter(adapter);
            mViewPager.setOffscreenPageLimit(mListQuestion.size());
            txtCurPage.setText(1 + "/" + mListQuestion.size());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_next:

                if (curChildPosition + 1 >= mListQuestion.size())
                    return;

                curChildPosition++;
//                mViewPager.setCurrentItem(getItem(+1), true);
                mViewPager.setCurrentItem(mViewPager.getAdapter().getCount()-1,true);
                txtCurPage.setText(curChildPosition + 1 + "/" + mListQuestion.size());
                if(curChildPosition + 1==mViewPager.getAdapter().getCount())
                {
                    Log.d("home",mViewPager.getAdapter().getCount()+"");
                    viewBtnNext.setVisibility(View.INVISIBLE);
                }
                else {
                    viewBtnNext.setVisibility(View.VISIBLE);
                }
                if(curChildPosition + 1>1)
                {
                    viewBtnPre.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.btn_prev:
                if (curChildPosition - 1 < 0)
                    return;

                curChildPosition--;
                mViewPager.setCurrentItem(getItem(-1), true);
                txtCurPage.setText(curChildPosition + 1 + "/" + mListQuestion.size());

                if(curChildPosition + 1==mViewPager.getAdapter().getCount())
                {

                    viewBtnPre.setVisibility(View.INVISIBLE);
                }
                else {
                    viewBtnPre.setVisibility(View.VISIBLE);
                }
                if(curChildPosition + 1<mViewPager.getAdapter().getCount())
                {
                    viewBtnNext.setVisibility(View.VISIBLE);
                }
                if(curChildPosition + 1==1)
                {
                    viewBtnPre.setVisibility(View.GONE);
                }
                break;
        }

    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }


}
