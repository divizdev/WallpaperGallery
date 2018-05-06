package ru.divizdev.photogallery.presentation.list.view;

import android.os.Bundle;
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
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.list.adapter.ListImagesAdapter;
import ru.divizdev.photogallery.presentation.list.presenter.IListImagesPresenter;

public class ListImagesActivity extends AppCompatActivity implements IListImagesView {

    private final static int COUNT_COLUMN_LIST = 2;

    private final List<ImageUI> _listImages = new ArrayList<>();
    private IListImagesPresenter _listImagesPresenter = PGApplication.getFactory().getListImagesPresenter();
    private RecyclerView _recyclerView;
    private ProgressBar _progressBar;
    private Router _router = PGApplication.getFactory().getRouter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);

        _recyclerView = findViewById(R.id.photo_recycler_view);
        _progressBar = findViewById(R.id.progress_bar_load_images);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, COUNT_COLUMN_LIST);

        _recyclerView.setAdapter(new ListImagesAdapter(_listImages, _listImagesPresenter));
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onResume() {
        super.onResume();
        _listImagesPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        _listImagesPresenter.detachView();
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
            _progressBar.setVisibility(View.VISIBLE);
        } else {
            _progressBar.setVisibility(View.GONE);
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
    public void navToDetailScreen(Integer id) {
        _router.navToDetail(this, id);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about:
                _listImagesPresenter.actionShowAbout();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

}
