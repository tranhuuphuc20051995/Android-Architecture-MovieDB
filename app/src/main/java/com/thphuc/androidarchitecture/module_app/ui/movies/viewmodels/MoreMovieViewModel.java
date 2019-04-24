package com.thphuc.androidarchitecture.module_app.ui.movies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.thphuc.androidarchitecture.module_app.base.BaseViewModel;
import com.thphuc.androidarchitecture.module_app.commons.NetworkState;
import com.thphuc.androidarchitecture.module_app.ui.movies.datasources.MovieDataSourceFactory;
import com.thphuc.androidarchitecture.module_data.models.Movie;
import com.thphuc.androidarchitecture.module_data.usecases.MovieUseCase;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public class MoreMovieViewModel extends BaseViewModel {
    private String apiKey;
    private MovieUseCase movieUseCase;
    private LiveData<PagedList<Movie>> listMovies = new MutableLiveData<>();
    private LiveData<NetworkState> networkState;
    private MovieDataSourceFactory movieDataSourceFactory;
    private MutableLiveData<Boolean> error;
    private MutableLiveData<Boolean> loading;
    private static final Integer PAGE_SIZE = 20;

    public LiveData<PagedList<Movie>> getListMovies() {
        return listMovies;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    @Inject
    public MoreMovieViewModel(@Named("api_key") String apiKey, MovieUseCase movieUseCase) {
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
        this.movieDataSourceFactory = new MovieDataSourceFactory(this.apiKey, this.movieUseCase, getDisposable());
        this.networkState = Transformations.switchMap(movieDataSourceFactory.getMutableLiveData(), dataSource -> dataSource.getNetworkState());
        this.loading = (MutableLiveData<Boolean>) Transformations.switchMap(movieDataSourceFactory.getMutableLiveData(), dataSource -> dataSource.getLoading());
        this.error = (MutableLiveData<Boolean>) Transformations.switchMap(movieDataSourceFactory.getMutableLiveData(), dataSource -> dataSource.getError());
    }

    public void getData(int type) {
        movieDataSourceFactory.initType(type);
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build();
        listMovies = new LivePagedListBuilder<>(movieDataSourceFactory, config).build();
    }
}
