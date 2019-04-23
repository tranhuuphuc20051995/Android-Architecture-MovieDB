package com.thphuc.androidarchitecture.module_app.ui.home.listeners;

import com.thphuc.androidarchitecture.module_data.models.Movie;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public interface MainFragmentListener {
    void onItemMovieClick(Movie movie);

    void onItemMoreClick(int type);
}
