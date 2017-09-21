package com.massivcode.example.simplerecycleradapter.models;

import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.ViewTypes;
import com.massivcode.simplerecycleradapter.Item;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:30
 */

public class Memo implements Item {
    private String title, contents;

    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public int getViewType() {
        return ViewTypes.MEMO;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.item_memo;
    }

    @Override
    public String toString() {
        return "Memo{" + "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
