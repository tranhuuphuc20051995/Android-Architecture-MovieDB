package com.thphuc.androidarchitecture.module_data.usecases.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thphuc.androidarchitecture.module_data.models.Movie;
import com.thphuc.androidarchitecture.module_data.models.Video;
import com.thphuc.androidarchitecture.module_data.models.bases.BaseResponse;
import com.thphuc.androidarchitecture.module_data.usecases.MovieUseCase;
import com.thphuc.androidarchitecture.module_network.service.MovieService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TranHuuPhuc on 3/24/19.
 */
public class MovieUseCaseImpl extends BaseUseCase implements MovieUseCase {
    private MovieService movieService;

    @Inject
    public MovieUseCaseImpl(Gson gson, MovieService movieService) {
        super(gson);
        this.movieService = movieService;
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getTrendingListForTheDay(String apiKey, int page) {
        return movieService.getTrendingListForTheDay(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, int page) {
        return movieService.getNowPlaying(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getPopulars(String apiKey, int page) {
        return movieService.getPopulars(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, int page) {
        return movieService.getTopRated(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, int page) {
        return movieService.getUpcoming(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<Movie>> getMovieDetail(String apiKey, int movieId) {
        return movieService.getMovieDetail(apiKey, movieId)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<Movie>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getRecommendations(String apiKey, int movieId, int page) {
        return movieService.getRecommendations(apiKey, movieId, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getSimilarMovies(String apiKey, int movieId, int page) {
        return movieService.getSimilarMovies(apiKey, movieId, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Video>>> getVideoTrailer(String apiKey, int movieId) {
        return movieService.getVideoTrailer(apiKey, movieId)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Video>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> searchMovies(String apiKey, String query, int page) {
        return movieService.searchMovies(apiKey, query, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }
}
