package com.thphuc.androidarchitecture.module_app.ui.movies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.thphuc.androidarchitecture.module_app.base.BaseViewModel;
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
    private MovieDataSourceFactory movieDataSourceFactory;
    private static final Integer PAGE_SIZE = 20;

    public LiveData<PagedList<Movie>> getListMovies() {
        return listMovies;
    }

    @Inject
    public MoreMovieViewModel(@Named("api_key") String apiKey, MovieUseCase movieUseCase) {
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
    }

    public void getData(int type) {
        this.movieDataSourceFactory = new MovieDataSourceFactory(type, apiKey, movieUseCase, getDisposable());
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build();
        listMovies = new LivePagedListBuilder<>(movieDataSourceFactory, config).build();
    }
}
