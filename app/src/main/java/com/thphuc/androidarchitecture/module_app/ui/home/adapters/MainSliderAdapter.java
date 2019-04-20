package com.thphuc.androidarchitecture.module_app.ui.home.adapters;

import androidx.databinding.ViewDataBinding;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.ItemSliderBinding;
import com.thphuc.androidarchitecture.module_commons.base.AbsBindingAdapter;
import com.thphuc.androidarchitecture.module_data.models.Movie;

import java.util.List;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainSliderAdapter extends AbsBindingAdapter<ItemSliderBinding> {
    private List<Movie> listMovie;
    private MainMovieAdapter.MainMovieAdapterListener listener;

    public MainSliderAdapter(List<Movie> listMovie, MainMovieAdapter.MainMovieAdapterListener listener) {
        super(null);
        this.listMovie = listMovie;
        this.listener = listener;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        return R.layout.item_slider;
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (binding instanceof ItemSliderBinding) {
            ItemSliderBinding itemBind = (ItemSliderBinding) binding;
            Movie movie = listMovie.get(position);
            itemBind.setName(movie.getTitle());
            itemBind.setImageUrl(movie.getBackdropPath());
            itemBind.flSlider.setOnClickListener(view -> listener.onItemMovieClick(movie));
        }
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size();
    }
}
