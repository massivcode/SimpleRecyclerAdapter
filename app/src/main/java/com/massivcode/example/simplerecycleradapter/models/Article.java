package com.massivcode.example.simplerecycleradapter.models;

import com.massivcode.example.simplerecycleradapter.R;
import com.massivcode.example.simplerecycleradapter.ViewTypes;
import com.massivcode.simplerecycleradapter.Item;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:31
 */

public class Article implements Item {
    private String title, author, date;
    private int hits;

    public Article(String title, String author, String date, int hits) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.hits = hits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHits() {
        return hits + " hits";
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    @Override
    public int getViewType() {
        return ViewTypes.ARTICLE;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.item_article;
    }

    @Override
    public String toString() {
        return "Article{" + "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", hits=" + hits +
                '}';
    }
}
