package com.postnov.android.tfnews.util;

/**
 * Created by platon on 02.11.2016.
 */

public interface INetworkManager {
    String CONNECTION_ERR = "Отсутствует подключение к сети";
    boolean networkIsAvailable();
}
