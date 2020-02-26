package com.yulin.ivan.gurutest.ui.fraga;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.ivan.gurutest.R;

public class FragA extends Fragment implements IFragAView {
    private IFragAPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        findViews(view);
        setViews();
        return view;
    }

    private void findViews(View container) {
        recyclerView = container.findViewById(R.id.photos_list);
    }

    private void setViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PhotosListAdapter(presenter));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.fetchData();
    }

    @Override
    public ViewModelStoreOwner getStoreOwner() {
        return this;
    }

    @Override
    public void onCallError() {
        Toast.makeText(getContext(), "oops... something happened :(", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(IFragAPresenter photosPresenter) {
        this.presenter = photosPresenter;
    }

    public ImageView getSharedImage(int position) {
        return recyclerView.getLayoutManager().findViewByPosition(position).findViewById(R.id.image);
    }
}
