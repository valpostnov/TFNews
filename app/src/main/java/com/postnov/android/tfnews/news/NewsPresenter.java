package com.postnov.android.tfnews.news;

import com.postnov.android.tfnews.news.interfaces.INewsPresenter;
import com.postnov.android.tfnews.news.interfaces.NewsView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by platon on 01.11.2016.
 */

public class NewsPresenter extends INewsPresenter {

    private final GetNewsInteractor getNewsInteractor;

    public NewsPresenter(GetNewsInteractor getNewsInteractor) {
        this.getNewsInteractor = getNewsInteractor;
    }

    @Override
    public void bindIntents() {
        Observable<NewsViewSate> loadNews = generateObservable(NewsView::loadNewsIntent);
        Observable<NewsViewSate> refreshNews = generateObservable(NewsView::refreshIntent);
        subscribe(loadNews.mergeWith(refreshNews), NewsView::render);
    }

    private Observable<NewsViewSate> generateObservable(ViewIntentBinder<NewsView, Void> binder) {
        return intent(binder)
                .switchMap(getNewsInteractor::execute)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
