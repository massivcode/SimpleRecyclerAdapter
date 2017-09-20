package com.massivcode.simplerecycleradapter;

import android.support.annotation.LayoutRes;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 09:45
 */

public interface Item {
    int getViewType();

    @LayoutRes
    int getLayoutResourceId();
}
