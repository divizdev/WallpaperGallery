package ru.divizdev.wallpapergallery.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ru.divizdev.wallpapergallery.entities.ImageCategory;
import ru.divizdev.wallpapergallery.entities.ImageCategoryKey;
import ru.divizdev.wallpapergallery.entities.ImageUI;

public interface IPhotoGalleryRepository {

    void loadListImages(ImageCategoryKey categoryKey, @NonNull final ICallBackListImages callBack);
    void loadListImages(@NonNull final ICallBackListImages callBack, Boolean isRefresh, ImageCategoryKey categoryKey);
    @Nullable
    ImageUI getImageUI(Integer id);


    List<ImageCategory> getCategories();
    ImageCategory getCategories(ImageCategoryKey key);
}
