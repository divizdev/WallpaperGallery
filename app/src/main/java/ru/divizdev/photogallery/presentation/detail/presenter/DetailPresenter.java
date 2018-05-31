package ru.divizdev.photogallery.presentation.detail.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.divizdev.photogallery.data.ICallBackListImages;
import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.entities.ImageCategoryKey;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.entities.TypeErrorLoad;
import ru.divizdev.photogallery.presentation.detail.view.IDetailView;

public class DetailPresenter implements IDetailPresenter {


    private final IPhotoGalleryRepository _repository;
    private List<ImageUI> _imageUIList;
    private WeakReference<IDetailView> _viewDetail;

    public DetailPresenter(IPhotoGalleryRepository repository) {
        _repository = repository;
    }

    @Override
    public void attachView(@NonNull final IDetailView view, ImageCategoryKey categoryKey, final Integer id) {
        _viewDetail = new WeakReference<>(view);
        _repository.loadListImages(categoryKey, new ICallBackListImages() {
            @Override
            public void onImages(List<ImageUI> imagesList) {
                _imageUIList = imagesList;
                for (int i = 0; i < _imageUIList.size(); i++) {

                    if(_imageUIList.get(i).getID() == id){
                        view.showImages(i, _imageUIList);
                    }
                }
            }

            @Override
            public void onError(TypeErrorLoad typeError, String error) {

            }
        });
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
    public void actionShare(Integer numberImage) {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showShare(_imageUIList.get(numberImage));
        }
    }

    @Override
    public void actionSetWallpaper(Integer numberImage) {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.setWallpaper(_imageUIList.get(numberImage));
        }
    }
}
