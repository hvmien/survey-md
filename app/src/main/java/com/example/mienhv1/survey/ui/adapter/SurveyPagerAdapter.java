package com.example.mienhv1.survey.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/23.
 */

public class SurveyPagerAdapter extends FragmentPagerAdapter {
    ArrayList<ItemBaseSurveyFragment> fragments = new ArrayList<>();

    public SurveyPagerAdapter(FragmentManager fm, ArrayList fragments) {
        super(fm);
        this.fragments = fragments;
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
