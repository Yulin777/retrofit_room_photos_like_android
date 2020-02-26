package com.yulin.ivan.gurutest.ui.fraga;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.PhotoViewModel;
import com.yulin.ivan.gurutest.data.entity.Photo;

import java.util.List;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class PhotosListAdapter extends Adapter<PhotoViewHolder> {
    private final IAPresenter mPresenter;
    private PhotoViewModel photoViewModel;
    private List<Photo> photosList;

    PhotosListAdapter(IAPresenter photosPresenter) {
        this.mPresenter = photosPresenter;
        this.mPresenter.setListAdapter(this);
        this.photoViewModel = new ViewModelProvider(mPresenter.getStoreOwner()).get(PhotoViewModel.class);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(mPresenter.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        mPresenter.observePhoto(photosList.get(position).id, holder::updateUI);
        holder.itemView.setOnClickListener(v -> mPresenter.onItemClicked(photosList.get(position), position));
    }

    @Override
    public int getItemCount() {
        return photosList == null ? 0 : photosList.size();
    }

    void setPhotoListFromDB() {
        LiveData<List<Photo>> photosObserver = photoViewModel.getAllPhotos();
        photosObserver.observeForever(photos -> {
            this.photosList = photos;
            notifyDataSetChanged();
        });
    }
}
