package com.thphuc.androidarchitecture.module_app.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.thphuc.androidarchitecture.module_app.commons.ViewModelFactory;
import com.thphuc.androidarchitecture.module_app.di.utils.ViewModelKey;
import com.thphuc.androidarchitecture.module_app.ui.home.viewmodels.MovieViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    abstract ViewModel bindMovieViewModel(MovieViewModel movieViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
