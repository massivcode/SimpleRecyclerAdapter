package com.massivcode.simplerecycleradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
 MIT License
 Copyright (c) 2017 massivcode@gmail.com

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    // The ViewHolder's ViewType to check itemClickListener
    private int mViewType;
    // The ClickEventBus to handle views click event
    private ClickEventBus mClickEventBus;

    /**
     * The Constructor
     *
     * @param itemView The ItemView
     * @param viewType The ViewHolder's ViewType to check itemClickListener
     * @param clickEventBus The ClickEventBus to handle views click event
     */
    public BaseViewHolder(final View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView);
        mViewType = viewType;
        mClickEventBus = clickEventBus;
    }

    /**
     * Call this method when item clicked
     *
     * @param view The clicked View
     * @param position The clicked Position
     */
    public void onItemClicked(View view, int position) {
        if (mClickEventBus != null) {
            mClickEventBus.onItemClicked(mViewType, view, position);
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param item The data object to set
     * @param position The item position
     */
    public abstract void onBindItem(Item item, int position);
}
