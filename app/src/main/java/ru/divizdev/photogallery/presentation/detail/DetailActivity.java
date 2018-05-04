package ru.divizdev.photogallery.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private ImageView _imageView;
    private DetailPresenter _detailPresenter = DetailPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        _imageView = findViewById(R.id.image_detail);

        _detailPresenter.attachView(this);
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public void viewImage(ImageUI image){
        GlideApp.with(this).load(image.getDetailImageUrl()).centerInside().into(_imageView);
    }
}
