package com.postnov.android.tfnews.base;

/**
 * Created by platon on 01.11.2016.
 */

public interface BaseView {
    void showError(Throwable throwable);
    void showProgressView(boolean show);
}
