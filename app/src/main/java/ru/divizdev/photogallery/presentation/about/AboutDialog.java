package ru.divizdev.photogallery.presentation.about;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import ru.divizdev.photogallery.R;

public class AboutDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {




        AboutView aboutPage = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.logo_square)
//                .setCover(R.mipmap.profile_cover)
                .setName("DivizDev")
                .setBrief("This is simple image gallery. The Pixabay is a images source.")
//                .setAppIcon(R.mipmap.logo_square)
                .setAppName(R.string.app_name)
                .addGitHubLink("divizdev/PhotoGallery")
                .addWebsiteLink("https://pixabay.com")
                .setLinksColumnsCount(2)
                .setShowDivider(false)
                .setVersionNameAsAppSubTitle()
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();


        return new AlertDialog.Builder(getActivity())
//                .setTitle(R.string.about_title)
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
