package ru.divizdev.photogallery.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.divizdev.photogallery.presentation.about.AboutDialog;
import ru.divizdev.photogallery.presentation.detail.DetailActivity;

public class Router {


    public void navTo(Context context, Screen screen) {
        Intent intent = null;

        switch (screen) {
            case list:
                break;
            case detail:

                break;
            case about:
                break;
        }
        if (intent != null) {

            context.startActivity(intent);
        }

    }

    public void navToDetail(@NonNull AppCompatActivity activity, Integer id){
       Intent intent = DetailActivity.newIntent(activity, id);
       activity.startActivity(intent);
    }

    public void navToAbout(@NonNull AppCompatActivity activity){

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.show(fragmentManager, "");
    }


    public enum Screen {
        list,
        detail,
        about
    }

}
