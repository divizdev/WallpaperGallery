package ru.divizdev.photogallery.presentation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.presentation.about.AboutDialog;
import ru.divizdev.photogallery.presentation.detail.view.DetailActivity;
import ru.divizdev.photogallery.presentation.list.view.ListImagesActivity;

public class Router {


    public void navToDetail(@NonNull AppCompatActivity activity, Integer id){
       Intent intent = DetailActivity.newIntent(activity, id);
       activity.startActivity(intent);
    }

    public void navToAbout(@NonNull AppCompatActivity activity){

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.show(fragmentManager, "");
    }

    public void navToListImages(@NonNull AppCompatActivity activity, ImageCategory category){
        Intent intent = ListImagesActivity.newIntent(activity, category.getKey());
        activity.startActivity(intent);
    }

}
