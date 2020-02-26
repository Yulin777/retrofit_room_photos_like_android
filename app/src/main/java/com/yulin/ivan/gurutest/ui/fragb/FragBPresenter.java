package com.yulin.ivan.gurutest.ui.fragb;

import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.ui.contracts.PhotoContract;
import com.yulin.ivan.gurutest.ui.fraga.PhotoUrlBuilder;
import com.yulin.ivan.gurutest.ui.mainactivity.IBasePresenter;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class FragBPresenter implements PhotoContract.Presenter {
    private final IBasePresenter basePresenter;
    private final IFragBView mView;
    private final Photo photo;
    private final int position;
//    private final PhotoViewModel photoViewModel;

    public FragBPresenter(IFragBView fragBView, IBasePresenter basePresenter, Photo photo, int position) {
        this.basePresenter = basePresenter;
        this.mView = fragBView;
        this.mView.setmPresenter(this);
        this.photo = photo;
        this.position = position;
//        this.photoViewModel = new ViewModelProvider(this.mView.getStoreOwner()).get(PhotoViewModel.class);
    }

    @Override
    public void fetchData() {
        mView.setTitle(photo.title);
        mView.setLikes(photo.likes);
        mView.setViews(photo.views);
        mView.setImage(buildImageUrl(photo));
    }

    @Override
    public void onImageClicked() {
        //hart animation
        //update photo liked
        //update item in fragA
        photo.liked = !photo.liked;
        mView.setHart(photo.liked);
        mView.animateLike(photo.liked);
        basePresenter.onPhotoLiked(photo, position);
    }

    @Override
    public void onOutsideImageClicked() {
        mView.finishSelf();
    }

    private String buildImageUrl(Photo photo) {
        return new PhotoUrlBuilder()
                .width(photo.width)
                .height(photo.height)
                .memberId(photo.member_id)
                .photoId(photo.id)
                .build();
    }
}
