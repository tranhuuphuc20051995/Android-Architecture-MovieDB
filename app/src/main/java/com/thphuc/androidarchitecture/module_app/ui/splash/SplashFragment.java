package com.thphuc.androidarchitecture.module_app.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentSplashBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public class SplashFragment extends BaseDatabindingFragment<FragmentSplashBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void init(@Nullable View view) {

    }

    @Override
    protected void screenResume() {
        disposableManager.add(Observable.just(true).delay(3000, TimeUnit.MILLISECONDS).subscribe(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_walkThroughtFragment);
        }));
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
