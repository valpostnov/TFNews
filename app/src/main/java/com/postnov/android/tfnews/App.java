package com.postnov.android.tfnews;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.source.IDataSource;
import com.postnov.android.tfnews.data.source.NewsRepository;
import com.postnov.android.tfnews.data.source.local.ICache;
import com.postnov.android.tfnews.data.source.local.JsonSerializer;
import com.postnov.android.tfnews.data.source.local.NewsCache;
import com.postnov.android.tfnews.data.source.remote.RemoteDataSource;

import timber.log.Timber;

/**
 * Created by platon on 01.11.2016.
 */

public class App extends Application {

    private IDataSource repository;
    private IDataSource remoteDataSource;
    private ICache<News> newsCache;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public static App get(Fragment fragment) {
        return (App) fragment.getContext().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        newsCache = new NewsCache(getCacheDir(), new JsonSerializer());
        remoteDataSource = new RemoteDataSource();
        repository = new NewsRepository(newsCache, remoteDataSource);
    }

    public IDataSource repository() {
        return repository;
    }

    public IDataSource remoteDataSource() {
        return remoteDataSource;
    }

    public ICache<News> newsCache() {
        return newsCache;
    }
}
