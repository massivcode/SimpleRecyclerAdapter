package com.massivcode.example.simplerecycleradapter.models;

import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.ViewTypes;
import com.massivcode.simplerecycleradapter.Item;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:30
 */

public class Contact implements Item {
    private String name, phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getViewType() {
        return ViewTypes.CONTACT;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.item_contact;
    }

    @Override
    public String toString() {
        return "Contact{" + "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
