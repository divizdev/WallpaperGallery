package ru.divizdev.photogallery.data;

import ru.divizdev.photogallery.entities.ImageCategoryKey;

public interface IPhotoGalleryState {
    ImageCategoryKey getCurrentCategory();

    void setCurrentCategory(ImageCategoryKey currentCategory);

    Integer getIdCurrentImages();

    void setIdCurrentImages(ImageCategoryKey currentCategory, Integer idCurrentImages);
    void setIdCurrentImages(Integer idCurrentImages);
}
