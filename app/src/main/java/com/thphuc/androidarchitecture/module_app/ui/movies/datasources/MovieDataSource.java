package com.thphuc.androidarchitecture.module_app.ui.movies.datasources;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.thphuc.androidarchitecture.module_app.commons.Constant;
import com.thphuc.androidarchitecture.module_data.models.Movie;
import com.thphuc.androidarchitecture.module_data.usecases.MovieUseCase;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    private int type;
    private String apiKey;
    private MovieUseCase movieUseCase;
    private CompositeDisposable compositeDisposable;
    private static final Integer FIRST_PAGE = 1;

    public MovieDataSource(int type, String apiKey, MovieUseCase movieUseCase, CompositeDisposable compositeDisposable) {
        this.type = type;
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        switch (type) {
            case Constant.TYPE_NOW:
                compositeDisposable.add(movieUseCase.getNowPlaying(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                })));
                break;
            case Constant.TYPE_POPULAR:
                compositeDisposable.add(movieUseCase.getPopulars(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                })));
                break;
            case Constant.TYPE_TOP_RATE:
                compositeDisposable.add(movieUseCase.getTopRated(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                })));
                break;
            case Constant.TYPE_UPCOMING:
            default:
                compositeDisposable.add(movieUseCase.getUpcoming(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                })));
                break;
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        switch (type) {
            case Constant.TYPE_NOW:
                compositeDisposable.add(movieUseCase.getNowPlaying(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
            case Constant.TYPE_POPULAR:
                compositeDisposable.add(movieUseCase.getPopulars(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
            case Constant.TYPE_TOP_RATE:
                compositeDisposable.add(movieUseCase.getTopRated(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
            case Constant.TYPE_UPCOMING:
            default:
                compositeDisposable.add(movieUseCase.getUpcoming(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        switch (type) {
            case Constant.TYPE_NOW:
                compositeDisposable.add(movieUseCase.getNowPlaying(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key + 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
            case Constant.TYPE_POPULAR:
                compositeDisposable.add(movieUseCase.getPopulars(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key + 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
            case Constant.TYPE_TOP_RATE:
                compositeDisposable.add(movieUseCase.getTopRated(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key + 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
            case Constant.TYPE_UPCOMING:
            default:
                compositeDisposable.add(movieUseCase.getUpcoming(apiKey, params.key).subscribe((baseResponse -> {
                    Integer key = (params.key > 1) ? params.key + 1 : null;
                    callback.onResult(baseResponse.getResults(), key);
                })));
                break;
        }
    }
}