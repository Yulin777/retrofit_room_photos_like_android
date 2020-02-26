package com.yulin.ivan.gurutest.ui.mainactivity;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelStoreOwner;

import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.ui.fraga.FragA;
import com.yulin.ivan.gurutest.ui.fraga.FragAPresenter;
import com.yulin.ivan.gurutest.ui.fragb.FragB;
import com.yulin.ivan.gurutest.ui.fragb.FragBPresenter;

public class MainActivity extends AppCompatActivity implements IBaseView {
    //    private PhotosPresenter photosPresenter;
    private FragA fragA;
    private IBasePresenter basePresenter;
    private FragB fragB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basePresenter = new BasePresenter(this);
        openFragA();
    }


    private void openFragA() {
        fragA = new FragA();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.a_container, fragA)
                .commit();

        FragAPresenter fragAPresenter = new FragAPresenter(fragA, basePresenter);
        basePresenter.setAPresenter(fragAPresenter);
    }


    @Override
    public ViewModelStoreOwner getStoreOwner() {
        return this;
    }

    @Override
    public void openFragB(Photo photo, int position) {
        ImageView sharedImage = fragA.getSharedImage(position);
        fragB = new FragB();
        Bundle args = new Bundle();
        args.putString(getString(R.string.transition_name), sharedImage.getTransitionName());
        fragB.setArguments(args);

        fragB.setSharedElementEnterTransition(new Explode());
        fragB.setEnterTransition(new AutoTransition()/*new Fade()*/);
        fragB.setExitTransition(new Fade());
        fragB.setSharedElementReturnTransition(new DetailsTransition());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.b_container, fragB)
                .addSharedElement(sharedImage, sharedImage.getTransitionName())
                .addToBackStack("fragB")
                .commit();

        FragBPresenter fragBPresenter = new FragBPresenter(fragB, basePresenter, photo, position);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack("fragB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
