package ru.divizdev.photogallery.presentation.detail;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.entities.ImageUI;

public class DetailPresenter {

    private static DetailPresenter _instance = new DetailPresenter();

    public static DetailPresenter getInstance(){
        return _instance;
    }


    private WeakReference<IDetailView> _viewDetail;

    public void attachView(@NonNull IDetailView view, Integer id) {
        _viewDetail = new WeakReference<>(view);
        ImageUI selectImage = PGApplication.getPhotoGalleryInteraction().getImageUI(id);
        view.viewImage(selectImage);
    }

    public void detachView() {
        _viewDetail= null;
    }
}
