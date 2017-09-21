package com.massivcode.example.simplerecycleradapter.models.utils;

import com.massivcode.example.simplerecycleradapter.models.Article;
import com.massivcode.example.simplerecycleradapter.models.Contact;
import com.massivcode.example.simplerecycleradapter.models.Memo;
import com.massivcode.simplerecycleradapter.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017. 9. 20. 13:31
 */

public enum  DummyDataGenerator {
    INSTANCE;

    public List<Item> generateArticleDummies(int dummyCount) {
        List<Item> results = new ArrayList<>();

        for (int i = 0; i < dummyCount; i ++) {
            results.add(new Article("title " + i, "author " + i, "2017.09.21", i));
        }

        return results;
    }

    public List<Item> generateContactDummies(int dummyCount) {
        List<Item> results = new ArrayList<>();

        for (int i = 0; i < dummyCount; i++) {
            results.add(new Contact("name " + i, "1234-567" + i));
        }

        return results;
    }

    public List<Item> generateMemoDummies(int dummyCount) {
        List<Item> results = new ArrayList<>();

        for (int i = 0; i < dummyCount; i++) {
            results.add(new Memo("title " + i, "contents " + i));
        }

        return results;
    }

    public List<Item> generateArticleContactDummies() {
        List<Item> results = generateArticleDummies(3);
        results.addAll(generateContactDummies(3));

        return results;
    }

    public List<Item> generateContactMemoDummies() {
        List<Item> results = generateContactDummies(3);
        results.addAll(generateMemoDummies(3));

        return results;
    }

    public List<Item> generateArticleContactMemoDummies() {
        List<Item> results = generateArticleDummies(2);
        results.addAll(generateContactDummies(2));
        results.addAll(generateMemoDummies(2));

        return results;
    }

}
