package ru.divizdev.photogallery.presentation.category.view;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageCategory;

public interface IListCategoriesView {

    void showListImages(List<ImageCategory> listImages);

    void navToListImageScreen(ImageCategory category);

    void showAboutDialog();

}
