package com.yulin.ivan.gurutest.ui.b_frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.entity.Photo;

import java.util.Locale;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class BFrag extends Fragment implements IBView {

    private IBPresenter mPresenter;
    private TextView title;
    private TextView likes;
    private TextView views;
    private ImageView image;
    private ImageView hart;

    private Animation zoomToFull;

    public BFrag() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zoomToFull = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_to_full);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        findViews(view);
        view.setOnClickListener(v -> mPresenter.onOutsideImageClicked());
        mPresenter.incrementViews();

        return view;
    }

    private void findViews(View container) {
        this.title = container.findViewById(R.id.title);
        this.likes = container.findViewById(R.id.likes);
        this.views = container.findViewById(R.id.views);
        this.image = container.findViewById(R.id.image);
        this.hart = container.findViewById(R.id.hart);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.image.setOnClickListener(v -> mPresenter.onImageClicked());
        mPresenter.fetchData();
    }

    @Override
    public void setPresenter(IBPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void animateLike(boolean liked) {
        this.hart.setVisibility(VISIBLE);
        this.hart.startAnimation(zoomToFull);
        this.hart.setVisibility(INVISIBLE);
    }

    @Override
    public void finishSelf() {
        getActivity().onBackPressed();
    }

    @Override
    public void updateUI(Photo photo) {
        this.title.setText(photo.title);
        this.likes.setText(String.format(Locale.ENGLISH, "likes: %d", photo.likes));
        this.views.setText(String.format(Locale.ENGLISH, "votes: %d", photo.views));
        this.hart.setImageResource(photo.liked ? R.drawable.filled_hart : R.drawable.empty_hart);

        Picasso.get().load(photo.imageUrl)
                .centerCrop()
                .resize(300, 300) //required
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        image.setAlpha(0f);
                        image.animate().setDuration(400).alpha(1f).start();
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });
    }


}
