package ru.divizdev.wallpapergallery.presentation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.divizdev.wallpapergallery.R;
import ru.divizdev.wallpapergallery.data.IPhotoGalleryState;
import ru.divizdev.wallpapergallery.entities.ImageCategory;
import ru.divizdev.wallpapergallery.entities.ImageUI;
import ru.divizdev.wallpapergallery.presentation.about.AboutDialog;
import ru.divizdev.wallpapergallery.presentation.category.view.CategoryActivity;
import ru.divizdev.wallpapergallery.presentation.detail.view.DetailActivity;
import ru.divizdev.wallpapergallery.presentation.list.view.ListImagesActivity;

public class Router {

    private final IPhotoGalleryState _state;

    public Router(IPhotoGalleryState state) {
        _state = state;
    }

    public void navToDetail(@NonNull AppCompatActivity activity, ImageCategory category, Integer id){
       Intent intent = DetailActivity.newIntent(activity);
       _state.setIdCurrentImages(category.getKey(), id);
       activity.startActivity(intent);
    }

    public void navToAbout(@NonNull AppCompatActivity activity){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.show(fragmentManager, "");
    }

    public void navToListImages(@NonNull AppCompatActivity activity, ImageCategory category){
        Intent intent = ListImagesActivity.newIntent(activity);
        _state.setCurrentCategory(category.getKey());
        activity.startActivity(intent);
    }

    public void navToMainScreen(@NonNull AppCompatActivity activity){
        Intent intent = CategoryActivity.newIntent(activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }


    public void showShare(@NonNull AppCompatActivity activity, ImageUI imageUI) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, imageUI.getShareImageUrl());

        activity.startActivity(Intent.createChooser(intent, activity.getResources().getString(R.string.menu_share)));
    }

}
