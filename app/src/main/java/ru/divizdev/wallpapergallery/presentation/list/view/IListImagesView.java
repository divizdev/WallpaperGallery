package ru.divizdev.wallpapergallery.presentation.list.view;

import java.util.List;

import ru.divizdev.wallpapergallery.entities.ImageCategory;
import ru.divizdev.wallpapergallery.entities.ImageUI;

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
