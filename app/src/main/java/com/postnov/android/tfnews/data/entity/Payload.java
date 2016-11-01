package com.postnov.android.tfnews.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by platon on 01.11.2016.
 */

public class Payload {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("text")
    private String text;

    @SerializedName("publicationDate")
    private AbstractDate publicationDate;

    @SerializedName("bankInfoTypeId")
    private int bankInfoTypeId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public AbstractDate getPublicationDate() {
        return publicationDate;
    }

    public int getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payload payload = (Payload) o;

        if (id != payload.id) return false;
        if (bankInfoTypeId != payload.bankInfoTypeId) return false;
        if (!name.equals(payload.name)) return false;
        if (!text.equals(payload.text)) return false;
        return publicationDate.equals(payload.publicationDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + publicationDate.hashCode();
        result = 31 * result + bankInfoTypeId;
        return result;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", publicationDate=" + publicationDate +
                ", bankInfoTypeId=" + bankInfoTypeId +
                '}';
    }
}
