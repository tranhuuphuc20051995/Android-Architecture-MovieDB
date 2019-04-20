package com.thphuc.androidarchitecture.module_app.ui.walkthrought.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.rd.animation.type.AnimationType;
import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentWalkthroughtBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;
import com.thphuc.androidarchitecture.module_app.ui.walkthrought.adapters.WalkThroughtPagerAdapter;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public class WalkThroughtFragment extends BaseDatabindingFragment<FragmentWalkthroughtBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_walkthrought;
    }

    @Override
    protected void init(@Nullable View view) {
        initViewPager();
        initEvent();
    }

    private void initEvent() {
        viewDataBinding.btWalkthrought.setOnClickListener(v -> {
            if (viewDataBinding.vpWalkthrought.getCurrentItem() == 2) {
                NavHostFragment.findNavController(this).navigate(R.id.action_walkThroughtFragment_to_mainFragment);
            } else {
                viewDataBinding.vpWalkthrought.setCurrentItem(viewDataBinding.vpWalkthrought.getCurrentItem() + 1);
            }
        });
    }

    private void initViewPager() {
        viewDataBinding.pageIndicator.setCount(3);
        viewDataBinding.pageIndicator.setSelection(0);
        viewDataBinding.pageIndicator.setAnimationType(AnimationType.WORM);
        viewDataBinding.vpWalkthrought.setAdapter(new WalkThroughtPagerAdapter());
        viewDataBinding.btWalkthrought.setText(R.string.next);
        viewDataBinding.btWalkthrought.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_solid_white));
        viewDataBinding.vpWalkthrought.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    viewDataBinding.btWalkthrought.setText(R.string.get_started);
                    viewDataBinding.btWalkthrought.setBackground(ContextCompat.getDrawable(viewDataBinding.getRoot().getContext(), R.drawable.bg_gradient));
                } else {
                    viewDataBinding.btWalkthrought.setText(R.string.next);
                    viewDataBinding.btWalkthrought.setBackground(ContextCompat.getDrawable(viewDataBinding.getRoot().getContext(), R.drawable.bg_solid_white));
                }
                viewDataBinding.pageIndicator.setSelection(position);
                super.onPageSelected(position);
            }
        });
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
