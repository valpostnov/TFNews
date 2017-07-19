package com.postnov.android.tfnews.news;

import com.postnov.android.tfnews.news.interfaces.INewsPresenter;
import com.postnov.android.tfnews.news.interfaces.NewsView;

import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by platon on 01.11.2016.
 */

public class NewsPresenter implements INewsPresenter {

    private NewsView newsView;
    private final GetNewsInteractor getNewsInteractor;

    public NewsPresenter(GetNewsInteractor getNewsInteractor) {
        this.getNewsInteractor = getNewsInteractor;
    }

    @Override
    public void bind(NewsView view) {
        newsView = view;
        bindIntents();
    }

    @Override
    public void unbind() {
        newsView = null;
    }

    @Override
    public void bindIntents() {
        newsView.loadNewsIntent()
                .flatMap(getNewsInteractor::execute)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsView::render);
    }
}
