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
import java.util.List;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageCategoryKey;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.detail.adapter.DetailPagerAdapter;
import ru.divizdev.photogallery.presentation.detail.presenter.IDetailPresenter;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private static final String ID_PHOTO = "DetailActivity.PHOTO_ID";
    private static final String CATEGORY_PHOTO = "DetailActivity.CATEGORY_PHOTO";

    private IDetailPresenter _detailPresenter = PGApplication.getFactory().getDetailPresenter();
    private Router _router = PGApplication.getFactory().getRouter();
    private ViewPager _pager;
    private PagerAdapter _pagerAdapter;


    public static Intent newIntent(Context packageContext, Integer id, ImageCategoryKey categoryKey) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(ID_PHOTO, id);


        intent.putExtra(CATEGORY_PHOTO, categoryKey);
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
        _pager = findViewById(R.id.pager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //presenter
        Integer id = getIntent().getIntExtra(ID_PHOTO, -1);
        ImageCategoryKey imageCategoryKey = (ImageCategoryKey)getIntent().getSerializableExtra(CATEGORY_PHOTO);
        _detailPresenter.attachView(this, imageCategoryKey, id);
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
    public void showImages(Integer initPosition, List<ImageUI> listImage) {
        _pagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), listImage);
        _pager.setAdapter(_pagerAdapter);
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
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, imageUI.getShareImageUrl());

        startActivity(Intent.createChooser(intent, getResources().getString(R.string.menu_share)));
    }


}
