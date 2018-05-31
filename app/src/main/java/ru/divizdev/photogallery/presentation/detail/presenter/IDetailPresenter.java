package ru.divizdev.photogallery.presentation.detail.presenter;

import android.support.annotation.NonNull;

import ru.divizdev.photogallery.entities.ImageCategoryKey;
import ru.divizdev.photogallery.presentation.detail.view.IDetailView;

public interface IDetailPresenter {
    void attachView(@NonNull IDetailView view, ImageCategoryKey category, Integer id);

    void detachView();

    void actionShowAbout();

    void actionShare(Integer numberImage);

    void actionSetWallpaper(Integer numberImage);
}
