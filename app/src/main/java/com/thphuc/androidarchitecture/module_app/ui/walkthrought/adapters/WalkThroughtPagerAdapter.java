package com.thphuc.androidarchitecture.module_app.ui.walkthrought.adapters;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

import com.thphuc.androidarchitecture.R;
import com.thphuc.androidarchitecture.databinding.ItemWalkthroughtBinding;
import com.thphuc.androidarchitecture.module_commons.base.AbsBindingAdapter;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class WalkThroughtPagerAdapter extends AbsBindingAdapter<ItemWalkthroughtBinding> {
    public WalkThroughtPagerAdapter() {
        super(null);
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        return R.layout.item_walkthrought;
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (binding instanceof ItemWalkthroughtBinding) {
            ItemWalkthroughtBinding itemBind = (ItemWalkthroughtBinding) binding;
            Context context = binding.getRoot().getContext();
            if (position == 0) {
                itemBind.ivWalkthrought.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bg_walkthrough_one));
                itemBind.setTitle("Get The First");
                itemBind.setDescription("Movie & TV information");
            } else if (position == 1) {
                itemBind.ivWalkthrought.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bg_walkthrough_two));
                itemBind.setTitle("Know The Movie");
                itemBind.setDescription("Is Not Worth Watching");
            } else {
                itemBind.ivWalkthrought.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bg_walkthrough_three));
                itemBind.setTitle("Realtime");
                itemBind.setDescription("Updates Movie Trailer");
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
