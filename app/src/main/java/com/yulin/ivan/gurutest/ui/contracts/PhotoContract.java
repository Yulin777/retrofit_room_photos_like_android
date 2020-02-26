package com.yulin.ivan.gurutest.ui.contracts;

import com.yulin.ivan.gurutest.ui.mainactivity.IBasePresenter;
import com.yulin.ivan.gurutest.ui.fragb.IFragBPresenter;
import com.yulin.ivan.gurutest.ui.fragb.IFragBView;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface PhotoContract {
    interface BasePresenter extends IBasePresenter {

    }

    interface PhotoView extends IFragBView {

    }

    interface Presenter extends IFragBPresenter {

    }
}
