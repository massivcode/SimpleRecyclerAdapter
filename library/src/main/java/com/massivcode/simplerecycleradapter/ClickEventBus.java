package com.massivcode.simplerecycleradapter;

import android.view.View;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:13
 */

public interface ClickEventBus {
    void onItemClicked(int viewType, View view, int position);
}
