package com.yulin.ivan.gurutest.ui.a_frag;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.entity.Photo;

import java.util.Locale;

import static android.view.View.*;
import static android.view.View.GONE;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

class PhotoViewHolder extends ViewHolder {
    private ImageView image;
    private ImageView hart;
    private TextView title;
    private TextView likes;
    private TextView views;
    private ProgressBar busyIndicator;

    PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View itemView) {
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.likes = itemView.findViewById(R.id.likes);
        this.views = itemView.findViewById(R.id.views);
        this.hart = itemView.findViewById(R.id.hart);
        this.busyIndicator = itemView.findViewById(R.id.busy_indicator);
    }

    void updateUI(Photo photo) {
        this.busyIndicator.setVisibility(VISIBLE);
        this.title.setText(photo.title);
        this.likes.setText(String.format(Locale.ENGLISH, "likes: %d", photo.likes));
        this.views.setText(String.format(Locale.ENGLISH, "views: %d", photo.views));

        Picasso.get()
                .load(photo.imageUrl)
                .centerCrop()
                .resize(300, 300) //minimize, required
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        busyIndicator.setVisibility(GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });

        this.hart.setImageResource(photo.liked ? R.drawable.filled_hart : R.drawable.empty_hart);
    }
}
