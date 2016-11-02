package com.postnov.android.tfnews.newscontent;

import com.postnov.android.tfnews.data.entity.NewsContent;
import com.postnov.android.tfnews.data.source.IDataSource;
import com.postnov.android.tfnews.newscontent.interfaces.INewsContentPresenter;
import com.postnov.android.tfnews.newscontent.interfaces.NewsContentView;
import com.postnov.android.tfnews.util.INetworkManager;
import com.postnov.android.tfnews.util.NetworkConnectionException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsContentPresenter implements INewsContentPresenter {

    private NewsContentView newsContentView;
    private final CompositeSubscription subscription;
    private final IDataSource repository;
    private final INetworkManager networkManager;

    public NewsContentPresenter(IDataSource repository, INetworkManager networkManager) {
        subscription = new CompositeSubscription();
        this.repository = repository;
        this.networkManager = networkManager;
    }

    @Override
    public void fetchContent(int id) {
        if (!networkManager.networkIsAvailable()) {
            onError.call(new NetworkConnectionException());
        } else {
            subscription.add(repository.getContent(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError));
        }
    }

    @Override
    public void bind(NewsContentView view) {
        newsContentView = view;
    }

    @Override
    public void unbind() {
        subscription.clear();
        newsContentView = null;
    }

    private final Action1<NewsContent> onNext = content -> {
        newsContentView.showProgressView(false);
        newsContentView.showContent(content);
    };

    private final Action1<Throwable> onError = e -> {
        newsContentView.showProgressView(false);
        newsContentView.showError(e);
    };
}
