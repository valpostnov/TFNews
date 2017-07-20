package com.postnov.android.tfnews.newscontent.interfaces;

import com.postnov.android.tfnews.base.BasePresenter;
import com.postnov.android.tfnews.news.NewsContentViewState;

/**
 * Created by platon on 01.11.2016.
 */

public abstract class INewsContentPresenter extends BasePresenter<NewsContentView, NewsContentViewState> {
    public abstract void fetchContent(int id);
}
