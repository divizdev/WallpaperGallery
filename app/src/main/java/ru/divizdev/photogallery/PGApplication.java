package ru.divizdev.photogallery;

import android.app.Application;

import ru.divizdev.photogallery.interaction.PhotoGalleryInteraction;
import ru.divizdev.photogallery.presentation.Router;

public class PGApplication extends Application {

    private static PhotoGalleryInteraction _pgInteraction;
    private static Router _router;

    public static PhotoGalleryInteraction getPhotoGalleryInteraction() {
        return _pgInteraction;
    }

    public static Router getRouter(){
        return _router;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _router = new Router();
        _pgInteraction = new PhotoGalleryInteraction();

    }
}
