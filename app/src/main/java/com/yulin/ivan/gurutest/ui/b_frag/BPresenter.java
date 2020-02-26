package com.yulin.ivan.gurutest.ui.b_frag;

import android.os.Handler;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.yulin.ivan.gurutest.data.PhotoViewModel;
import com.yulin.ivan.gurutest.data.entity.Photo;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class BPresenter implements IBPresenter {
    private final IBView mView;
    private final Photo photo;
    private final PhotoViewModel photoViewModel;

    public BPresenter(ViewModelStoreOwner storeOwner, IBView fragBView, Photo photo) {
        this.mView = fragBView;
        this.mView.setPresenter(this);
        this.photo = photo;
        this.photoViewModel = new ViewModelProvider(storeOwner).get(PhotoViewModel.class);
    }

    @Override
    public void fetchData() {
        photoViewModel.getPhoto(photo.id).observeForever(mView::updateUI);
    }

    @Override
    public void onImageClicked() {
        photo.liked = !photo.liked;
        photoViewModel.updateLiked(photo.id, photo.liked);
        photoViewModel.updateLikes(photo.id, photo.liked ? photo.likes + 1 : photo.likes);
        new Handler().postDelayed(() -> {
            mView.animateLike(photo.liked);
        }, 100);
    }

    @Override
    public void onOutsideImageClicked() {
        mView.finishSelf();
    }

    @Override
    public void incrementViews() {
        photoViewModel.updateViews(photo.id, ++photo.views);
    }

}
