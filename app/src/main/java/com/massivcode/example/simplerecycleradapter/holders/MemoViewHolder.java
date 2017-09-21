package com.massivcode.example.simplerecycleradapter.holders;

import android.view.View;
import android.widget.TextView;

import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.models.Memo;
import com.massivcode.simplerecycleradapter.BaseViewHolder;
import com.massivcode.simplerecycleradapter.ClickEventBus;
import com.massivcode.simplerecycleradapter.Item;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:50
 */

public class MemoViewHolder extends BaseViewHolder {
    private TextView mTitleTextView, mContentsTextView;

    public MemoViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView, viewType, clickEventBus);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_tv);
        mContentsTextView = (TextView) itemView.findViewById(R.id.contents_tv);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindItem(Item item, int position) {
        Memo memo = (Memo) item;

        mTitleTextView.setText(memo.getTitle());
        mContentsTextView.setText(memo.getContents());
    }
}
