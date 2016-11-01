package com.postnov.android.tfnews.news.interfaces;

import com.postnov.android.tfnews.base.BaseView;
import com.postnov.android.tfnews.data.entity.News;

/**
 * Created by platon on 01.11.2016.
 */

public interface NewsView extends BaseView {
    void showNews(News news);
}
