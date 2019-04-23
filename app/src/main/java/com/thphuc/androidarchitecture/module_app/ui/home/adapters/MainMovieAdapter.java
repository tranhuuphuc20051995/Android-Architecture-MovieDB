package com.thphuc.androidarchitecture.module_app.ui.home.adapters;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.rd.animation.type.AnimationType;
import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.ItemMainMovieBinding;
import com.thphuc.androidarchitecture.databinding.ItemMainSliderBinding;
import com.thphuc.androidarchitecture.module_app.commons.Constant;
import com.thphuc.androidarchitecture.module_commons.LinearDividerItemDecoration;
import com.thphuc.androidarchitecture.module_commons.base.AbsBindingAdapter;
import com.thphuc.androidarchitecture.module_data.models.Movie;

import java.util.List;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainMovieAdapter extends AbsBindingAdapter<ViewDataBinding> {
    private static final int TYPE_SLIDER = 0;
    private static final int TYPE_NOW = 1;
    private static final int TYPE_POPULAR = 2;
    private static final int TYPE_TOP = 3;
    private static final int TYPE_UPCOMING = 4;
    private List<Movie> listSlider, listNow, listPopular, listTopRated, listUpcoming;
    private MainMovieAdapterListener listener;

    public MainMovieAdapter(MainMovieAdapterListener listener) {
        super(null);
        this.listener = listener;
    }

    public void addData(List<Movie> listSlider, List<Movie> listNow, List<Movie> listPopular, List<Movie> listTopRated, List<Movie> listUpcoming) {
        this.listSlider = listSlider;
        this.listNow = listNow;
        this.listPopular = listPopular;
        this.listTopRated = listTopRated;
        this.listUpcoming = listUpcoming;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        if (viewType == TYPE_SLIDER) {
            return R.layout.item_main_slider;
        } else {
            return R.layout.item_main_movie;
        }
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        Context context = binding.getRoot().getContext();
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(android.R.color.white, Constant.LINEAR_DIVIDER_ITEM_DECORATION);
        if (position == TYPE_SLIDER) {
            ItemMainSliderBinding itemBind = (ItemMainSliderBinding) binding;
            itemBind.pageIndicator.setCount(listSlider.size());
            itemBind.pageIndicator.setSelection(0);
            itemBind.pageIndicator.setAnimationType(AnimationType.THIN_WORM);
            itemBind.vpSlider.setAdapter(new MainSliderAdapter(listSlider, listener));
            itemBind.vpSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    itemBind.pageIndicator.setSelection(position);
                    super.onPageSelected(position);
                }
            });
        } else if (position == TYPE_NOW) {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Now Playing");
            itemBind.rvContent.addItemDecoration(dividerItemDecoration);
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_NOW, listNow, listener, Constant.TYPE_NOW));
        } else if (position == TYPE_POPULAR) {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Popular");
            itemBind.rvContent.addItemDecoration(dividerItemDecoration);
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_COMMON, listPopular, listener, Constant.TYPE_POPULAR));
        } else if (position == TYPE_TOP) {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Top Rated");
            itemBind.rvContent.addItemDecoration(dividerItemDecoration);
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_COMMON, listTopRated, listener, Constant.TYPE_TOP_RATE));
        } else {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Upcoming");
            itemBind.rvContent.addItemDecoration(dividerItemDecoration);
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_COMMON, listUpcoming, listener, Constant.TYPE_UPCOMING));
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_SLIDER:
                return TYPE_SLIDER;
            case TYPE_NOW:
                return TYPE_NOW;
            case TYPE_POPULAR:
                return TYPE_POPULAR;
            case TYPE_TOP:
                return TYPE_TOP;
            case TYPE_UPCOMING:
            default:
                return TYPE_UPCOMING;
        }
    }

    @Override
    public int getItemCount() {
        return listSlider == null && listNow == null && listPopular == null && listTopRated == null && listUpcoming == null ? 0 : 5;
    }

    public interface MainMovieAdapterListener {
        void onItemMovieClick(Movie movie);

        void onItemMoreClick(int type);
    }
}
