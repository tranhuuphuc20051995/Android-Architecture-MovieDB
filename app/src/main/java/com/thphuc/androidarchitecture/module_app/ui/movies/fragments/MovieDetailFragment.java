package com.thphuc.androidarchitecture.module_app.ui.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentMovieDetailBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;
import com.thphuc.androidarchitecture.module_data.models.Movie;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public class MovieDetailFragment extends BaseDatabindingFragment<FragmentMovieDetailBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }


    @Override
    protected void init(@Nullable View view) {
        if (getArguments() != null) {
            Movie movie = (Movie) getArguments().getSerializable("movie");
            Toast.makeText(getContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void screenResume() {

    }

    @Override
    protected void screenPause() {

    }

    @Override
    protected void screenStart(@Nullable Bundle saveInstanceState) {

    }

    @Override
    protected void attach(Context context) {

    }
}
