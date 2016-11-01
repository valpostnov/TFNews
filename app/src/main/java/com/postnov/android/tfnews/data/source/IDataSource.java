package com.postnov.android.tfnews.data.source;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.entity.NewsContent;

import rx.Observable;

/**
 * Created by platon on 01.11.2016.
 */

public interface IDataSource {
    Observable<News> getNews();
    Observable<NewsContent> getContent(int id);
}
