package com.yulin.ivan.gurutest.ui.fraga;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class PhotoUrlBuilder {
    private String PHOTO_BASE_URL = "https://photos.gurushots.com/";
    private int width = 300;
    private int height = 300;
    private String memberId = "";
    private String photoId = "";

    public PhotoUrlBuilder() {
    }

    public PhotoUrlBuilder width(int width) {
        this.width = width;
        return this;
    }

    public PhotoUrlBuilder height(int height) {
        this.height = height;
        return this;
    }

    public PhotoUrlBuilder memberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public PhotoUrlBuilder photoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public String build() {
        return new StringBuilder(PHOTO_BASE_URL)
                .append("unsafe/")
                .append(width)
                .append("x")
                .append(height)
                .append("/")
                .append(memberId)
                .append("/3_")
                .append(photoId)
                .append(".jpg")
                .toString();
    }
}
