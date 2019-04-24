package com.thphuc.androidarchitecture.module_app.ui.movies.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.thphuc.androidarchitecture.databinding.ItemMovieStyleOneBinding;
import com.thphuc.androidarchitecture.module_data.models.Movie;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public class MoreMovieViewHolder extends RecyclerView.ViewHolder {
    private ItemMovieStyleOneBinding itemMovieStyleOneBinding;

    public MoreMovieViewHolder(ItemMovieStyleOneBinding view) {
        super(view.getRoot());
        itemMovieStyleOneBinding = view;
        itemMovieStyleOneBinding.tvName.setMaxLines(10);
    }

    public void bindData(Movie item) {
        itemMovieStyleOneBinding.setName(item.getTitle());
        itemMovieStyleOneBinding.setImageUrl(item.getPosterPath());
    }
}
