package ru.divizdev.photogallery.presentation.detail.presenter;

import android.support.annotation.NonNull;

import ru.divizdev.photogallery.presentation.detail.view.IDetailView;

public interface IDetailPresenter {
    void attachView(@NonNull IDetailView view);

    void detachView();

    void actionShowAbout();

    void actionShare(Integer numberImage);

    void actionSetWallpaper(Integer numberImage);

    void actionSaveFile(Integer numberImage);

    IImageUIListAdapter getImageUIListAdapter();
}
