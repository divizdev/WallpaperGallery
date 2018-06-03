package ru.divizdev.photogallery.presentation.list.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.list.adapter.ListImagesAdapter;
import ru.divizdev.photogallery.presentation.list.presenter.IListImagesPresenter;

public class ListImagesActivity extends AppCompatActivity implements IListImagesView, SwipeRefreshLayout.OnRefreshListener {

    private static final int DEFAULT_COUNT_COLUMN_LIST = 2;


    private final List<ImageUI> _listImages = new ArrayList<>();
    private IListImagesPresenter _listImagesPresenter = PGApplication.getFactory().getListImagesPresenter();
    private RecyclerView _recyclerView;
    private ProgressBar _progressBar;
    private SwipeRefreshLayout _swipeRefreshLayout;
    private Router _router = PGApplication.getFactory().getRouter();

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ListImagesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);

        _recyclerView = findViewById(R.id.photo_recycler_view);
        _progressBar = findViewById(R.id.progress_bar_load_images);
        _swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        _swipeRefreshLayout.setOnRefreshListener(this);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, getCountColumnList());

        _recyclerView.setAdapter(new ListImagesAdapter(_listImages, _listImagesPresenter));
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(layoutManager);

        _listImagesPresenter.attachView(this);

    }

    private int getCountColumnList(){
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return DEFAULT_COUNT_COLUMN_LIST + 3;
        }

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return DEFAULT_COUNT_COLUMN_LIST + 2;
        }

        return DEFAULT_COUNT_COLUMN_LIST;
    }


    @Override
    protected void onDestroy() {
        _listImagesPresenter.detachView();

        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showListImages(List<ImageUI> listImages) {
        _listImages.clear();
        _listImages.addAll(listImages);
        _recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoadingProgress(Boolean isView) {
        if (isView) {
            if (!_swipeRefreshLayout.isRefreshing()) {
                _progressBar.setVisibility(View.VISIBLE);
            }

        } else {
            _progressBar.setVisibility(View.GONE);
            _swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showErrorLoading(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorNoBody() {
        Toast.makeText(this, R.string.error_no_body, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorBadConnect() {
        Toast.makeText(this, R.string.error_bad_connect, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navToDetailScreen(ImageCategory category, Integer id) {
        _router.navToDetail(this, category, id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void showAboutDialog() {
        _router.navToAbout(this);
    }

    @Override
    public void setTitle(ImageCategory key) {
        int id = getResources().getIdentifier("ru.divizdev.photogallery:string/"+key.getKeyResourceName(), null, null);
        setTitle(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about:
                _listImagesPresenter.actionShowAbout();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onRefresh() {
        _listImagesPresenter.refreshImageList();
    }
}
