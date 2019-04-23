package com.thphuc.androidarchitecture.module_commons;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by TranHuuPhuc on 2019-04-23.
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public GridDividerItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = margin;
        outRect.bottom = margin;
        outRect.left = margin;
        outRect.right = margin;
    }
}
