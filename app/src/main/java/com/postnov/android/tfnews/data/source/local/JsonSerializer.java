package com.postnov.android.tfnews.data.source.local;

import com.google.gson.Gson;
import com.postnov.android.tfnews.data.entity.News;

/**
 * Created by platon on 01.11.2016.
 */

public class JsonSerializer implements ISerializer<News> {

    private final Gson gson = new Gson();

    @Override
    public String serialize(News news) {
        return gson.toJson(news);
    }

    @Override
    public News deserialize(String string) {
        return gson.fromJson(string, News.class);
    }
}
