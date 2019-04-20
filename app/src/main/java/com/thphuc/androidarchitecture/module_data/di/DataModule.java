package com.thphuc.androidarchitecture.module_data.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.thphuc.androidarchitecture.module_data.usecases.MovieUseCase;
import com.thphuc.androidarchitecture.module_data.usecases.impl.MovieUseCaseImpl;
import com.thphuc.androidarchitecture.module_network.di.NetworkModule;
import com.thphuc.androidarchitecture.module_network.service.MovieService;

import java.text.DateFormat;
import java.util.Date;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TranHuuPhuc on 2019-04-19.
 */
@Module(includes = NetworkModule.class)
public class DataModule {
    @Provides
    public Gson providesGson() {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, context) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG);
        return builder.create();
    }

    @Provides
    public MovieUseCase providesMovieUseCase(Gson gson, MovieService movieService) {
        return new MovieUseCaseImpl(gson, movieService);
    }

}
