package ru.divizdev.wallpapergallery.presentation.detail.presenter;

import android.support.annotation.NonNull;

import ru.divizdev.wallpapergallery.presentation.detail.view.IDetailView;

public interface IDetailPresenter {
    void attachView(@NonNull IDetailView view);

    void detachView();

    void actionShowAbout();

    void actionShare(Integer numberImage);

    void actionSetWallpaper(Integer numberImage);

    void actionSaveFile(Integer numberImage);

    void resultPermission(Boolean result, Integer numberImage);

    void resultConfirmInstallWallpaper(Boolean result, Integer numberImage);

    IImageUIListAdapter getImageUIListAdapter();
}
