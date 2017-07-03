package com.example.mienhv1.survey.ui.home;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datasource.model.AnswerModel;
import com.example.datasource.model.DataAnswerText;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.ResponeDataText;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.UploadDataAnswerTextUserCase;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.SurveyPagerAdapter;
import com.example.mienhv1.survey.ui.dialog.DoneAnswerDialogFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.upload.UploadFragment;
import com.example.mienhv1.survey.utils.view.CSTextView;
import com.example.mienhv1.survey.utils.view.CSViewPageNoScroll;

import java.util.ArrayList;

import cn.refactor.lib.colordialog.PromptDialog;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener,
        DoneAnswerDialogFragment.OnDialogFragmentClickListener {

    private HomePresenter mHomePresenter;

    private ProgressBar mProgressbar;
    private CSViewPageNoScroll mViewPager;
    ImageView viewBtnNext;
    ImageView viewBtnPre;

    int curChildPosition = 0;

    CSTextView txtCurPage;
    private ArrayList mListQuestion = new ArrayList();
    private ArrayList mListAnswer = new ArrayList();

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void mapView(View view) {
        mProgressbar = (ProgressBar) view.findViewById(R.id.home_progress_bar);
        mViewPager = (CSViewPageNoScroll) view.findViewById(R.id.viewpager);
        viewBtnNext = (ImageView) view.findViewById(R.id.btn_next);
        viewBtnPre = (ImageView) view.findViewById(R.id.btn_prev);
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
        if (curChildPosition + 1 == 1) {
            viewBtnPre.setClickable(false);
            viewBtnNext.setImageResource(R.drawable.ic_arrow_right_active);
        }
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

    SurveyPagerAdapter adapter;

    @Override
    public void getListQuestion(DataResponse<ItemQuestionModel> datamodel) {
        mListQuestion = new ArrayList();
        if (datamodel.data != null) {
            mListQuestion.addAll(datamodel.data);
            adapter = new SurveyPagerAdapter(this.getChildFragmentManager(), mListQuestion);

            mViewPager.setAdapter(adapter);
            mViewPager.setOffscreenPageLimit(mListQuestion.size());

            txtCurPage.setText(1 + "/" + mListQuestion.size());
        }
    }

    private void showPromptDlgSuccess() {
        PromptDialog promptDialog = new PromptDialog(getActivity());

        promptDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
        promptDialog.setAnimationEnable(true);
        promptDialog.setCancelable(false);
        promptDialog.setTitleText(getString(R.string.success));
        promptDialog.setContentText(getString(R.string.text_data));
        promptDialog.setPositiveListener(getString(R.string.ok), new PromptDialog.OnPositiveListener() {
            @Override
            public void onClick(PromptDialog dialog) {
                dialog.dismiss();
                getActivity().finish();
            }
        });
        promptDialog.setCancelable(false);
        promptDialog.show();
    }

    private void showPromptDlgError(String message) {
        PromptDialog promptDialog = new PromptDialog(getActivity());

        promptDialog.setDialogType(PromptDialog.DIALOG_TYPE_WRONG);
        promptDialog.setAnimationEnable(true);
        promptDialog.setCancelable(false);
        promptDialog.setTitleText(getString(R.string.error));
        promptDialog.setContentText(message);
        promptDialog.setPositiveListener(getString(R.string.ok), new PromptDialog.OnPositiveListener() {
            @Override
            public void onClick(PromptDialog dialog) {
                curChildPosition--;
                dialog.dismiss();
            }
        });
        promptDialog.setCancelable(false);
        promptDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_next:
                if (checkHaveData()) {
                    ItemBaseSurveyFragment item = (ItemBaseSurveyFragment) adapter.getItem(curChildPosition);

                    AnswerModel m = item.getDataFromUserHandle();
                    mListAnswer.add(m);
                    if (curChildPosition + 1 >= mListQuestion.size() + 1) {
                        Log.d("Homefrag", "return out page");
                        return;
                    }

                    curChildPosition++;
                    mViewPager.setCurrentItem(getItem(+1), true);
                    if (curChildPosition < mListQuestion.size()) {
                        txtCurPage.setText(curChildPosition + 1 + "/" + mListQuestion.size());
                    }
                    if (curChildPosition + 1 == mViewPager.getAdapter().getCount()) {
                        viewBtnPre.setClickable(true);
                        viewBtnNext.setImageResource(R.drawable.checked_done);

                    } else if (curChildPosition + 1 == mViewPager.getAdapter().getCount() + 1) {
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.addToBackStack(null);
//
//                        DoneAnswerDialogFragment generalDialogFragment =
//                                DoneAnswerDialogFragment.newInstance(getResources().getString(R.string.title_dialog), "message");
//                        generalDialogFragment.setCancelable(false);
//                        generalDialogFragment.setDoneAnswerDialogFragment(this);
//                        ft.add(generalDialogFragment,"");
//                        ft.commit();
                        DataAnswerText datatext = new DataAnswerText();
                        datatext.userid = 10;
                        datatext.answerModelArrayList = mListAnswer;
                        uploaddata(datatext);
//                        showPromptDlg();
                    } else {
                        viewBtnPre.setClickable(true);
                        viewBtnNext.setImageResource(R.drawable.ic_arrow_right_active);
                    }
                    if (curChildPosition + 1 > 1) {
                        viewBtnPre.setImageResource(R.drawable.ic_arrow_left_active);
                        viewBtnPre.setClickable(true);
                    }

                } else {
                    Toast.makeText(getActivity(), "Chua dien day du thong tin", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_prev:

                if (curChildPosition - 1 < 0)
                    return;

                curChildPosition--;
                mViewPager.setCurrentItem(getItem(-1), true);
                txtCurPage.setText(curChildPosition + 1 + "/" + mListQuestion.size());

//                if(curChildPosition + 1==mViewPager.getAdapter().getCount())
//                {
//
//                    viewBtnPre.setVisibility(View.INVISIBLE);
//                }
//                else {
//                    viewBtnPre.setVisibility(View.VISIBLE);
//                }
                if (curChildPosition + 1 < mViewPager.getAdapter().getCount()) {
                    viewBtnNext.setImageResource(R.drawable.ic_arrow_right_active);
                    viewBtnNext.setClickable(true);
                }
                if (curChildPosition + 1 == 1) {
                    viewBtnPre.setImageResource(R.drawable.ic_arrow_left_gray);
                    viewBtnPre.setClickable(false);
                }
                break;
        }

    }

    private void uploaddata(DataAnswerText order) {
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
        UploadDataAnswerTextUserCase uploadDataAnswerTextUserCase = new UploadDataAnswerTextUserCase(dataRepository);
        UploadDataAnswerTextUserCase.RequestValue requestValue = new UploadDataAnswerTextUserCase.RequestValue(order);
        uploadDataAnswerTextUserCase.execute(new UploadDataObserver(), requestValue);
    }

    private boolean checkHaveData() {
        if (curChildPosition < mListQuestion.size() + 1) {
            ItemBaseSurveyFragment item = (ItemBaseSurveyFragment) adapter.getItem(curChildPosition);
            //if(item.checkData())
            return true;
        }
        return false;
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    @Override
    public void onOkClicked(DoneAnswerDialogFragment dialog) {

        Toast.makeText(getActivity(), "onOkClicked", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
        getActivity().finish();
    }

    @Override
    public void onCancelClicked(DoneAnswerDialogFragment dialog) {
        curChildPosition--;
        Toast.makeText(getActivity(), "onCancelClicked", Toast.LENGTH_SHORT).show();
    }

    UploadFragment uploadimage;

    private class UploadDataObserver extends DisposableObserver<DataResponse<ResponeDataText>> implements UploadFragment.OnCallbackUpload {
        @Override
        public void onNext(DataResponse<ResponeDataText> storeSystemDataResponse) {
            Toast.makeText(getActivity(), "onNext UploadDataObserver" + storeSystemDataResponse.msg, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(Throwable e) {
            showPromptDlgError(e.getMessage());
//            Toast.makeText(getActivity(), "onError " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
//            uploadimage = (UploadFragment) adapter.getItem(mListQuestion.size() - 1);
//            uploadimage.setOnCallbackUpload(this);
//            uploadimage.upload();

        }

        @Override
        public void onSuccess() {
            showPromptDlgSuccess();
        }

        @Override
        public void onError() {
            showPromptDlgError("uploadimage error");
        }
    }
}
