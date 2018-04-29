package ru.divizdev.photogallery.ui;

import java.util.List;

import ru.divizdev.photogallery.Entities.ImageUI;

public interface IViewListPhoto {

    void showListImages(List<ImageUI> listImages);

    void showLoadingProgress(Boolean isView);

    void showErrorLoading();

}
