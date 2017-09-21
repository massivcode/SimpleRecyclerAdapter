package com.massivcode.example.simplerecycleradapter;

import android.view.View;

import com.massivcode.example.simplerecycleradapter.holders.ArticleViewHolder;
import com.massivcode.example.simplerecycleradapter.holders.ContactViewHolder;
import com.massivcode.example.simplerecycleradapter.holders.MemoViewHolder;
import com.massivcode.simplerecycleradapter.BaseViewHolder;
import com.massivcode.simplerecycleradapter.ClickEventBus;
import com.massivcode.simplerecycleradapter.Item;
import com.massivcode.simplerecycleradapter.SimpleRecyclerAdapter;

import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 21. 09:00
 */

public class TestAdapter extends SimpleRecyclerAdapter<Item> {

    public TestAdapter(List<Item> mItems) {
        super(mItems);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        BaseViewHolder viewHolder = null;

        switch (viewType) {
            case ViewTypes.ARTICLE:
                viewHolder = new ArticleViewHolder(itemView, viewType, clickEventBus);
                break;
            case ViewTypes.CONTACT:
                viewHolder = new ContactViewHolder(itemView, viewType, clickEventBus);
                break;
            case ViewTypes.MEMO:
                viewHolder = new MemoViewHolder(itemView, viewType, clickEventBus);
                break;
        }

        return viewHolder;
    }
}
