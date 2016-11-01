package com.postnov.android.tfnews.newscontent.interfaces;

import com.postnov.android.tfnews.base.BasePresenter;

/**
 * Created by platon on 01.11.2016.
 */

public interface INewsContentPresenter extends BasePresenter<NewsContentView> {
    void fetchContent(int id);
}
