package com.yulin.ivan.gurutest.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class RetrofitInstance {
    private static Retrofit allPhotosInstance;
    public static final String ALL_PHOTOS_BASE_URL = "https://api.gurushots.com/";

    public static Retrofit getAllPhotosInstance() {
        if (allPhotosInstance == null) {
            allPhotosInstance = new Retrofit.Builder()
                    .baseUrl(ALL_PHOTOS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return allPhotosInstance;
    }

}
