package ru.divizdev.photogallery.presentation.list.view;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageUI;

public interface IListImagesView {

    void showListImages(List<ImageUI> listImages);

    void showLoadingProgress(Boolean isView);

    void showErrorLoading(String error);

    void showErrorNoBody();

    void showErrorBadConnect();

    void navToDetailScreen(ImageCategory category, Integer id);

    void showAboutDialog();

    void setTitle(ImageCategory key);

}
