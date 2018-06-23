package ru.divizdev.wallpapergallery;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import ru.divizdev.wallpapergallery.di.Factory;
import ru.divizdev.wallpapergallery.di.IFactory;

public class PGApplication extends Application {

    private static IFactory _factory;


    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        _factory = new Factory(getApplicationContext());
    }

    public static IFactory getFactory() {
        return _factory;
    }
}
