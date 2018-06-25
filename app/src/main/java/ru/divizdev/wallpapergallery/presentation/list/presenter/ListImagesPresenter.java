package ru.divizdev.wallpapergallery.presentation.list.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.divizdev.wallpapergallery.data.ICallBackListImages;
import ru.divizdev.wallpapergallery.data.IPhotoGalleryRepository;
import ru.divizdev.wallpapergallery.data.IPhotoGalleryState;
import ru.divizdev.wallpapergallery.entities.ImageCategory;
import ru.divizdev.wallpapergallery.entities.ImageUI;
import ru.divizdev.wallpapergallery.entities.TypeErrorLoad;
import ru.divizdev.wallpapergallery.presentation.list.view.IListImagesView;

public class ListImagesPresenter implements ICallBackListImages, IListImagesPresenter {


    private WeakReference<IListImagesView> _viewListPhoto;
    private final IPhotoGalleryRepository _repository;
    private final IPhotoGalleryState _state;
    private ImageCategory _category;

    public ListImagesPresenter(IPhotoGalleryRepository repository, IPhotoGalleryState state){
        _repository = repository;
        _state = state;
    }


    @Override
    public void attachView(@NonNull IListImagesView view) {
        _viewListPhoto = new WeakReference<>(view);
        if (_state.getCurrentCategory() == null) {
            view.navToMainScreen();
            return;
        }
        _category = _repository.getCategories(_state.getCurrentCategory());
        loadImage(false);
        view.setTitle(_category);
    }


    @Override
    public void detachView() {
        _viewListPhoto.clear();
    }

    @Override
    public void refreshImageList() {
        loadImage(true);
    }


    private void loadImage(Boolean isRefresh) {

        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.showLoadingProgress(true);
        }
        _repository.loadListImages(this, isRefresh, _category.getKey());
    }

    @Override
    public void onImages(List<ImageUI> imagesList) {

        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.showLoadingProgress(false);
            iViewListImages.showListImages(imagesList);
        }

    }

    @Override
    public void onError(TypeErrorLoad typeError, String error) {
        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {

            switch (typeError) {

                case NoBody:
                    iViewListImages.showErrorNoBody();
                    break;
                case BadConnect:
                    iViewListImages.showErrorBadConnect();
                    break;
                default:
                    iViewListImages.showErrorLoading(error);
                    break;
            }
            iViewListImages.showLoadingProgress(false);

        }
    }

    @Override
    public void onClick(ImageUI imageUI) {

        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.navToDetailScreen(_category, imageUI.getID());
        }
    }

    @Override
    public void actionShowAbout() {
        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.showAboutDialog();
        }

    }
}
