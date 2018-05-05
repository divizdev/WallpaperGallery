package ru.divizdev.photogallery.presentation.detail;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.entities.ImageUI;

public class DetailPresenter {

    private static DetailPresenter _instance = new DetailPresenter();
    private ImageUI _imageUI;
    private WeakReference<IDetailView> _viewDetail;

    public static DetailPresenter getInstance() {
        return _instance;
    }

    public void attachView(@NonNull IDetailView view, Integer id) {
        _viewDetail = new WeakReference<>(view);
        _imageUI = PGApplication.getPhotoGalleryInteraction().getImageUI(id);
        view.showImage(_imageUI);
    }

    public void detachView() {
        _viewDetail = null;
    }

    public void actionShowAbout() {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showAboutDialog();
        }
    }

    public void actionShare() {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showShare(_imageUI);
        }
    }
}
