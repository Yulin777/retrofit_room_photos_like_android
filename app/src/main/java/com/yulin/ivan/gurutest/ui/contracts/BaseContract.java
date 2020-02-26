package com.yulin.ivan.gurutest.ui.contracts;

import com.yulin.ivan.gurutest.ui.mainactivity.IBasePresenter;
import com.yulin.ivan.gurutest.ui.fragb.IFragBPresenter;
import com.yulin.ivan.gurutest.ui.fraga.IFragAPresenter;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public interface BaseContract {
    interface BasePresenter extends IBasePresenter {

    }

    interface PhotosPresenter extends IFragAPresenter {

    }

    interface PhotoPresenter extends IFragBPresenter {

    }
}
