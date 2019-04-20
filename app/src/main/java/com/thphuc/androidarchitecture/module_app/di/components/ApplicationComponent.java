package com.thphuc.androidarchitecture.module_app.di.components;

import android.app.Application;

import com.thphuc.androidarchitecture.module_app.base.BaseApplication;
import com.thphuc.androidarchitecture.module_app.di.modules.ActivityBindingModule;
import com.thphuc.androidarchitecture.module_app.di.modules.ApplicationModule;
import com.thphuc.androidarchitecture.module_app.di.modules.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}