package ru.divizdev.photogallery.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageCategoryKey;
import ru.divizdev.photogallery.entities.ImageUI;

public interface IPhotoGalleryRepository {

    void loadListImages(@NonNull final ICallBackListImages callBack);
    void loadListImages(@NonNull final ICallBackListImages callBack, Boolean isRefresh, ImageCategoryKey categoryKey);
    @Nullable
    ImageUI getImageUI(Integer id);


    List<ImageCategory> getCategories();
}
