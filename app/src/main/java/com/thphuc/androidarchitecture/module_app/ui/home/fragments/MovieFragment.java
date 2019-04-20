package com.thphuc.androidarchitecture.module_app.ui.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.FragmentMovieBinding;
import com.thphuc.androidarchitecture.module_app.base.BaseDatabindingFragment;
import com.thphuc.androidarchitecture.module_app.commons.ViewModelFactory;
import com.thphuc.androidarchitecture.module_app.ui.home.adapters.MainMovieAdapter;
import com.thphuc.androidarchitecture.module_app.ui.home.viewmodels.MovieViewModel;
import com.thphuc.androidarchitecture.module_data.models.Movie;

import javax.inject.Inject;

/**
 * Created by TranHuuPhuc on 3/24/19.
 */
public class MovieFragment extends BaseDatabindingFragment<FragmentMovieBinding> implements MainMovieAdapter.MainMovieAdapterListener {
    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Inject
    ViewModelFactory viewModelFactory;
    MovieViewModel viewModel;
    MainMovieAdapter adapter;

    @Override
    protected void init(@Nullable View view) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        adapter = new MainMovieAdapter(this);
        viewDataBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        viewDataBinding.rvMovies.setAdapter(adapter);
        viewModel.fetchAllData();
        observableViewModel();
    }

    private void observableViewModel() {
        viewModel.getListMovies().observe(this, listMovies -> {
            if (listMovies != null) {
                viewDataBinding.rvMovies.setVisibility(View.VISIBLE);
                viewDataBinding.tvError.setVisibility(View.GONE);
                viewDataBinding.pbLoading.setVisibility(View.GONE);
                adapter.addData(listMovies.get(0), listMovies.get(1), listMovies.get(2), listMovies.get(3), listMovies.get(4));
            }
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                viewDataBinding.tvError.setVisibility(View.VISIBLE);
                viewDataBinding.rvMovies.setVisibility(View.GONE);
                viewDataBinding.tvError.setText("An Error Occurred While Loading Data!");
            } else {
                viewDataBinding.tvError.setVisibility(View.GONE);
                viewDataBinding.tvError.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                viewDataBinding.pbLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    viewDataBinding.tvError.setVisibility(View.GONE);
                    viewDataBinding.rvMovies.setVisibility(View.GONE);
                }
            }
        });
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
    public void onItemMovieClick(Movie movie) {

    }

    @Override
    public void onItemMoreClick(int type) {

    }
}
