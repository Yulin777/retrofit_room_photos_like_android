package com.yulin.ivan.gurutest.ui.mainactivity;

import androidx.lifecycle.ViewModelStoreOwner;

import com.yulin.ivan.gurutest.data.entity.Photo;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IBaseView {

    ViewModelStoreOwner getStoreOwner();

    void openFragB(Photo photo, int position);

}
