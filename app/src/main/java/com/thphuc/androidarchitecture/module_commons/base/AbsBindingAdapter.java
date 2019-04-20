package com.thphuc.androidarchitecture.module_commons.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by tran.huu.phuc on 10/7/16.
 */
public abstract class AbsBindingAdapter<T extends ViewDataBinding> extends RecyclerView.Adapter<AbsBindingHolder<? extends ViewDataBinding>> {
    private RecyclerViewClickListener itemListener;
    private Gson gson;

    public AbsBindingAdapter(RecyclerViewClickListener itemListener) {
        this.itemListener = itemListener;
        gson = new GsonBuilder().create();
    }


    @NonNull
    @Override
    public AbsBindingHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutResourceId(viewType), parent, false);
        return new AbsBindingHolder<>(binding, itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsBindingHolder holder, int position) {
        updateBinding(holder.getBinding(), position);
    }

    @LayoutRes
    protected abstract int getLayoutResourceId(int viewType);


    public abstract void updateBinding(ViewDataBinding binding, int position);

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View view, int position);
    }

    public Gson getGson() {
        return gson;
    }
}

class AbsBindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder implements View.OnClickListener {
    @NonNull
    final T binding;
    private AbsBindingAdapter.RecyclerViewClickListener itemListener;

    public AbsBindingHolder(@NonNull T binding, AbsBindingAdapter.RecyclerViewClickListener itemListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.itemListener = itemListener;
        binding.getRoot().setOnClickListener(this);
    }

    @NonNull
    public T getBinding() {
        return binding;
    }

    @Override
    public void onClick(View v) {
        if (itemListener != null) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }

    }
}