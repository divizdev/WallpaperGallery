package ru.divizdev.photogallery.data;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;

public class ImageDownloaderTask extends AsyncTask<ImageUI, Void, String> {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private final WeakReference<Context> _contextWeakReference;


    public ImageDownloaderTask(Context context) {
        _contextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        Context context = _contextWeakReference.get();
        if (context != null) {

        }
    }

    @Override
    protected String doInBackground(ImageUI... imageUIS) {
        if (imageUIS.length <= 0) {
            return null;
        }
        Context context = _contextWeakReference.get();
        if (context == null) {
            return null;
        }
        ImageUI imageUI = imageUIS[0];
        try {
            Bitmap bitmap = GlideApp.with(context).asBitmap().load(imageUI.getDetailImageUrl()).submit().get();
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        imageUI.getFileName());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }catch (SecurityException e){
                    e.printStackTrace();
                    return null;
                }
            return file.toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Context context = _contextWeakReference.get();
        if (context != null) {
            if (result != null) {
                String message = context.getString(R.string.message_ok_save_image) + " \"" + result  + "\"";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, R.string.message_fail_save_image, Toast.LENGTH_LONG).show();
            }
        }

    }
}
