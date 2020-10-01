package com.example.UTN.src.Activities.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.UTN.src.Activities.tabs.AddTab;
import com.example.UTN.src.Activities.tabs.ListTab;
import com.example.UTN.src.Activities.tabs.UpdateTab;
import com.example.UTN.src.Interfaces.TabInterface;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final TabInterface[] APP_COMPAT_ACTIVITIES = new TabInterface[]{
            AddTab.newInstance(),
            UpdateTab.newInstance(),
            ListTab.newInstance()
    };
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (Fragment) APP_COMPAT_ACTIVITIES[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(APP_COMPAT_ACTIVITIES[position].getTabName());
    }

    @Override
    public int getCount() {
        return APP_COMPAT_ACTIVITIES.length;
    }
}