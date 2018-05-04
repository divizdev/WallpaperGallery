package ru.divizdev.photogallery.utils;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageUI;

public interface ICallBackListImages {

    void onImages(List<ImageUI> imagesList);

    void onError(String error);

}
