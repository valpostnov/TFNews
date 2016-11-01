package com.postnov.android.tfnews.newscontent.interfaces;

import com.postnov.android.tfnews.base.BaseView;
import com.postnov.android.tfnews.data.entity.NewsContent;

/**
 * Created by platon on 01.11.2016.
 */

public interface NewsContentView extends BaseView {
    void showContent(NewsContent content);
}
