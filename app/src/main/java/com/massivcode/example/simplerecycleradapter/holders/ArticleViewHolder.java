package com.massivcode.example.simplerecycleradapter.holders;

import android.view.View;
import android.widget.TextView;

import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.models.Article;
import com.massivcode.simplerecycleradapter.BaseViewHolder;
import com.massivcode.simplerecycleradapter.ClickEventBus;
import com.massivcode.simplerecycleradapter.Item;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:50
 */

public class ArticleViewHolder extends BaseViewHolder {
    private TextView mTitleTextView, mAuthorTextView, mDateTextView, mHitsTextView;

    public ArticleViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView, viewType, clickEventBus);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_tv);
        mAuthorTextView = (TextView) itemView.findViewById(R.id.author_tv);
        mDateTextView = (TextView) itemView.findViewById(R.id.date_tv);
        mHitsTextView = (TextView) itemView.findViewById(R.id.hits_tv);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindItem(Item item, int position) {
        Article article = (Article) item;

        mTitleTextView.setText(article.getTitle());
        mAuthorTextView.setText(article.getAuthor());
        mDateTextView.setText(article.getDate());
        mHitsTextView.setText(article.getHits());
    }
}
