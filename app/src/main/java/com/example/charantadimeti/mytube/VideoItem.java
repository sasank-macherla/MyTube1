package com.example.charantadimeti.mytube;

import com.google.api.client.util.DateTime;

import java.math.BigInteger;

/**
 * Created by charantadimeti on 10/3/15.
 */
public class VideoItem {
    private String title;
    private String description;
    private String thumbnailURL;
    private String id;
    private BigInteger noOfViews;
    private DateTime publishedDate;
    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public BigInteger getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(BigInteger noOfViews) {
        this.noOfViews = noOfViews;
    }

    public DateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(DateTime publishedDate) {
        this.publishedDate = publishedDate;
    }
}
