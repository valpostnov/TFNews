package com.postnov.android.tfnews.base;

/**
 * Created by platon on 01.11.2016.
 */

public interface BasePresenter<T extends BaseView> {
    void bind(T view);
    void unbind();
}
