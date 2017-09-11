package com.example.mienhv1.survey.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.checkbox.CheckboxFragment;
import com.example.mienhv1.survey.ui.fragment.info.InfoFragment;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonFragment;
import com.example.mienhv1.survey.ui.fragment.radiobuttontextfield.RadioButtonTextFieldFragment;
import com.example.mienhv1.survey.ui.fragment.textfield.EditTextFragment;
import com.example.mienhv1.survey.ui.fragment.upload.UploadFragment;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/23.
 */

public class SurveyPagerAdapter extends FragmentPagerAdapter {
    ArrayList<ItemBaseSurveyFragment> fragments = new ArrayList<>();

    public SurveyPagerAdapter(FragmentManager fm, ArrayList<ItemQuestionModel> models) {
        super(fm);
        generateFragments(models);
    }

    private void generateFragments(ArrayList<ItemQuestionModel> models) {
        for (int i = 0; i < models.size(); i++) {
            switch (models.get(i).type) {
                case 0:
                    InfoFragment f0 = InfoFragment.newInstance(models.get(i));
                    fragments.add(f0);
                    break;
                case 1:
                    RadioButtonFragment f1 = RadioButtonFragment.newInstance(models.get(i));
                    fragments.add(f1);
                    break;
                case 2:
                    CheckboxFragment f2 = CheckboxFragment.newInstance(models.get(i));
                    fragments.add(f2);
                    break;

                case 3:
                    RadioButtonTextFieldFragment f3 = RadioButtonTextFieldFragment.newInstance(models.get(i));
                    fragments.add(f3);
                    break;

                case 4:
                    CheckboxFragment f4 = CheckboxFragment.newInstance(models.get(i));
                    fragments.add(f4);
                    break;
                case 5:
                    CheckboxFragment f5 = CheckboxFragment.newInstance(models.get(i));
                    fragments.add(f5);
                    break;
                case 6:
                    EditTextFragment f6 = EditTextFragment.newInstance(models.get(i));
                    fragments.add(f6);
                    break;
                case 7:
                    UploadFragment f7 = UploadFragment.newInstance(models.get(i));
                    fragments.add(f7);
                    break;
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("mien123",position+"/"+fragments.size());
        if (position < fragments.size())
            return fragments.get(position);
        return null;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
