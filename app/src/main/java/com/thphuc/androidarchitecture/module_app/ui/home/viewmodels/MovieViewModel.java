package com.thphuc.androidarchitecture.module_app.ui.home.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thphuc.androidarchitecture.module_app.base.BaseViewModel;
import com.thphuc.androidarchitecture.module_data.models.Movie;
import com.thphuc.androidarchitecture.module_data.models.bases.BaseResponse;
import com.thphuc.androidarchitecture.module_data.usecases.MovieUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
public class MovieViewModel extends BaseViewModel {
    private String apiKey;
    private MovieUseCase movieUseCase;
    private final MutableLiveData<List<List<Movie>>> listMovies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LiveData<Boolean> getError() {
        return repoLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<List<List<Movie>>> getListMovies() {
        return listMovies;
    }

    @Inject
    public MovieViewModel(@Named("api_key") String apiKey, MovieUseCase movieUseCase) {
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
    }

    public void fetchAllData() {
        loading.setValue(true);
        getDisposable().add(Observable.combineLatest(
                getTrendings(apiKey, 1),
                getNowPlaying(apiKey, 1),
                getPopular(apiKey, 1),
                getTopRated(apiKey, 1),
                getUpcoming(apiKey, 1),
                (baseResponseTrending, baseResponseNowPlaying, baseResponsePopular, baseResponseTopRated, baseResponseUpcoming) -> {
                    List<List<Movie>> list = new ArrayList<>();
                    list.add(baseResponseTrending.getResults().subList(0, 5));
                    list.add(baseResponseNowPlaying.getResults());
                    list.add(baseResponsePopular.getResults());
                    list.add(baseResponseTopRated.getResults());
                    list.add(baseResponseUpcoming.getResults());
                    return list;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResponse -> {
                    repoLoadError.setValue(false);
                    listMovies.setValue(listResponse);
                    loading.setValue(false);
                }, error -> {
                    repoLoadError.setValue(true);
                    loading.setValue(false);
                }));
    }

    public Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, int page) {
        return movieUseCase.getUpcoming(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    public Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, int page) {
        return movieUseCase.getTopRated(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    public Observable<BaseResponse<List<Movie>>> getPopular(String apiKey, int page) {
        return movieUseCase.getPopulars(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    public Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, int page) {
        return movieUseCase.getNowPlaying(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    public Observable<BaseResponse<List<Movie>>> getTrendings(String apiKey, int page) {
        return movieUseCase.getTrendingListForTheDay(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }
}
