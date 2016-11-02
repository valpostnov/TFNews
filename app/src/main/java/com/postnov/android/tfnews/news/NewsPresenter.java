package com.postnov.android.tfnews.news;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.source.IDataSource;
import com.postnov.android.tfnews.news.interfaces.INewsPresenter;
import com.postnov.android.tfnews.news.interfaces.NewsView;
import com.postnov.android.tfnews.util.INetworkManager;
import com.postnov.android.tfnews.util.NetworkManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.postnov.android.tfnews.util.INetworkManager.CONNECTION_ERR;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsPresenter implements INewsPresenter {

    private final CompositeSubscription subscription;
    private final IDataSource repository;
    private final INetworkManager networkManager;
    private NewsView newsView;

    public NewsPresenter(IDataSource repository, INetworkManager networkManager) {
        subscription = new CompositeSubscription();
        this.repository = repository;
        this.networkManager = networkManager;
    }

    @Override
    public void bind(NewsView view) {
        newsView = view;
    }

    @Override
    public void unbind() {
        subscription.clear();
        newsView = null;
    }

    @Override
    public void fetchNews() {
        if (!networkManager.networkIsAvailable()) {
            onError.call(new Exception(NetworkManager.CONNECTION_ERR));
        }

        subscription.add(repository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError));
    }

    private final Action1<News> onNext = news -> {
        newsView.showProgressView(false);
        newsView.showNews(news);
    };

    private final Action1<Throwable> onError = e -> {
        newsView.showProgressView(false);
        newsView.showError(e.getMessage());
    };
}
