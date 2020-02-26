package com.yulin.ivan.gurutest.ui.fraga;

import android.content.Context;
import android.view.View;

import com.yulin.ivan.gurutest.data.entity.Photo;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IFragAPresenter {
    void fetchData();

    Context getContext();

    void setListAdapter(PhotosListAdapter photosListAdapter);

    void onItemClicked(Photo photo, int position);

}
