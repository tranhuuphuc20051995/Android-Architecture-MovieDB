package com.thphuc.androidarchitecture.module_app.commons;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.thphuc.androidarchitecture.databinding.ItemLoadMoreAndNetworkStateBinding;

/**
 * Created by TranHuuPhuc on 2019-04-24.
 */
public class ItemNetworkStateViewHolder extends RecyclerView.ViewHolder {
    private ItemLoadMoreAndNetworkStateBinding bindingView;

    public ItemNetworkStateViewHolder(ItemLoadMoreAndNetworkStateBinding view, ItemNetworkStateListener listener) {
        super(view.getRoot());
        this.bindingView = view;
        this.bindingView.btRetry.setOnClickListener(v -> listener.onRetryClick(v, getAdapterPosition()));
    }

    public void bindView(NetworkState networkState) {
        if (networkState != null && networkState.getStatus() == Status.RUNNING) {
            bindingView.pbLoading.setVisibility(View.VISIBLE);
        } else {
            bindingView.pbLoading.setVisibility(View.GONE);
        }

        if (networkState != null && networkState.getStatus() == Status.FAILED) {
            bindingView.tvError.setVisibility(View.VISIBLE);
            bindingView.btRetry.setVisibility(View.VISIBLE);
            bindingView.tvError.setText(networkState.getMsg());
        } else {
            bindingView.btRetry.setVisibility(View.GONE);
            bindingView.tvError.setVisibility(View.GONE);
        }
    }
}
