package ru.divizdev.photogallery.presentation.detail.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.detail.view.IDetailView;

public class DetailPresenter implements IDetailPresenter {


    private ImageUI _imageUI;
    private WeakReference<IDetailView> _viewDetail;
    private final IPhotoGalleryRepository _repository;

    public DetailPresenter(IPhotoGalleryRepository repository) {
        _repository = repository;
    }

    @Override
    public void attachView(@NonNull IDetailView view, Integer id) {
        _viewDetail = new WeakReference<>(view);
        _imageUI = _repository.getImageUI(id);
        view.showImage(_imageUI);
    }

    @Override
    public void detachView() {
        _viewDetail.clear();
    }

    @Override
    public void actionShowAbout() {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showAboutDialog();
        }
    }

    @Override
    public void actionShare() {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showShare(_imageUI);
        }
    }
}
