package ru.divizdev.wallpapergallery.data;

import android.support.annotation.Nullable;

import ru.divizdev.wallpapergallery.entities.ImageCategoryKey;

/***
 * Transferring parameters between screens
 */
public interface IPhotoGalleryState {
    @Nullable
    ImageCategoryKey getCurrentCategory();

    void setCurrentCategory(ImageCategoryKey currentCategory);

    Integer getIdCurrentImages();

    void setIdCurrentImages(ImageCategoryKey currentCategory, Integer idCurrentImages);
    void setIdCurrentImages(Integer idCurrentImages);
}
