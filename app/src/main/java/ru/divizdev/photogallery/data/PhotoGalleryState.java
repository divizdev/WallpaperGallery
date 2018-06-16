package ru.divizdev.photogallery.data;

import ru.divizdev.photogallery.entities.ImageCategoryKey;

/***
 * Transferring parameters between screens
 */
public class PhotoGalleryState implements IPhotoGalleryState {

    public static final int NO_ID_IMAGE = -1;

    private ImageCategoryKey _currentCategory;
    private Integer _idCurrentImages = NO_ID_IMAGE;



    @Override
    public ImageCategoryKey getCurrentCategory() {
        return _currentCategory;
    }

    @Override
    public void setCurrentCategory(ImageCategoryKey currentCategory) {
        _idCurrentImages = NO_ID_IMAGE;
        _currentCategory = currentCategory;
    }

    @Override
    public Integer getIdCurrentImages() {
        return _idCurrentImages;
    }

    @Override
    public void setIdCurrentImages(ImageCategoryKey currentCategory, Integer idCurrentImages) {
        _idCurrentImages = idCurrentImages;
        _currentCategory = currentCategory;

    }

    @Override
    public void setIdCurrentImages(Integer idCurrentImages) {
        _idCurrentImages = idCurrentImages;
    }

}
