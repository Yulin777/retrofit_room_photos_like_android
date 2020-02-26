package com.yulin.ivan.gurutest.data.rest;

import com.yulin.ivan.gurutest.data.entity.PhotoList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface GetPhotosListService {
    @FormUrlEncoded
    @Headers({
            "X-API-VERSION: 20",
            "X-ENV: ANDROID"
    })
    @POST("rest_mobile/get_photos_public/items")
    Call<PhotoList> getPhotos(@Field("member_id") String id,
                              @Field("get_likes") boolean likes,
                              @Field("limit") int limit,
                              @Field("start") int start);
}
