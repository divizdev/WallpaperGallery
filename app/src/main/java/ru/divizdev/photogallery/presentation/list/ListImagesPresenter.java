package ru.divizdev.photogallery.presentation.list;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.utils.ICallBackListImages;

public class ListImagesPresenter implements ICallBackListImages, ListImagesAdapter.IImageClickListener {

    private final static ListImagesPresenter _listImagesPresenter = new ListImagesPresenter();
    private WeakReference<IListImagesView> _viewListPhoto;


    public static ListImagesPresenter getInstance() {
        return _listImagesPresenter;
    }

    public void attachView(IListImagesView view) {
        _viewListPhoto = new WeakReference<>(view);
        loadImage();
    }

    private void showListImages(List<ImageUI> imageUIList) {
        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.showListImages(imageUIList);
            iViewListImages.showLoadingProgress(false);
        }
    }


    public void detachView() {
        _viewListPhoto = null;
    }


    private void loadImage() {

        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.showLoadingProgress(true);
        }

        PGApplication.getPhotoGalleryInteraction().loadListImages(this);


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
    public void onError(String error) {
        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.showErrorLoading(error);
        }
    }

    @Override
    public void onClick(ImageUI imageUI) {
        PGApplication.getPhotoGalleryInteraction().selectImage(imageUI);
        IListImagesView iViewListImages = _viewListPhoto.get();
        if (iViewListImages != null) {
            iViewListImages.navToDetailScreen();
        }
    }
}
