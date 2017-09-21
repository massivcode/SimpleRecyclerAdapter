package com.massivcode.example.simplerecycleradapter;

import android.support.annotation.IntDef;

import static com.massivcode.example.simplerecycleradapter.ViewTypes.ARTICLE;
import static com.massivcode.example.simplerecycleradapter.ViewTypes.CONTACT;
import static com.massivcode.example.simplerecycleradapter.ViewTypes.MEMO;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:52
 */

@IntDef({ARTICLE, CONTACT, MEMO})
public @interface ViewTypes {
    int ARTICLE = 0;
    int CONTACT = 1;
    int MEMO = 2;
}
