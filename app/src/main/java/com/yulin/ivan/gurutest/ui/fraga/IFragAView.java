package com.yulin.ivan.gurutest.ui.fraga;

import android.content.Context;

import androidx.lifecycle.ViewModelStoreOwner;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IFragAView {
    ViewModelStoreOwner getStoreOwner();

    void onCallError();

    Context getContext();

    void setPresenter(IFragAPresenter photosPresenter);
}
