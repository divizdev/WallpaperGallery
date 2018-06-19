package ru.divizdev.wallpapergallery.data;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

import ru.divizdev.wallpapergallery.GlideApp;
import ru.divizdev.wallpapergallery.R;
import ru.divizdev.wallpapergallery.entities.ImageUI;

public class WallpaperSetTask extends AsyncTask<ImageUI, Void, Boolean> {

    private final WeakReference<Context> _contextWeakReference;

    WallpaperSetTask(Context context) {
        _contextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected Boolean doInBackground(ImageUI... imageUIS) {
        if (imageUIS.length <= 0) {
            return false;
        }
        final Context context = _contextWeakReference.get();
        if (context == null) {
            return false;
        }
        try {
            ImageUI imageUI = imageUIS[0];
            Bitmap resource = GlideApp.with(context).asBitmap().load(imageUI.getDetailImageUrl()).submit().get();
            WallpaperManager.getInstance(context).setBitmap(resource);
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Context context = _contextWeakReference.get();
        if (context != null) {
            if (result) {
                Toast.makeText(context, R.string.message_ok_wallpaper, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, R.string.message_fail_wallpaper, Toast.LENGTH_LONG).show();
            }
        }
    }
}
