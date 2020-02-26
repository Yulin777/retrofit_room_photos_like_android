package com.yulin.ivan.gurutest.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class Photo {
    @SerializedName("id")
    public String id;

    @SerializedName("member_id")
    public String member_id;

    @SerializedName("title")
    public String title;

    @SerializedName("likes")
    public int likes;

    @SerializedName("views")
    public int views;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    public boolean liked = false;
}
