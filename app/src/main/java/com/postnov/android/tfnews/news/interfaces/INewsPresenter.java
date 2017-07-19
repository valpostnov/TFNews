package com.postnov.android.tfnews.news.interfaces;

import com.postnov.android.tfnews.base.BasePresenter;

/**
 * Created by platon on 01.11.2016.
 */

public interface INewsPresenter extends BasePresenter<NewsView> {
    void bindIntents();
}
