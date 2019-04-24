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
    private int type;
    private String apiKey;
    private MovieUseCase movieUseCase;
    private MovieDataSource movieDataSource;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory(String apiKey, MovieUseCase movieUseCase, CompositeDisposable compositeDisposable) {
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
        this.movieDataSourceMutableLiveData = new MutableLiveData<>();
        this.compositeDisposable = compositeDisposable;
    }

    public void initType(int type) {
        this.type = type;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        movieDataSource = new MovieDataSource(type, apiKey, movieUseCase, compositeDisposable);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
