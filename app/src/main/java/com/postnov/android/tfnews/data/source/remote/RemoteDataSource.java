package com.postnov.android.tfnews.data.source.remote;

import com.postnov.android.tfnews.api.NewsApi;
import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.entity.NewsContent;
import com.postnov.android.tfnews.data.source.IDataSource;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by platon on 01.11.2016.
 */

public class RemoteDataSource implements IDataSource {
    private static final String ENDPOINT = "https://api.tinkoff.ru/v1/";
    private NewsApi api;

    public RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(NewsApi.class);
    }

    @Override
    public Observable<News> getNews() {
        return api.newsList();
    }

    @Override
    public Observable<NewsContent> getContent(int id) {
        return api.newsContent(id);
    }
}
