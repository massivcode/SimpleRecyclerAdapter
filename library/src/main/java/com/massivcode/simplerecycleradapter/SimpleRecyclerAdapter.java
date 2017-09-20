package com.massivcode.simplerecycleradapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 09:36
 */

public abstract class SimpleRecyclerAdapter<ITEM extends Item> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<ITEM> mItems;
    private SparseIntArray mConfigs = new SparseIntArray();
    private SparseArray<ItemClickListener> mItemClickListeners = new SparseArray<>();

    private ClickEventBus mClickEventBus = new ClickEventBus() {
        @Override
        public void onItemClicked(int viewType, View view, int position) {
            ItemClickListener itemClickListener = getItemClickListener(viewType);

            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, position, getItem(position));
            }
        }
    };

    public SimpleRecyclerAdapter(List<ITEM> mItems) {
        this.mItems = mItems;
    }

    @Override
    public int getItemViewType(int position) {
        ITEM item = getItem(position);
        int viewType = item.getViewType();
        int layoutResourceId = item.getLayoutResourceId();

        if (mConfigs.get(viewType) == 0) {
            mConfigs.put(viewType, layoutResourceId);
        }

        return viewType;
    }

    public void setOnItemClickListener(int viewType, ItemClickListener listener) {
        mItemClickListeners.put(viewType, listener);
    }

    private ItemClickListener getItemClickListener(int viewType) {
        return mItemClickListeners.get(viewType);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext()).inflate(mConfigs.get(viewType), parent, false), viewType, mClickEventBus);
    }

    public abstract BaseViewHolder onCreateViewHolder(View itemView, int viewType, ClickEventBus clickEventBus);


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindItem(getItem(position), position);
    }

    public ITEM getItem(int position) {
        return mItems.get(position);
    }

    public void refreshItems(List<ITEM> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void addItem(ITEM item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addItem(ITEM item, int position) {
        int oldLastPosition = mItems.size() - 1;

        if (oldLastPosition == position) {
            addItem(item);
        } else {
            mItems.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void addItems(List<ITEM> items) {
        int oldLastPosition = mItems.size() - 1;

        mItems.addAll(items);
        notifyItemRangeInserted(oldLastPosition, mItems.size() - 1);
    }

    public void addItems(List<ITEM> items, int position) {
        int oldLastPosition = mItems.size() - 1;

        if (oldLastPosition == position) {
            addItems(items);
        } else {
            mItems.addAll(position, items);
            notifyItemRangeInserted(position, position + items.size() - 1);
        }
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

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
