package com.yulin.ivan.gurutest.ui.fragb;

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

import com.squareup.picasso.Picasso;
import com.yulin.ivan.gurutest.R;

import static android.view.View.*;

public class FragB extends Fragment implements IFragBView {

    private IFragBPresenter mPresenter;
    private TextView title;
    private TextView likes;
    private TextView views;
    private ImageView image;
    private ImageView hart;

    private Animation zoomToFull;

    public FragB() {
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
        this.image.setTransitionName(getArguments().getString(getString(R.string.transition_name)));

        view.setOnClickListener(v -> mPresenter.onOutsideImageClicked());

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
    public void setmPresenter(IFragBPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setLikes(int likes) {
        this.likes.setText(String.valueOf(likes));
    }

    @Override
    public void setViews(int views) {
        this.views.setText(String.valueOf(views));
    }

    @Override
    public void setImage(String imageUrl) {
//        Picasso.get()
//                .load(imageUrl)
//                .centerCrop()
//                .resize(500, 500)
//                .into(image);
    }

    @Override
    public void animateLike(boolean liked) {
        this.hart.setVisibility(VISIBLE);
        this.hart.startAnimation(zoomToFull);
        this.hart.setVisibility(INVISIBLE);
    }

    @Override
    public void setHart(boolean liked) {
        this.hart.setImageResource(liked ? R.drawable.filled_hart : R.drawable.empty_hart);
    }

    @Override
    public void finishSelf() {
        getActivity().onBackPressed();
    }


}
