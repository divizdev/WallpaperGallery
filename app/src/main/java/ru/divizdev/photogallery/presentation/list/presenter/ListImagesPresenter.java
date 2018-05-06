package ru.divizdev.photogallery.presentation.list.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.divizdev.photogallery.PGApplication;
import ru.divizdev.photogallery.data.ICallBackListImages;
import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.data.PhotoGalleryRepository;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.entities.TypeErrorLoad;
import ru.divizdev.photogallery.presentation.list.view.IListImagesView;

public class ListImagesPresenter implements ICallBackListImages, IListImagesPresenter {


    private WeakReference<IListImagesView> _viewListPhoto;
    private final IPhotoGalleryRepository _repository;

    public ListImagesPresenter(IPhotoGalleryRepository repository){
        _repository = repository;
    }


    @Override
    public void attachView(IListImagesView view) {
        _viewListPhoto = new WeakReference<>(view);
        loadImage(false);
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
        _repository.loadListImages(this, isRefresh);
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
            iViewListImages.navToDetailScreen(imageUI.getID());
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
