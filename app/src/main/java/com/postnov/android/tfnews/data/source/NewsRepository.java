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

    private IDataSource remote;
    private ICache<News> cache;
    private SparseArray<NewsContent> contentCache;

    public NewsRepository(ICache<News> cache, IDataSource remote) {
        this.remote = remote;
        this.cache = cache;
        contentCache = new SparseArray<>();
    }

    @Override
    public Observable<News> getNews() {

        if (cache.isEmpty()) {
            return getNewsFromRemote();
        }

        return remote.getNews()
                .filter(rem -> !rem.equals(cache.get()))
                .doOnNext(news -> cache.put(news))
                .switchIfEmpty(getNewsFromCache());
    }

    @Override
    public Observable<NewsContent> getContent(int id) {
        NewsContent nc = contentCache.get(id);
        if (nc != null) {
            return Observable.just(nc);
        }

        return remote.getContent(id)
                .doOnNext(newsContent -> contentCache.put(id, newsContent));
    }

    @NonNull
    private Observable<News> getNewsFromRemote() {
        return remote.getNews().doOnNext(news -> cache.put(news));
    }

    @NonNull
    private Observable<News> getNewsFromCache() {
        return Observable.just(cache.get());
    }
}
