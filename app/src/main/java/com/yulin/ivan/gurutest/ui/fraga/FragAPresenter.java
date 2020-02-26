package com.yulin.ivan.gurutest.ui.fraga;

import android.content.Context;

import com.yulin.ivan.gurutest.data.PhotoViewModel;
import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.data.entity.PhotoList;
import com.yulin.ivan.gurutest.data.rest.GetPhotosListService;
import com.yulin.ivan.gurutest.data.rest.RetrofitInstance;
import com.yulin.ivan.gurutest.ui.mainactivity.IBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class FragAPresenter implements IFragAPresenter {
    private final IFragAView photosView;
    private final IBasePresenter basePresenter;
    private PhotoViewModel photoViewModel;
    private PhotosListAdapter photosListAdapter;

    public FragAPresenter(IFragAView fragAView, IBasePresenter basePresenter) {
        this.photosView = fragAView;
        this.photosView.setPresenter(this);
        this.basePresenter = basePresenter;
//        this.photoViewModel = new ViewModelProvider(photosView.getStoreOwner()).get(PhotoViewModel.class);
    }

    @Override
    public void fetchData() {
        GetPhotosListService service = RetrofitInstance.getAllPhotosInstance().create(GetPhotosListService.class);
        Call<PhotoList> call = service.getPhotos("2a49ab04b1534574e578a08b8f9d7441", true, 50, 0);
        call.enqueue(new Callback<PhotoList>() {
            @Override
            public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {
                photosListAdapter.setPhotoList(response.body());
            }

            @Override
            public void onFailure(Call<PhotoList> call, Throwable t) {
                photosView.onCallError();
            }
        });
    }

    @Override
    public void setListAdapter(PhotosListAdapter photosListAdapter) {
        this.photosListAdapter = photosListAdapter;
    }

    @Override
    public void onItemClicked(Photo photo, int position) {
        basePresenter.onItemClicked(photo, position);
    }

    @Override
    public Context getContext() {
        return photosView.getContext();
    }

    public void onPhotoLiked(Photo photo, int position) {
        photosListAdapter.onPhotoLiked(photo, position);
    }
}
