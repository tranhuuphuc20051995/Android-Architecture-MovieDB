package com.thphuc.androidarchitecture.module_app.di.modules;

import com.thphuc.androidarchitecture.module_data.di.DataModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
@Module(includes = {ViewModelModule.class, DataModule.class})
public class ApplicationModule {
    @Provides
    @Named("api_key")
    public String provideAPIKey() {
        return "28aa4fa810b9a3e6a836ec3eaf3d916e";
    }

}
