package com.postnov.android.tfnews.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by platon on 01.11.2016.
 */

public class News {

    @SerializedName("resultCode")
    private String resultCode;

    @SerializedName("payload")
    private List<Payload> payload;

    public String getResultCode() {
        return resultCode;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!resultCode.equals(news.resultCode)) return false;
        return payload.equals(news.payload);

    }

    @Override
    public int hashCode() {
        int result = resultCode.hashCode();
        result = 31 * result + payload.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "resultCode='" + resultCode + '\'' +
                ", payload=" + payload +
                '}';
    }
}
