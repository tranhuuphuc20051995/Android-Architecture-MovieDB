package com.thphuc.androidarchitecture.module_app.ui.movies.adapters;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.ItemMovieStyleOneBinding;
import com.thphuc.androidarchitecture.module_app.ui.movies.adapters.diffutil.MovieDiffUtilCallBack;
import com.thphuc.androidarchitecture.module_commons.base.AbsBindingAdapter;
import com.thphuc.androidarchitecture.module_data.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TranHuuPhuc on 3/28/19.
 */
public class MoreMovieAdapter extends AbsBindingAdapter<ViewDataBinding> {
    private List<Movie> items;

    public MoreMovieAdapter() {
        super(null);
    }

    public void updateData(List<Movie> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        MovieDiffUtilCallBack diffCallback = new MovieDiffUtilCallBack(this.items, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.items.clear();
        this.items.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        return R.layout.item_movie_style_one;
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (binding instanceof ItemMovieStyleOneBinding) {
            ItemMovieStyleOneBinding itemBind = (ItemMovieStyleOneBinding) binding;
            Movie movie = items.get(position);
            itemBind.setName(movie.getTitle());
            itemBind.setImageUrl(movie.getPosterPath());
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
