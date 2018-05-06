package ru.divizdev.photogallery.presentation.detail.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jsibbold.zoomage.ZoomageView;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.detail.presenter.IDetailPresenter;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private static final String ID_PHOTO = "DetailActivity.PHOTO_ID";
    private ZoomageView _imageView;
    private ProgressBar _progressBar;
    private IDetailPresenter _detailPresenter = PGApplication.getFactory().getDetailPresenter();
    private Router _router = PGApplication.getFactory().getRouter();

    public static Intent newIntent(Context packageContext, Integer id) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(ID_PHOTO, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //bind
        _imageView = findViewById(R.id.image_detail);
        _progressBar = findViewById(R.id.progress_bar_detail_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //presenter
        Integer id = getIntent().getIntExtra(ID_PHOTO, -1);
        _detailPresenter.attachView(this, id);
    }

    @Override
    protected void onPause() {
        super.onPause();
        _detailPresenter.detachView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                _detailPresenter.actionShare();
                return true;

            case R.id.action_about:
                _detailPresenter.actionShowAbout();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void showImage(ImageUI image) {
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

    @Override
    public void showAboutDialog() {
        _router.navToAbout(this);
    }

    @Override
    public void showShare(ImageUI imageUI) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, imageUI.getShareImageUrl());

        startActivity(Intent.createChooser(intent, getResources().getString(R.string.menu_share)));
    }


}
