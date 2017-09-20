package com.massivcode.simplerecycleradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 10:44
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mViewType;
    private ClickEventBus mClickEventBus;

    public BaseViewHolder(final View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView);
        mViewType = viewType;
        mClickEventBus = clickEventBus;
    }

    public void onItemClicked(View view, int position) {
        if (mClickEventBus != null) {
            mClickEventBus.onItemClicked(mViewType, view, position);
        }
    }

    public abstract void onBindItem(Item item, int position);
}
