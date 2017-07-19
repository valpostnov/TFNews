package com.postnov.android.tfnews.news;

import com.postnov.android.tfnews.data.source.IDataSource;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author Valentin Postnov
 */
public class GetNewsInteractor {

    private final IDataSource newsRepository;

    public GetNewsInteractor(IDataSource newsRepository) {
        this.newsRepository = newsRepository;
    }

    Observable<NewsViewSate> execute(Object o) {
        return newsRepository.getNews()
                .subscribeOn(Schedulers.io())
                .map(NewsViewSate.DataState::new)
                .cast(NewsViewSate.class)
                .startWith(new NewsViewSate.LoadingState())
                .onErrorReturn(NewsViewSate.ErrorState::new);
    }
}
