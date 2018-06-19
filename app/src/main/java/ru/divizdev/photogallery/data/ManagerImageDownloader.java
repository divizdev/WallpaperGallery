package ru.divizdev.photogallery.data;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import ru.divizdev.photogallery.entities.ImageUI;

public class ManagerImageDownloader {

    private final WeakReference<Context> _contextWeakReference;

    public ManagerImageDownloader(Context context) {
        _contextWeakReference = new WeakReference<>(context);
    }

    public AsyncTask<ImageUI, Void, String> getImageDownloaderTask(){
        Context context = _contextWeakReference.get();
        if (context != null) {
            return new ImageDownloaderTask(context);
        }
        return null;

    }
}
