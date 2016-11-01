package com.postnov.android.tfnews.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by platon on 01.11.2016.
 */

public class AbstractDate {
    @SerializedName("milliseconds")
    private long date;

    public long getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractDate that = (AbstractDate) o;
        return date == that.date;
    }

    @Override
    public int hashCode() {
        return (int) (date ^ (date >>> 32));
    }

    @Override
    public String toString() {
        return "AbstractDate{" +
                "date=" + date +
                '}';
    }
}
