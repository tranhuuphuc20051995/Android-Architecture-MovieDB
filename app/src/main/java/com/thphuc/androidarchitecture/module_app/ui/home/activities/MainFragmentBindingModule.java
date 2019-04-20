package com.thphuc.androidarchitecture.module_app.ui.home.activities;

import com.thphuc.androidarchitecture.module_app.ui.home.fragments.MainFragment;
import com.thphuc.androidarchitecture.module_app.ui.home.fragments.MovieFragment;
import com.thphuc.androidarchitecture.module_app.ui.home.fragments.TVShowFragment;
import com.thphuc.androidarchitecture.module_app.ui.splash.SplashFragment;
import com.thphuc.androidarchitecture.module_app.ui.walkthrought.fragments.WalkThroughtFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */

@Module
public abstract class MainFragmentBindingModule {
    @ContributesAndroidInjector
    abstract WalkThroughtFragment provideWalkThroughtFragment();

    @ContributesAndroidInjector
    abstract MainFragment provideMainFragment();

    @ContributesAndroidInjector
    abstract SplashFragment provideSplashFragment();

    @ContributesAndroidInjector
    abstract MovieFragment provideMovieFragment();

    @ContributesAndroidInjector
    abstract TVShowFragment provideTVShowFragment();
}
