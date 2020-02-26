package com.yulin.ivan.gurutest.ui.fragb;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface IFragBView {
    void setmPresenter(IFragBPresenter mPresenter);

    void setTitle(String title);

    void setLikes(int likes);

    void setViews(int views);

    void setImage(String buildImageUrl);

    void animateLike(boolean liked);

    void setHart(boolean liked);

    void finishSelf();
}
