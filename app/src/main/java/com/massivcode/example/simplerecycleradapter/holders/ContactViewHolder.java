package com.massivcode.example.simplerecycleradapter.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.models.Contact;
import com.massivcode.simplerecycleradapter.BaseViewHolder;
import com.massivcode.simplerecycleradapter.ClickEventBus;
import com.massivcode.simplerecycleradapter.Item;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:50
 */

public class ContactViewHolder extends BaseViewHolder {
    private TextView mNameTextView, mPhoneNumberTextView;

    public ContactViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView, viewType, clickEventBus);

        mNameTextView = (TextView) itemView.findViewById(R.id.name_tv);
        mPhoneNumberTextView = (TextView) itemView.findViewById(R.id.phoneNumber_tv);
        ImageView profileImageView = (ImageView) itemView.findViewById(R.id.profile_iv);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindItem(Item item, int position) {
        Contact contact = (Contact) item;

        mNameTextView.setText(contact.getName());
        mPhoneNumberTextView.setText(contact.getPhoneNumber());
    }
}
