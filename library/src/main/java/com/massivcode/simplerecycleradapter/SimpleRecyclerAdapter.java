package com.massivcode.simplerecycleradapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
public abstract class SimpleRecyclerAdapter<ITEM extends Item> extends RecyclerView.Adapter<BaseViewHolder> {
    // The objects to represent in the RecyclerView.
    private List<ITEM> mItems;

    // The Container with ViewType and ItemLayoutResourceId
    private SparseIntArray mViewTypeResPairs = new SparseIntArray();

    // The Container with ViewType and ItemClickListener
    private SparseArray<ItemClickListener<ITEM>> mItemClickListeners = new SparseArray<>();

    /**
     * The EventListener to handle each view holders click event
     */
    private ClickEventBus mClickEventBus = new ClickEventBus() {
        @Override
        public void onItemClicked(int viewType, View view, int position) {
            ItemClickListener<ITEM> itemClickListener = getItemClickListener(viewType);

            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, position, getItem(position));
            }
        }
    };

    /**
     * Constructor
     * @param items The objects to represent in the RecyclerView.
     */
    public SimpleRecyclerAdapter(List<ITEM> items) {
        this.mItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        ITEM item = getItem(position);
        int viewType = item.getViewType();
        int layoutResourceId = item.getLayoutResourceId();

        if (mViewTypeResPairs.get(viewType) == 0) {
            mViewTypeResPairs.put(viewType, layoutResourceId);
        }

        return viewType;
    }

    /**
     *
     * @param viewType The ViewType to specify view holder
     * @param listener The ItemClickListener to handle specified view holder's click event
     */
    public void setOnItemClickListener(int viewType, ItemClickListener<ITEM> listener) {
        mItemClickListeners.put(viewType, listener);
    }

    private ItemClickListener<ITEM> getItemClickListener(int viewType) {
        return mItemClickListeners.get(viewType);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext()).inflate(mViewTypeResPairs.get(viewType), parent, false), viewType, mClickEventBus);
    }

    /**
     *
     * Called when {@link RecyclerView} needs a new RecyclerView.ViewHolder of the given type to represent an item.
     *
     * @param itemView The View to inflate views
     * @param viewType The ViewType to specify view holder
     * @param clickEventBus The EventListener to handle each view holders click event
     * @return Created ViewHolder that extends BaseViewHolder
     */
    public abstract BaseViewHolder onCreateViewHolder(View itemView, int viewType, ClickEventBus clickEventBus);


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindItem(getItem(position), position);
    }

    /**
     * Get the data item associated with the specified position in the data set.
     * @param position Position of the item whose data we want within the adapter's data set.
     * @return The data at the specified position.
     */
    public ITEM getItem(int position) {
        return mItems.get(position);
    }

    /**
     * The Method to replace data source
     * @param items The new objects to represent in the RecyclerView.
     */
    public void refreshItems(List<ITEM> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    /**
     * The Method to add item object into data source
     * @param item The object that will be added into data source
     */
    public void addItem(ITEM item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    /**
     * The Method to add item object into data source's specified position
     * @param item The object that will be added into data source
     * @param position The position to which data object will be added into data source
     */
    public void addItem(ITEM item, int position) {
        int oldLastPosition = mItems.size() - 1;

        if (oldLastPosition == position) {
            addItem(item);
        } else {
            mItems.add(position, item);
            notifyItemInserted(position);
        }
    }

    /**
     * The Method to add item objects into data source
     * @param items The objects that will be added into data source
     */
    public void addItems(List<ITEM> items) {
        int oldLastPosition = mItems.size() - 1;

        mItems.addAll(items);
        notifyItemRangeInserted(oldLastPosition, mItems.size() - 1);
    }

    /**
     * The Method to add item objects into data source's specified position
     * @param items The objects that will be added into data source
     * @param position The position to which data objects will be added into data source
     */
    public void addItems(List<ITEM> items, int position) {
        int oldLastPosition = mItems.size() - 1;

        if (oldLastPosition == position) {
            addItems(items);
        } else {
            mItems.addAll(position, items);
            notifyItemRangeInserted(position, position + items.size() - 1);
        }
    }

    /**
     * The Method to remove item that specified position
     * @param position The position to which will be removed from data source
     */
    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * The Method to update item that specified position
     * @param item The Item that replace old item at position
     * @param position The position to witch will be update from data source
     */
    public void updateItem(ITEM item, int position) {
        mItems.remove(position);
        mItems.add(position, item);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

}
