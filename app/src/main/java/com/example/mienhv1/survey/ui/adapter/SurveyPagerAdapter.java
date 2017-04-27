package com.example.mienhv1.survey.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.checkbox.CheckboxFragment;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonFragment;
import com.example.mienhv1.survey.ui.fragment.radiobuttontextfield.RadioButtonTextFieldFragment;
import com.example.mienhv1.survey.ui.fragment.textfield.EditTextFragment;

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
                case 1:
                    RadioButtonFragment f = RadioButtonFragment.newInstance(models.get(i));
                    fragments.add(f);
                    break;
                case 2:
                    CheckboxFragment f2 = CheckboxFragment.newInstance(models.get(i));
                    fragments.add(f2);
                    break;

                case  3:
                    RadioButtonTextFieldFragment f3 = RadioButtonTextFieldFragment.newInstance(models.get(i));
                    fragments.add(f3);
                    break;

                case  4:
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
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
