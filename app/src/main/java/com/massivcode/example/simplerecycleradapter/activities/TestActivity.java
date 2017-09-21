package com.massivcode.example.simplerecycleradapter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.massivcode.example.simplerecycleradapter.models.utils.DummyDataGenerator;
import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.TestAdapter;
import com.massivcode.example.simplerecycleradapter.ViewTypes;
import com.massivcode.simplerecycleradapter.Item;
import com.massivcode.simplerecycleradapter.ItemClickListener;

import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 21. 09:14
 */

public class TestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        Intent intent = getIntent();
        int dataType = intent.getIntExtra("dataType", -1);

        switch (dataType) {
            case R.id.article_btn:
                setup(DummyDataGenerator.INSTANCE.generateArticleDummies(6));
                break;
            case R.id.contact_btn:
                setup(DummyDataGenerator.INSTANCE.generateContactDummies(6));
                break;
            case R.id.memo_btn:
                setup(DummyDataGenerator.INSTANCE.generateMemoDummies(6));
                break;
            case R.id.article_contact_btn:
                setup(DummyDataGenerator.INSTANCE.generateArticleContactDummies());
                break;
            case R.id.contact_memo_btn:
                setup(DummyDataGenerator.INSTANCE.generateContactMemoDummies());
                break;
            case R.id.article_contact_memo_btn:
                setup(DummyDataGenerator.INSTANCE.generateArticleContactMemoDummies());
                break;
        }
    }

    public void setup(List<Item> items) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        TestAdapter testAdapter = new TestAdapter(items);

        testAdapter.setOnItemClickListener(ViewTypes.CONTACT, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Item item) {
                int viewId = view.getId();

                if (viewId == R.id.profile_iv) {
                    // Profile ImageView Clicked
                    System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
                } else {
                    // ItemView Clicked
                    System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
                }
            }
        });

        testAdapter.setOnItemClickListener(ViewTypes.ARTICLE, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Item item) {
                System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
            }
        });

        testAdapter.setOnItemClickListener(ViewTypes.MEMO, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Item item) {
                System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
            }
        });

        mRecyclerView.setAdapter(testAdapter);
    }

}
