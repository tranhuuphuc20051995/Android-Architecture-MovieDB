package com.thphuc.androidarchitecture.module_commons;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;

import com.jakewharton.rxbinding2.view.RxView;
import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.WidgetTopbarBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by TranHuuPhuc on 3/27/18.
 */

public class WidgetTopBar extends FrameLayout {
    protected WidgetTopbarBinding widgetTopbarBinding;
    private String title;

    public WidgetTopBar(Context context) {
        this(context, null);
    }

    public WidgetTopBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetTopBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setTitle(String title) {
        widgetTopbarBinding.setTitle(title);
    }

    public void setTitle(@StringRes int title) {
        widgetTopbarBinding.tvTitle.setText(title);
    }

    public void hidenBackButton() {
        widgetTopbarBinding.ivBack.setVisibility(GONE);
    }

    protected void init(@Nullable AttributeSet attrs) {
        widgetTopbarBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.widget_topbar, this, true);

        RxView.clicks(widgetTopbarBinding.ivBack)
                .throttleFirst(1500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> widgetTopbarBinding.ivBack.setClickable(false))
                .doOnComplete(() -> widgetTopbarBinding.ivBack.setClickable(true))
                .doOnError((error) -> widgetTopbarBinding.ivBack.setClickable(true))
                .subscribe((next) -> ((Activity) getContext()).onBackPressed());

        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Topbar, 0, 0);
            title = typedArray.getString(R.styleable.Topbar_setTitle);
        }
        if (title != null) {
            widgetTopbarBinding.tvTitle.setText(title);
        }
    }
}
