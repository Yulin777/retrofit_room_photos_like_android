package com.yulin.ivan.gurutest.ui.mainactivity;

import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.ui.fraga.FragAPresenter;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IBasePresenter {
    void onItemClicked(Photo photo, int position);

    void onPhotoLiked(Photo photo, int position);

    void setAPresenter(FragAPresenter aPresenter);
}
