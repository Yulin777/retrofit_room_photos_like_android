package com.yulin.ivan.gurutest.ui.fragb;

import com.yulin.ivan.gurutest.data.entity.Photo;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IBView {
    void setPresenter(IBPresenter mPresenter);

    void animateLike(boolean liked);

    void finishSelf();

    void updateUI(Photo photo1);
}
