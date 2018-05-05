package ru.divizdev.photogallery.presentation;

import android.content.Context;
import android.content.Intent;

import ru.divizdev.photogallery.presentation.detail.DetailActivity;

public class Router {


    public void navTo(Context context, Screen screen) {
        Intent intent = null;

        switch (screen) {
            case list:
                break;
            case detail:
                intent = DetailActivity.newIntent(context);
                break;
            case about:
                break;
        }
        if (intent != null) {

            context.startActivity(intent);
        }

    }


    public enum Screen {
        list,
        detail,
        about

    }

}
