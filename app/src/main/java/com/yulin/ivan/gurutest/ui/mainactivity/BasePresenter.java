package com.yulin.ivan.gurutest.ui.mainactivity;


import androidx.lifecycle.ViewModelProvider;

import com.yulin.ivan.gurutest.data.PhotoViewModel;
import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.ui.fraga.FragAPresenter;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class BasePresenter implements IBasePresenter {
    private final IBaseView baseView;
    private final PhotoViewModel photoViewModel;
    private FragAPresenter aPresenter;

    public BasePresenter(IBaseView baseView) {
        this.baseView = baseView;
        this.photoViewModel = new ViewModelProvider(baseView.getStoreOwner()).get(PhotoViewModel.class);

    }

    @Override
    public void onItemClicked(Photo photo, int position) {
        baseView.openFragB(photo, position);
    }

    @Override
    public void onPhotoLiked(Photo photo, int position) {
        aPresenter.onPhotoLiked(photo, position);
    }

    @Override
    public void setAPresenter(FragAPresenter aPresenter) {
        this.aPresenter = aPresenter;
    }
}
