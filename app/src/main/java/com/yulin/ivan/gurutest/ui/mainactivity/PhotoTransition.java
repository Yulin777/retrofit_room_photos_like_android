package com.yulin.ivan.gurutest.ui.mainactivity;

import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.TransitionSet;

/**
 * Created by Ivan Y. on 2020-02-26.
 */

public class PhotoTransition extends TransitionSet {
    public PhotoTransition() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform()).
                addTransition(new ChangeImageTransform());
                addTransition(new Slide());
    }
}