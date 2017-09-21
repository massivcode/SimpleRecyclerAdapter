package com.massivcode.example.simplerecycleradapter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.massivcode.example.simplerecycleradapter.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.article_btn).setOnClickListener(this);
        findViewById(R.id.contact_btn).setOnClickListener(this);
        findViewById(R.id.memo_btn).setOnClickListener(this);
        findViewById(R.id.article_contact_btn).setOnClickListener(this);
        findViewById(R.id.contact_memo_btn).setOnClickListener(this);
        findViewById(R.id.article_contact_memo_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        launchActivity(view);
    }

    private void launchActivity(View view) {
        startActivity(new Intent(MainActivity.this, TestActivity.class).putExtra("dataType", view.getId()));
    }
}
