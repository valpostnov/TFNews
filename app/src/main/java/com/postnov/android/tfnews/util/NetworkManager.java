package com.postnov.android.tfnews.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by platon on 02.11.2016.
 */

public class NetworkManager implements INetworkManager {
    private ConnectivityManager connectivityManager;

    public NetworkManager(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean networkIsAvailable() {
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        return (activeInfo != null && activeInfo.isConnected());
    }
}
