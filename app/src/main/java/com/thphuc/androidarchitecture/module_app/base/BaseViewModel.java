package com.thphuc.androidarchitecture.module_app.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public class BaseViewModel extends ViewModel {
    private CompositeDisposable disposable;

    public BaseViewModel() {
        disposable = new CompositeDisposable();
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

}
