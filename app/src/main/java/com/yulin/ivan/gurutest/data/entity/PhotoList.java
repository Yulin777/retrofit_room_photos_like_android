package com.yulin.ivan.gurutest.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class PhotoList {

    @SerializedName("items")
    private ArrayList<Photo> photoList;

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<Photo> photoList) {
        this.photoList = photoList;
    }

    public int size() {
        return photoList.size();
    }

    public Photo get(int position) {
        return photoList.get(position);
    }
}
