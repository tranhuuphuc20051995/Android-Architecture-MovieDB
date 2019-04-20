package com.thphuc.androidarchitecture.module_app.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public abstract class BaseDatabindingFragment<T extends ViewDataBinding> extends DaggerFragment {
    protected T viewDataBinding;
    protected CompositeDisposable disposableManager;

    private boolean isAttach;

    public boolean isAttach() {
        return isAttach;
    }

//    @Inject
//    ViewModelFactory viewModelFactory;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init(@Nullable View view);

    protected abstract void screenResume();

    protected abstract void screenPause();

    protected abstract void screenStart(@Nullable Bundle saveInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        isAttach = false;
        disposableManager = new CompositeDisposable();
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        screenStart(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        screenResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposableManager.clear();
        System.gc();
    }

    @Override
    public void onPause() {
        super.onPause();
        screenPause();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttach = true;
        attach(context);
    }

//    public ViewModelFactory getViewModelFactory() {
//        return viewModelFactory;
//    }

    protected abstract void attach(Context context);
}