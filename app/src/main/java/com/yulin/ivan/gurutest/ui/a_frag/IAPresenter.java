package com.yulin.ivan.gurutest.ui.a_frag;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;

import com.yulin.ivan.gurutest.data.entity.Photo;

import java.util.function.Consumer;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IAPresenter {
    void fetchData();

    Context getContext();

    void setListAdapter(PhotosListAdapter photosListAdapter);

    void onItemClicked(Photo photo, int position);

    ViewModelStoreOwner getStoreOwner();

    LifecycleOwner getLifeCycleOwner();

    void observePhoto(String id, Consumer<Photo> onUpdateReceived);
}
