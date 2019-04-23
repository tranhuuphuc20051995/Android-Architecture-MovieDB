package com.thphuc.androidarchitecture.module_app.ui.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentTvShowBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;
import com.thphuc.androidarchitecture.module_app.ui.home.listeners.MainFragmentListener;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class TVShowFragment extends BaseDatabindingFragment<FragmentTvShowBinding> {
    public static TVShowFragment newInstance() {
        Bundle args = new Bundle();
        TVShowFragment fragment = new TVShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    MainFragmentListener mainFragmentListener;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tv_show;
    }

    @Override
    protected void init(@Nullable View view) {
        if (getParentFragment() instanceof MainFragmentListener) {
            mainFragmentListener = (MainFragmentListener) getParentFragment();
        }
    }

    @Override
    protected void screenResume() {

    }

    @Override
    protected void screenPause() {

    }

    @Override
    protected void screenStart(@Nullable Bundle saveInstanceState) {

    }

    @Override
    protected void attach(Context context) {

    }
}
