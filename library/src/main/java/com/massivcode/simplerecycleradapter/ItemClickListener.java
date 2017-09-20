package com.massivcode.simplerecycleradapter;

import android.view.View;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 09:48
 */

public interface ItemClickListener {
    void onItemClick(View view, int position, Item item);
}
