package ru.divizdev.photogallery;

import android.app.Application;

import ru.divizdev.photogallery.di.Factory;
import ru.divizdev.photogallery.di.IFactory;

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
