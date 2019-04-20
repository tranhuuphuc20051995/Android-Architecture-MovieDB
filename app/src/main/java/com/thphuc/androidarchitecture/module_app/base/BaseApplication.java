package com.thphuc.androidarchitecture.module_app.base;

import com.thphuc.androidarchitecture.module_app.di.components.ApplicationComponent;
import com.thphuc.androidarchitecture.module_app.di.components.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
