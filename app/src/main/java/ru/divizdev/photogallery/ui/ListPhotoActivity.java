package ru.divizdev.photogallery.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.divizdev.photogallery.Entities.ImageUI;
import ru.divizdev.photogallery.Interaction.InteractorPhotoGallery;
import ru.divizdev.photogallery.R;

public class ListPhotoActivity extends AppCompatActivity implements IViewListPhoto {

    private final static int COUNT_COLUMN_LIST = 2;

    private final List<ImageUI> _listImages = new ArrayList<>();
    private RecyclerView _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);

        _recyclerView = findViewById(R.id.photo_recycler_view);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, COUNT_COLUMN_LIST);

        _recyclerView.setAdapter(new PhotoGalleryAdapter(_listImages));
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(layoutManager);

        InteractorPhotoGallery.getInstance().attache(this);


    }

    @Override
    public void showListImages(List<ImageUI> listImages) {
        _listImages.clear();
        _listImages.addAll(listImages);
        _recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoadingProgress(Boolean isView) {

    }

    @Override
    public void showErrorLoading() {

    }
}
