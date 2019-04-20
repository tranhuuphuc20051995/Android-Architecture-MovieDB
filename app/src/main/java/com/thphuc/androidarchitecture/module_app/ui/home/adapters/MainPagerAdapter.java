package com.thphuc.androidarchitecture.module_app.ui.home.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thphuc.androidarchitecture.module_app.ui.home.fragments.MovieFragment;
import com.thphuc.androidarchitecture.module_app.ui.home.fragments.TVShowFragment;

/**
 * Created by TranHuuPhuc on 3/26/19.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MovieFragment.newInstance();
        }
        return TVShowFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
