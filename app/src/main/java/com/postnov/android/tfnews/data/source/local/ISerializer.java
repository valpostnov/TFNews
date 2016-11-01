package com.postnov.android.tfnews.data.source.local;

/**
 * Created by platon on 01.11.2016.
 */

public interface ISerializer<T> {
    String serialize(T object);

    T deserialize(String string);
}
