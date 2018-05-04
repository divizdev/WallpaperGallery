package ru.divizdev.photogallery.interaction;

import android.support.annotation.NonNull;

import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.utils.ICallBackListImages;

public interface IPhotoGalleryInteraction {

    void loadListImages(@NonNull final ICallBackListImages callBack);

    void selectImage(@NonNull ImageUI image);

    ImageUI getSelectImage();


}
