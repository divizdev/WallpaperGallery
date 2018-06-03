package ru.divizdev.photogallery.presentation.detail.view;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.detail.adapter.DetailPagerAdapter;
import ru.divizdev.photogallery.presentation.detail.presenter.IDetailPresenter;
import ru.divizdev.photogallery.presentation.detail.presenter.IImageUIListAdapter;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private IDetailPresenter _detailPresenter = PGApplication.getFactory().getDetailPresenter();
    private Router _router = PGApplication.getFactory().getRouter();
    private ViewPager _pager;


    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, DetailActivity.class);
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
        _pager = findViewById(R.id.pager);

        //presenter
        _detailPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        _detailPresenter.detachView();

        super.onDestroy();
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
                _detailPresenter.actionShare(_pager.getCurrentItem());
                return true;

            case R.id.action_about:
                _detailPresenter.actionShowAbout();
                return true;
            case R.id.action_set_wallpaper:
                _detailPresenter.actionSetWallpaper(_pager.getCurrentItem());
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void showImages(Integer initPosition, IImageUIListAdapter imageUIListAdapter) {
        PagerAdapter pagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), imageUIListAdapter);
        _pager.setAdapter(pagerAdapter);
        _pager.setCurrentItem(initPosition);
    }

    @Override
    public void showAboutDialog() {
        _router.navToAbout(this);
    }

    @Override
    public void setWallpaper(ImageUI imageUI) {

        GlideApp.with(this).asBitmap().load(imageUI.getDetailImageUrl()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    WallpaperManager.getInstance(getApplicationContext()).setBitmap(resource);
                    Toast.makeText(getApplicationContext(), R.string.message_ok_wallpaper, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), R.string.message_fail_wallpaper, Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @Override
    public void showShare(ImageUI imageUI) {
        _router.showShare(this, imageUI);
    }


}
