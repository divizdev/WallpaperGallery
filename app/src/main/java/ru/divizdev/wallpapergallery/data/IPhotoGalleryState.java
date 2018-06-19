package ru.divizdev.wallpapergallery.data;

import ru.divizdev.wallpapergallery.entities.ImageCategoryKey;

/***
 * Transferring parameters between screens
 */
public interface IPhotoGalleryState {
    ImageCategoryKey getCurrentCategory();

    void setCurrentCategory(ImageCategoryKey currentCategory);

    Integer getIdCurrentImages();

    void setIdCurrentImages(ImageCategoryKey currentCategory, Integer idCurrentImages);
    void setIdCurrentImages(Integer idCurrentImages);
}
