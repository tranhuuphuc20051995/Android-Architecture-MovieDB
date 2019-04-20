package com.thphuc.androidarchitecture.module_app.ui.home.adapters;

import androidx.databinding.ViewDataBinding;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.ItemMoreBinding;
import com.thphuc.androidarchitecture.databinding.ItemMovieStyleOneBinding;
import com.thphuc.androidarchitecture.databinding.ItemMovieStyleTwoBinding;
import com.thphuc.androidarchitecture.module_commons.base.AbsBindingAdapter;
import com.thphuc.androidarchitecture.module_data.models.Movie;

import java.util.List;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainCommonAdapter extends AbsBindingAdapter<ViewDataBinding> {
    public static final int TYPE_NOW = 0;
    public static final int TYPE_COMMON = 1;
    private static final int TYPE_FOOTER = 2;
    private int style;
    private List<Movie> listMovie;
    private MainMovieAdapter.MainMovieAdapterListener listener;
    private int type;

    public MainCommonAdapter(int style, List<Movie> listMovie, MainMovieAdapter.MainMovieAdapterListener listener, int type) {
        super(null);
        this.style = style;
        this.listMovie = listMovie;
        this.listener = listener;
        this.type = type;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        if (viewType == TYPE_NOW) {
            return R.layout.item_movie_style_one;
        } else if (viewType == TYPE_COMMON) {
            return R.layout.item_movie_style_two;
        } else {
            return R.layout.item_more;
        }
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (position == listMovie.size()) {
            ItemMoreBinding itemBind = (ItemMoreBinding) binding;
            itemBind.flMore.setOnClickListener(view -> listener.onItemMoreClick(type));
        } else {
            Movie movie = listMovie.get(position);
            if (binding instanceof ItemMovieStyleOneBinding) {
                ItemMovieStyleOneBinding itemBind = (ItemMovieStyleOneBinding) binding;
                itemBind.setName(movie.getTitle());
                itemBind.setImageUrl(movie.getPosterPath());
                itemBind.llMovie.setOnClickListener(view -> listener.onItemMovieClick(movie));
            } else {
                ItemMovieStyleTwoBinding itemBind = (ItemMovieStyleTwoBinding) binding;
                itemBind.setName(movie.getTitle());
                itemBind.setImageUrl(movie.getPosterPath());
                itemBind.flMovie.setOnClickListener(view -> listener.onItemMovieClick(movie));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == listMovie.size() ? TYPE_FOOTER : style == TYPE_NOW ? TYPE_NOW : TYPE_COMMON;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size() + 1;
    }
}
