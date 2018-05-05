package ru.divizdev.photogallery.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jsibbold.zoomage.ZoomageView;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private static final String ID_PHOTO = "DetailActivity.PHOTO_ID";
    private ZoomageView _imageView;
    private ProgressBar _progressBar;
    private DetailPresenter _detailPresenter = DetailPresenter.getInstance();

    public static Intent newIntent(Context packageContext, Integer id) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(ID_PHOTO, id);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        _imageView = findViewById(R.id.image_detail);
        _progressBar = findViewById(R.id.progress_bar_detail_image);
        Integer id = getIntent().getIntExtra(ID_PHOTO, -1);
        _detailPresenter.attachView(this, id);
    }

    @Override
    public void viewImage(ImageUI image) {
        GlideApp.with(this).load(image.getDetailImageUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                _progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                _progressBar.setVisibility(View.GONE);
                return false;
            }
        }).centerInside().into(_imageView);
    }


}
