package com.postnov.android.tfnews.api;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.entity.NewsContent;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by platon on 01.11.2016.
 */

public interface NewsApi {
    @GET("news")
    Observable<News> newsList();

    @GET("news_content")
    Observable<NewsContent> newsContent(@Query("id") int id);
}
