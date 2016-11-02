package com.postnov.android.tfnews.util;

/**
 * Created by platon on 02.11.2016.
 */

public class NetworkConnectionException extends Exception {

    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(String message) {
        super(message);
    }

    public NetworkConnectionException(Throwable cause) {
        super(cause);
    }
}
