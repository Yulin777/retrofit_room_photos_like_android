package com.yulin.ivan.gurutest.data.entity;

import com.google.gson.annotations.SerializedName;
import com.yulin.ivan.gurutest.ui.fraga.PhotoUrlBuilder;

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

    public void attachImagesUrls() {
        for(Photo photo : photoList){
            photo.imageUrl = buildImageUrl(photo);
        }
    }

    private String buildImageUrl(Photo photo) {
        return new PhotoUrlBuilder()
                .width(photo.width)
                .height(photo.height)
                .memberId(photo.member_id)
                .photoId(photo.id)
                .build();
    }
}
