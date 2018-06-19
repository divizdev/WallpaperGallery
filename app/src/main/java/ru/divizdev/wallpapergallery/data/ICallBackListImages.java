package ru.divizdev.wallpapergallery.data;

import java.util.List;

import ru.divizdev.wallpapergallery.entities.TypeErrorLoad;
import ru.divizdev.wallpapergallery.entities.ImageUI;

public interface ICallBackListImages {

    void onImages(List<ImageUI> imagesList);

    void onError(TypeErrorLoad typeError, String error);

}
