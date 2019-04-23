package com.thphuc.androidarchitecture.module_app.ui.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.module_app.ui.movies.adapters.viewholders.MoreMovieViewHolder;
import com.thphuc.androidarchitecture.module_data.models.Movie;

/**
 * Created by TranHuuPhuc on 3/28/19.
 */
public class MoreMovieAdapter extends PagedListAdapter<Movie, MoreMovieViewHolder> {
    public MoreMovieAdapter() {
        super(Movie.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MoreMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie_style_one, parent, false);
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new MoreMovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreMovieViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }
}
