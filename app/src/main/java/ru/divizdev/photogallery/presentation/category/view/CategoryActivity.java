package ru.divizdev.photogallery.presentation.category.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.category.adapter.ListCategoriesAdapter;
import ru.divizdev.photogallery.presentation.category.presenter.IListCategoryPresenter;

public class CategoryActivity extends AppCompatActivity implements IListCategoriesView {

    private static final int DEFAULT_COUNT_COLUMN_LIST = 2;
    private RecyclerView _recyclerView;

    private List<ImageCategory> _listCategories = new ArrayList<>();

    private Router _router = PGApplication.getFactory().getRouter();
    private IListCategoryPresenter _presenter = PGApplication.getFactory().getListCategoryPresenter();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        _recyclerView = findViewById(R.id.category_recycler_view);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, getCountColumnList());
        _recyclerView.setAdapter(new ListCategoriesAdapter(_listCategories, _presenter));
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about:
                _presenter.actionShowAbout();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        _presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public int getCountColumnList() {
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return DEFAULT_COUNT_COLUMN_LIST + 2;
        }

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return DEFAULT_COUNT_COLUMN_LIST + 1;
        }

        return DEFAULT_COUNT_COLUMN_LIST;
    }


    @Override
    public void showListImages(List<ImageCategory> listImages) {
        _listCategories.clear();
        _listCategories.addAll(listImages);
        _recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void navToListImageScreen(ImageCategory category) {
        _router.navToListImages(this, category);
    }


    @Override
    public void showAboutDialog() {
        _router.navToAbout(this);
    }
}
