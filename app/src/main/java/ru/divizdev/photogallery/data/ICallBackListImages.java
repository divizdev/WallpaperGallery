package ru.divizdev.photogallery.data;

import java.util.List;

import ru.divizdev.photogallery.entities.TypeErrorLoad;
import ru.divizdev.photogallery.entities.ImageUI;

public interface ICallBackListImages {

    void onImages(List<ImageUI> imagesList);

    void onError(TypeErrorLoad typeError, String error);

}
