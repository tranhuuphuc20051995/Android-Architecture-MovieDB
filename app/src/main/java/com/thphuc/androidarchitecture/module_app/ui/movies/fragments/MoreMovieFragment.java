package com.thphuc.androidarchitecture.module_app.ui.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentMoreMovieBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;
import com.thphuc.androidarchitecture.module_app.commons.ItemNetworkStateListener;
import com.thphuc.androidarchitecture.module_app.commons.ViewModelFactory;
import com.thphuc.androidarchitecture.module_app.ui.movies.adapters.MoreMovieAdapter;
import com.thphuc.androidarchitecture.module_app.ui.movies.viewmodels.MoreMovieViewModel;
import com.thphuc.androidarchitecture.module_commons.GridDividerItemDecoration;

import javax.inject.Inject;

/**
 * Created by TranHuuPhuc on 2019-04-22.
 */
public class MoreMovieFragment extends BaseDatabindingFragment<FragmentMoreMovieBinding> implements ItemNetworkStateListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_movie;
    }

    @Inject
    ViewModelFactory viewModelFactory;
    MoreMovieViewModel viewModel;
    private int type;
    private MoreMovieAdapter adapter;

    @Override
    protected void init(@Nullable View view) {
        viewDataBinding.toolbar.setTitle(R.string.more_movie);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoreMovieViewModel.class);
        adapter = new MoreMovieAdapter(this);
        viewDataBinding.rvMore.addItemDecoration(new GridDividerItemDecoration(20));
        viewDataBinding.rvMore.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        viewDataBinding.rvMore.setHasFixedSize(false);
        viewDataBinding.rvMore.setAdapter(adapter);
        if (getArguments() != null) {
            type = MoreMovieFragmentArgs.fromBundle(getArguments()).getType();
            getData();
        }
        initObserveViewModel();
    }

    private void initObserveViewModel() {
        viewModel.getListMovies().observe(this, movies -> {
            viewDataBinding.rvMore.setVisibility(View.VISIBLE);
            viewDataBinding.tvError.setVisibility(View.GONE);
            viewDataBinding.pbLoading.setVisibility(View.GONE);
            adapter.submitList(movies);
        });
        viewModel.getNetworkState().observe(this, networkState -> adapter.setNetworkState(networkState));
        viewModel.getError().observe(this, isError -> {
            if (isError != null) {
                if (isError) {
                    viewDataBinding.tvError.setVisibility(View.VISIBLE);
                    viewDataBinding.rvMore.setVisibility(View.GONE);
                    viewDataBinding.tvError.setText("An Error Occurred While Loading Data!");
                } else {
                    viewDataBinding.tvError.setVisibility(View.GONE);
                    viewDataBinding.tvError.setText(null);
                }
            }
        });
        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                viewDataBinding.pbLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    viewDataBinding.tvError.setVisibility(View.GONE);
                    viewDataBinding.rvMore.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getData() {
        viewModel.getData(type);
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

    @Override
    public void onRetryClick(View v, int adapterPosition) {
        //Todo reload more
    }
}
