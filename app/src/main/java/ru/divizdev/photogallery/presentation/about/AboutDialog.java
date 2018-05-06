package ru.divizdev.photogallery.presentation.about;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import ru.divizdev.photogallery.R;

public class AboutDialog extends DialogFragment {

    private static final int LINKS_COLUMNS_COUNT = 2;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bitmap image = getBitmapFromDrawable(getActivity().getPackageManager(), getActivity().getPackageName());


        AboutView aboutPage = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.logo_square)
                .setName(R.string.about_title)
                .setBrief(R.string.about_description)
                .setAppIcon(image)
                .setAppName(R.string.app_name)
                .addGitHubLink(R.string.git_hub_link)
                .addWebsiteLink(R.string.website_link)
                .setLinksColumnsCount(LINKS_COLUMNS_COUNT)
                .setShowDivider(false)

                .setVersionNameAsAppSubTitle()
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();


        return new AlertDialog.Builder(getActivity())
                .setView(aboutPage)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

    }

    private Bitmap getBitmapFromDrawable(@Nullable PackageManager packageManager, String packageName) {
        Drawable drawable;
        if (packageManager == null) {
            return null;
        }
        try {
            drawable = packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

}
