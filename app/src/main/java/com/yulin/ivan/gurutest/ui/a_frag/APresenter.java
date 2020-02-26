package com.yulin.ivan.gurutest.ui.a_frag;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.PhotoViewModel;
import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.ui.b_frag.BFrag;
import com.yulin.ivan.gurutest.ui.b_frag.BPresenter;

import java.util.function.Consumer;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class APresenter implements IAPresenter {
    private IAView mView;
    private PhotoViewModel photoViewModel;
    private PhotosListAdapter photosListAdapter;

    public APresenter(ViewModelStoreOwner storeOwner, IAView AFrag) {
        this.mView = AFrag;
        this.mView.setPresenter(this);
        this.photoViewModel = new ViewModelProvider(storeOwner).get(PhotoViewModel.class);
    }

    @Override
    public void fetchData() {
        photoViewModel.fetchData(mView.getContext(), updateUiAfterPhotoListInsert, onCallError);
    }

    private Runnable updateUiAfterPhotoListInsert = () -> photosListAdapter.setPhotoListFromDB();
    private Runnable onCallError = () -> mView.onCallError();

    @Override
    public void setListAdapter(PhotosListAdapter photosListAdapter) {
        this.photosListAdapter = photosListAdapter;
    }

    @Override
    public void onItemClicked(Photo photo, int position) {
        openFragB(photo);
    }

    @Override
    public ViewModelStoreOwner getStoreOwner() {
        return mView.getStoreOwner();
    }

    @Override
    public LifecycleOwner getLifeCycleOwner() {
        return mView.getLifeCycleOwner();
    }

    @Override
    public void observePhoto(String id, Consumer<Photo> onUpdateReceived) {
        photoViewModel.getPhoto(id).observeForever(onUpdateReceived::accept);
    }

    @Override
    public Context getContext() {
        return mView.getContext();
    }

    public void openFragB(Photo photo) {
        BFrag fragB = new BFrag();

        mView.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.b_container, fragB)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack("fragB")
                .commit();

        BPresenter BPresenter = new BPresenter(mView.getActivity(), fragB, photo);
    }
}
