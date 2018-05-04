package ru.divizdev.photogallery.presentation;

import android.content.Context;
import android.content.Intent;

import ru.divizdev.photogallery.presentation.detail.DetailActivity;

public class Router {

    Context _context;

    public Router(Context context) {
        _context = context;
    }

    public void navTo(Screen screen) {
        Intent intent = null;

        switch (screen) {
            case list:
                break;
            case detail:
                intent = DetailActivity.newIntent(_context);
                break;
            case about:
                break;
        }
        if (intent != null) {

            _context.startActivity(intent);
        }

    }


    public enum Screen {
        list,
        detail,
        about

    }

}
