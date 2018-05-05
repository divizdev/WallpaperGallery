package ru.divizdev.photogallery.interaction;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.utils.ICallBackListImages;

public interface IPhotoGalleryInteraction {

    void loadListImages(@NonNull final ICallBackListImages callBack);
    @Nullable
    ImageUI getImageUI(Integer id);


}
