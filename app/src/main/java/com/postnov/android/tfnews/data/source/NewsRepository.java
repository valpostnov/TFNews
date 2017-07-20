package com.postnov.android.tfnews.data.source;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.entity.NewsContent;
import com.postnov.android.tfnews.data.source.local.ICache;

import rx.Observable;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsRepository implements IDataSource {

    private IDataSource remoteDataSource;
    private ICache<News> newsCache;
    private SparseArray<NewsContent> contentCache;

    public NewsRepository(ICache<News> newsCache, IDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.newsCache = newsCache;
        contentCache = new SparseArray<>();
    }

    @Override
    public Observable<News> getNews() {

        return getNewsFromCache().concatWith(getNewsFromRemote());
    }

    @Override
    public Observable<NewsContent> getContent(int id) {
        NewsContent newsContent = contentCache.get(id);
        if (newsContent != null) {
            return Observable.just(newsContent);
        }

        return remoteDataSource.getContent(id)
                .doOnNext(content -> contentCache.put(id, content));
    }

    @NonNull
    private Observable<News> getNewsFromRemote() {
        return remoteDataSource.getNews().doOnNext(news -> newsCache.put(news));
    }

    @NonNull
    private Observable<News> getNewsFromCache() {
        return Observable.just(newsCache.get());
    }
}
