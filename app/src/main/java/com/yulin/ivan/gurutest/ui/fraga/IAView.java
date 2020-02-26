package com.yulin.ivan.gurutest.ui.fraga;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IAView {
    ViewModelStoreOwner getStoreOwner(); //for the modelView

    void onCallError();

    Context getContext();

    void setPresenter(IAPresenter photosPresenter);

    LifecycleOwner getLifeCycleOwner();

    FragmentActivity getActivity();
}
