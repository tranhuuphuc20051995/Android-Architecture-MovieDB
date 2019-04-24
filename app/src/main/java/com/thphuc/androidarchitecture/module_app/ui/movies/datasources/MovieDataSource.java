package com.thphuc.androidarchitecture.module_app.ui.movies.datasources;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.thphuc.androidarchitecture.module_app.commons.Constant;
import com.thphuc.androidarchitecture.module_app.commons.NetworkState;
import com.thphuc.androidarchitecture.module_app.commons.Status;
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
    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<Boolean> error;
    private MutableLiveData<Boolean> loading;
    private static final Integer FIRST_PAGE = 1;

    public MovieDataSource(int type, String apiKey, MovieUseCase movieUseCase, CompositeDisposable compositeDisposable) {
        this.type = type;
        this.apiKey = apiKey;
        this.loading = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
        this.networkState = new MutableLiveData<>();
        this.movieUseCase = movieUseCase;
        this.compositeDisposable = compositeDisposable;
    }

    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData<Boolean> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        networkState.postValue(NetworkState.LOADING);
        loading.postValue(true);
        switch (type) {
            case Constant.TYPE_NOW:
                compositeDisposable.add(movieUseCase.getNowPlaying(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    loading.postValue(false);
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                    } else {
                        error.postValue(true);
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    error.postValue(true);
                    loading.postValue(false);
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_POPULAR:
                compositeDisposable.add(movieUseCase.getPopulars(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    loading.postValue(false);
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                    } else {
                        error.postValue(true);
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    error.postValue(true);
                    loading.postValue(false);
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_TOP_RATE:
                compositeDisposable.add(movieUseCase.getTopRated(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    loading.postValue(false);
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                    } else {
                        error.postValue(true);
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    error.postValue(true);
                    loading.postValue(false);
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_UPCOMING:
            default:
                compositeDisposable.add(movieUseCase.getUpcoming(apiKey, FIRST_PAGE).subscribe((baseResponse -> {
                    loading.postValue(false);
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        callback.onResult(baseResponse.getResults(), null, FIRST_PAGE + 1);
                    } else {
                        error.postValue(true);
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    error.postValue(true);
                    loading.postValue(false);
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        networkState.postValue(NetworkState.LOADING);
        switch (type) {
            case Constant.TYPE_NOW:
                compositeDisposable.add(movieUseCase.getNowPlaying(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_POPULAR:
                compositeDisposable.add(movieUseCase.getPopulars(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_TOP_RATE:
                compositeDisposable.add(movieUseCase.getTopRated(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_UPCOMING:
            default:
                compositeDisposable.add(movieUseCase.getUpcoming(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        networkState.postValue(NetworkState.LOADING);
        switch (type) {
            case Constant.TYPE_NOW:
                compositeDisposable.add(movieUseCase.getNowPlaying(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key + 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_POPULAR:
                compositeDisposable.add(movieUseCase.getPopulars(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key + 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_TOP_RATE:
                compositeDisposable.add(movieUseCase.getTopRated(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key + 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
            case Constant.TYPE_UPCOMING:
            default:
                compositeDisposable.add(movieUseCase.getUpcoming(apiKey, params.key).subscribe((baseResponse -> {
                    if (baseResponse.getStatusCode() == 0) {
                        networkState.postValue(NetworkState.LOADED);
                        Integer key = (params.key > 1) ? params.key + 1 : null;
                        callback.onResult(baseResponse.getResults(), key);
                    } else {
                        networkState.postValue(new NetworkState(Status.FAILED, baseResponse.getStatusMessage()));
                    }
                }), throwerError -> {
                    String messageError;
                    messageError = throwerError.getMessage();
                    if (throwerError == null) {
                        messageError = "Unknown Error";
                    }
                    networkState.postValue(new NetworkState(Status.FAILED, messageError));
                }));
                break;
        }
    }
}