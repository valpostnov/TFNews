package com.postnov.android.tfnews.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsContent {

    @SerializedName("resultCode")
    private String resultCode;

    @SerializedName("payload")
    private Supplement payload;

    @SerializedName("content")
    private String content;

    @SerializedName("bankInfoTypeId")
    private int bankInfoTypeId;

    @SerializedName("typeId")
    private String typeId;

    public String getResultCode() {
        return resultCode;
    }

    public Supplement getPayload() {
        return payload;
    }

    public String getContent() {
        return content;
    }

    public int getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public String getTypeId() {
        return typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsContent that = (NewsContent) o;

        if (bankInfoTypeId != that.bankInfoTypeId) return false;
        if (!resultCode.equals(that.resultCode)) return false;
        if (!payload.equals(that.payload)) return false;
        if (!content.equals(that.content)) return false;
        return typeId.equals(that.typeId);

    }

    @Override
    public int hashCode() {
        int result = resultCode.hashCode();
        result = 31 * result + payload.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + bankInfoTypeId;
        result = 31 * result + typeId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NewsContent{" +
                "resultCode='" + resultCode + '\'' +
                ", payload=" + payload +
                ", content='" + content + '\'' +
                ", bankInfoTypeId=" + bankInfoTypeId +
                ", typeId='" + typeId + '\'' +
                '}';
    }

    private static class Supplement {
        @SerializedName("title")
        Payload title;

        @SerializedName("creationDate")
        AbstractDate creationDate;

        @SerializedName("lastModificationDate")
        AbstractDate lastModificationDate;

        public Payload getTitle() {
            return title;
        }

        public AbstractDate getCreationDate() {
            return creationDate;
        }

        public AbstractDate getLastModificationDate() {
            return lastModificationDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Supplement that = (Supplement) o;

            if (!title.equals(that.title)) return false;
            if (!creationDate.equals(that.creationDate)) return false;
            return lastModificationDate.equals(that.lastModificationDate);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + creationDate.hashCode();
            result = 31 * result + lastModificationDate.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Supplement{" +
                    "title=" + title +
                    ", creationDate=" + creationDate +
                    ", lastModificationDate=" + lastModificationDate +
                    '}';
        }
    }
}
