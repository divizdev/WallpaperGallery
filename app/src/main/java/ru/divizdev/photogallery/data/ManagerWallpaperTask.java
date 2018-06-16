package ru.divizdev.photogallery.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import ru.divizdev.photogallery.entities.ImageUI;

public class ManagerWallpaperTask {

    private final WeakReference<Context> _contextWeakReference;

    public ManagerWallpaperTask(Context context) {
        _contextWeakReference = new WeakReference<>(context);
    }

    @Nullable
    public AsyncTask<ImageUI, Void, Boolean> getWallpaperSetTask() {
        Context context = _contextWeakReference.get();
        if (context != null) {
            return new WallpaperSetTask(context);
        }
        return null;
    }

}
