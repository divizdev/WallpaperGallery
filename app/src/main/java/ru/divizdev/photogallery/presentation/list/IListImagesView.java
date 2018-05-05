package ru.divizdev.photogallery.presentation.list;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageUI;

public interface IListImagesView {

    void showListImages(List<ImageUI> listImages);

    void showLoadingProgress(Boolean isView);

    void showErrorLoading(String error);

    void navToDetailScreen();

}
