package com.thphuc.androidarchitecture.module_app.ui.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.ItemLoadMoreAndNetworkStateBinding;
import com.thphuc.androidarchitecture.databinding.ItemMovieStyleOneBinding;
import com.thphuc.androidarchitecture.module_app.commons.ItemNetworkStateListener;
import com.thphuc.androidarchitecture.module_app.commons.ItemNetworkStateViewHolder;
import com.thphuc.androidarchitecture.module_app.commons.NetworkState;
import com.thphuc.androidarchitecture.module_app.ui.movies.adapters.viewholders.MoreMovieViewHolder;
import com.thphuc.androidarchitecture.module_data.models.Movie;

/**
 * Created by TranHuuPhuc on 3/28/19.
 */
public class MoreMovieAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {
    private NetworkState networkState;
    private ItemNetworkStateListener listener;

    public MoreMovieAdapter(ItemNetworkStateListener listener) {
        super(Movie.DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.item_movie_style_one) {
            ItemMovieStyleOneBinding binding = ItemMovieStyleOneBinding.inflate(layoutInflater, parent, false);
            binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new MoreMovieViewHolder(binding);
        } else if (viewType == R.layout.item_load_more_and_network_state) {
            ItemLoadMoreAndNetworkStateBinding binding = ItemLoadMoreAndNetworkStateBinding.inflate(layoutInflater, parent, false);
            return new ItemNetworkStateViewHolder(binding, listener);
        } else {
            throw new IllegalArgumentException("Error Unknown View Type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_movie_style_one:
                ((MoreMovieViewHolder) holder).bindData(getItem(position));
                break;
            case R.layout.item_load_more_and_network_state:
                ((ItemNetworkStateViewHolder) holder).bindView(networkState);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.item_load_more_and_network_state;
        } else {
            return R.layout.item_movie_style_one;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

}
