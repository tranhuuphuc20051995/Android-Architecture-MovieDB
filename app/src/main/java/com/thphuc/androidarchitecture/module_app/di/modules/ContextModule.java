package com.thphuc.androidarchitecture.module_app.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
@Module
public abstract class ContextModule {
    @Binds
    abstract Context provideContext(Application application);
}
