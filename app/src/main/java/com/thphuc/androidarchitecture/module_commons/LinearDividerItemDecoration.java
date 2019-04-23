package com.thphuc.androidarchitecture.module_commons;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by TranHuuPhuc on 3/21/18.
 */

public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;
    private int dividerHeight;

    private int layoutOrientation = -1;

    public LinearDividerItemDecoration(int color, int dHeight) {
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(dHeight);
        dividerHeight = dHeight;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getLayoutManager() instanceof LinearLayoutManager && layoutOrientation == -1) {
            layoutOrientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
        }

        if (layoutOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(dividerHeight, 0, dividerHeight, 0);
        } else {
            outRect.set(0, dividerHeight, 0, dividerHeight);
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (layoutOrientation == LinearLayoutManager.HORIZONTAL) {
            horizontalDivider(c, parent);
        } else {
            verticalDivider(c, parent);
        }
    }

    private void horizontalDivider(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            c.drawLine(left, top, left, bottom, paint);
        }
    }

    private void verticalDivider(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            c.drawLine(left, top, right, top, paint);
        }
    }
}
