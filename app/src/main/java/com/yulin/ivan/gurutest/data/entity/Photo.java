package com.yulin.ivan.gurutest.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

@Entity(tableName = "photos_table", primaryKeys = {})
public class Photo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
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
    public String imageUrl;
}
