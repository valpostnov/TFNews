package com.postnov.android.tfnews.news.interfaces;

import com.postnov.android.tfnews.base.BaseView;
import com.postnov.android.tfnews.news.NewsViewSate;

import rx.Observable;

/**
 * Created by platon on 01.11.2016.
 */

public interface NewsView extends BaseView {

    Observable<Object> loadNewsIntent();

    void render(NewsViewSate newsViewSate);
}
