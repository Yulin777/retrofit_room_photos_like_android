package com.yulin.ivan.gurutest.ui.fraga;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.data.entity.PhotoList;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class PhotosListAdapter extends Adapter<PhotoViewHolder> {
    private final IFragAPresenter photosPresenter;
    private PhotoList photoList;

    PhotosListAdapter(IFragAPresenter photosPresenter) {
        this.photosPresenter = photosPresenter;
        photosPresenter.setListAdapter(this);
    }

    void setPhotoList(PhotoList photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(photosPresenter.getContext())
                .inflate(R.layout.photo_list_item, parent, false);

        return new PhotoViewHolder(cardView, photosPresenter);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.setData(photoList.get(position), position);
        holder.itemView.setOnClickListener(v -> photosPresenter.onItemClicked(photoList.get(position), position));
    }

    @Override
    public int getItemCount() {
        return photoList == null ? 0 : photoList.size();
    }

    public void onPhotoLiked(Photo photo, int position) {
        photoList.get(position).liked = photo.liked;
        notifyItemChanged(position);
    }
}
