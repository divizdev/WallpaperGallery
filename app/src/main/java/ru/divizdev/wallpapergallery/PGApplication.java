package ru.divizdev.wallpapergallery;

import android.app.Application;

import ru.divizdev.wallpapergallery.di.Factory;
import ru.divizdev.wallpapergallery.di.IFactory;

public class PGApplication extends Application {

    private static IFactory _factory;


    @Override
    public void onCreate() {
        super.onCreate();
        _factory = new Factory(getApplicationContext());
    }

    public static IFactory getFactory() {
        return _factory;
    }
}
