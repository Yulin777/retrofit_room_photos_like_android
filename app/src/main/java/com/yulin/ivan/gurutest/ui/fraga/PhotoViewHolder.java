package com.yulin.ivan.gurutest.ui.fraga;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.entity.Photo;

import java.util.Locale;

import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

class PhotoViewHolder extends ViewHolder {
    private final IFragAPresenter photosPresenter;
    private View container;
    private ImageView image;
    private ImageView hart;
    private TextView title;
    private TextView likes;
    private TextView views;
    private int position;

    public PhotoViewHolder(@NonNull View itemView, IFragAPresenter photosPresenter) {
        super(itemView);
        this.photosPresenter = photosPresenter;
        findViews(itemView);
    }

    private void findViews(View itemView) {
        this.container = itemView;
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.likes = itemView.findViewById(R.id.likes);
        this.views = itemView.findViewById(R.id.views);
        this.hart = itemView.findViewById(R.id.hart);
    }

    public void setData(Photo photo, int position) {
        this.position = position;
        this.title.setText(photo.title);
        this.likes.setText(String.format(Locale.ENGLISH, "likes: %d", photo.likes));
        this.views.setText(String.format(Locale.ENGLISH, "votes: %d", photo.views));
        this.image.setTransitionName("image" + this.position);

        String imageUrl = buildImageUrl(photo);
        Picasso.get()
                .load(imageUrl)
//                .centerCrop()
//                .resize(500, 300) //required
                .into(image);

        this.hart.setImageResource(photo.liked ? R.drawable.filled_hart : R.drawable.empty_hart);
    }

    private String buildImageUrl(Photo photo) {
        return new PhotoUrlBuilder()
                .width(photo.width)
                .height(photo.height)
                .memberId(photo.member_id)
                .photoId(photo.id)
                .build();
    }
}
