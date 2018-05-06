package ru.divizdev.photogallery.presentation.detail.presenter;

import android.support.annotation.NonNull;

import ru.divizdev.photogallery.presentation.detail.view.IDetailView;

public interface IDetailPresenter {
    void attachView(@NonNull IDetailView view, Integer id);

    void detachView();

    void actionShowAbout();

    void actionShare();
}
