package ru.divizdev.photogallery.presentation.about;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import ru.divizdev.photogallery.R;

public class AboutDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String version = "1.0";
        try {
            PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        View aboutPage = new AboutPage(getContext())
                .setDescription(getContext().getResources().getString(R.string.about_description))
                .addItem(new Element().setTitle(R.string.about_version_title + version))
                .addWebsite("http://divizdev.ru")
                 .addGitHub("divizdev/PhotoGallery")
                .create();


        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.about_title)
                .setView(aboutPage)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

    }

}
