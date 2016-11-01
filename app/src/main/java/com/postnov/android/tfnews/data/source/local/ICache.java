package com.postnov.android.tfnews.data.source.local;

import java.util.List;

/**
 * Created by platon on 01.11.2016.
 */

public interface ICache<T> {
    T get();

    void put(T object);

    boolean delete();

    boolean isEmpty();
}
