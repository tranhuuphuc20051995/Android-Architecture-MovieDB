package com.thphuc.androidarchitecture.module_network.service;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TranHuuPhuc on 3/24/19.
 */
public interface MovieService {
    @GET("trending/movie/day")
    Observable<JsonObject> getTrendingListForTheDay(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/now_playing")
    Observable<JsonObject> getNowPlaying(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular")
    Observable<JsonObject> getPopulars(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Observable<JsonObject> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/upcoming")
    Observable<JsonObject> getUpcoming(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movieId}")
    Observable<JsonObject> getMovieDetail(@Query("api_key") String apiKey, @Path("movieId") int movieId);

    @GET("movie/{movieId}/recommendations")
    Observable<JsonObject> getRecommendations(@Query("api_key") String apiKey, @Path("movieId") int movieId, @Query("page") int page);

    @GET("movie/{movieId}/similar")
    Observable<JsonObject> getSimilarMovies(@Query("api_key") String apiKey, @Path("movieId") int movieId, @Query("page") int page);

    @GET("movie/{movieId}/videos")
    Observable<JsonObject> getVideoTrailer(@Query("api_key") String apiKey, @Path("movieId") int movieId);

    @GET("search/movie")
    Observable<JsonObject> searchMovies(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);
}
