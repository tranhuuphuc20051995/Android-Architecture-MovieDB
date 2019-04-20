package com.thphuc.androidarchitecture.module_app.di.modules;

import com.thphuc.androidarchitecture.module_app.ui.home.activities.MainActivity;
import com.thphuc.androidarchitecture.module_app.ui.home.activities.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
