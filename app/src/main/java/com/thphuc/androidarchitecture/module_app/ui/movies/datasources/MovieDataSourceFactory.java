package com.thphuc.androidarchitecture.module_app.ui.movies.datasources;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.thphuc.androidarchitecture.module_data.models.Movie;
import com.thphuc.androidarchitecture.module_data.usecases.MovieUseCase;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {
    MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData = new MutableLiveData<>();
    private int type;
    private String apiKey;
    private MovieUseCase movieUseCase;
    private CompositeDisposable compositeDisposable;

    public MovieDataSourceFactory(int type, String apiKey, MovieUseCase movieUseCase, CompositeDisposable compositeDisposable) {
        this.type = type;
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieDataSource movieDataSource = new MovieDataSource(type, apiKey, movieUseCase, compositeDisposable);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
