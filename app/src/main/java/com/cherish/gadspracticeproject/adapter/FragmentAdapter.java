package com.cherish.gadspracticeproject.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cherish.gadspracticeproject.fragment.LearningLeaderFragment;
import com.cherish.gadspracticeproject.fragment.SkillIQLearnerFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public FragmentAdapter(Context context, FragmentManager fragmentManager, int totalTabs){
        super(fragmentManager);
        this.context = context;
        this.totalTabs = totalTabs;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return LearningLeaderFragment.newInstance();
        }
        else {
            return SkillIQLearnerFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
