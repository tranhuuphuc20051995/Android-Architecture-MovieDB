package com.thphuc.androidarchitecture.module_network.service;

import android.database.Observable;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public interface TVService {
    @GET("tv/{tv_id}")
    Observable<JsonObject> getTVShowDetail(@Query("api_key") String apiKey, @Path("tv_id") int tvId);

    @GET("trending/tv/day")
    Observable<JsonObject> getTrendingListForTheDay(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/popular")
    Observable<JsonObject> getPopulars(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/top_rated")
    Observable<JsonObject> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/{tv_id}/recommendations")
    Observable<JsonObject> getRecommendations(@Query("api_key") String apiKey, @Path("tv_id") int tvId, @Query("page") int page);

    @GET("tv/{tv_id}/similar")
    Observable<JsonObject> getSimilarTVShow(@Query("api_key") String apiKey, @Path("tv_id") int tvId, @Query("page") int page);

    @GET("tv/{tv_id}/videos")
    Observable<JsonObject> getVideos(@Query("api_key") String apiKey, @Path("tv_id") int tvId);

    @GET("search/tv")
    Observable<JsonObject> searchTVShow(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);
}
