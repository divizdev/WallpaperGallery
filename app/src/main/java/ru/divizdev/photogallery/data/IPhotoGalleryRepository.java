package ru.divizdev.photogallery.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.divizdev.photogallery.entities.ImageUI;

public interface IPhotoGalleryRepository {

    void loadListImages(@NonNull final ICallBackListImages callBack);
    void loadListImages(@NonNull final ICallBackListImages callBack, Boolean isRefresh);
    @Nullable
    ImageUI getImageUI(Integer id);


}
