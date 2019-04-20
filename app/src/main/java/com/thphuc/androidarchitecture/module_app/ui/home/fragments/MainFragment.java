package com.thphuc.androidarchitecture.module_app.ui.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentMainBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;
import com.thphuc.androidarchitecture.module_app.ui.home.adapters.MainPagerAdapter;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public class MainFragment extends BaseDatabindingFragment<FragmentMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(@Nullable View view) {
        viewDataBinding.vpMain.setAdapter(new MainPagerAdapter(getChildFragmentManager()));
        viewDataBinding.bnvMain.setOnNavigationItemSelectedListener(this);
        viewDataBinding.vpMain.addOnPageChangeListener(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                viewDataBinding.vpMain.setCurrentItem(0);
                break;
            case R.id.navigation_tv_shows:
                viewDataBinding.vpMain.setCurrentItem(1);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewDataBinding.bnvMain.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
