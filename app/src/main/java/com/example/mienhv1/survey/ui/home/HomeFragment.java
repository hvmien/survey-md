package com.example.mienhv1.survey.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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
    private OnHomeListener mListener;
    private ProgressBar mProgressbar;
    private CSViewPageNoScroll mViewPager;


    int curChildPosition = 0;

    CSTextView txtCurPage;
    private ArrayList mListQuestion = new ArrayList();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeListener) {
            mListener = (OnHomeListener) context;
        }
    }

    @Override
    public void onAttach(Activity actvity) {
        super.onAttach(actvity);
        if (actvity instanceof OnHomeListener) {
            mListener = (OnHomeListener) actvity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void mapView(View view) {
        mProgressbar = (ProgressBar) view.findViewById(R.id.home_progress);
        mViewPager = (CSViewPageNoScroll) view.findViewById(R.id.viewpager);
        mViewPager.setScrolling(false);

        mListQuestion = new ArrayList();
        ItemQuestionModel item1 = new ItemQuestionModel();
        item1.id = 2;
        item1.name = "roadahead";
        item1.title = "Đường đi trước cửa hàng";
        item1.type = 1;
        mListQuestion.add(item1);

        ItemQuestionModel item2 = new ItemQuestionModel();
        item2.id = 3;
        item2.name = "roaddirection";
        item2.title = "Chiều của đường";
        item2.type = 1;
        mListQuestion.add(item2);

        SurveyPagerAdapter adapter = new SurveyPagerAdapter(this.getChildFragmentManager(), mListQuestion);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(7);


        view.findViewById(R.id.btn_prev).setOnClickListener(this);
        view.findViewById(R.id.btn_next).setOnClickListener(this);
        txtCurPage = (CSTextView) view.findViewById(R.id.txt_cur_page);
        txtCurPage.setText(1 + "/" + mListQuestion.size());
    }

    @Override
    protected void initData() {
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
        mHomePresenter = new HomePresenter(dataRepository, this);
        setHasOptionsMenu(true);
        //add viewpager this here
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_signout:
                mHomePresenter.signOut();
                return true;
        }
        return true;
    }

    @Override
    public void navigateToLoginPage() {
        mListener.onOpenLoginPage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_next:

                if (curChildPosition + 1 >= mListQuestion.size())
                    return;

                curChildPosition++;
                mViewPager.setCurrentItem(getItem(+1), true);
                txtCurPage.setText(curChildPosition + 1 + "/" + mListQuestion.size());

                break;
            case R.id.btn_prev:
                if (curChildPosition - 1 < 0)
                    return;

                curChildPosition--;
                mViewPager.setCurrentItem(getItem(-1), true);
                txtCurPage.setText(curChildPosition + 1 + "/" + mListQuestion.size());

                break;
        }

    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    interface OnHomeListener {
        void onOpenLoginPage();
    }
}
