package ru.divizdev.wallpapergallery.presentation.category.view;

import java.util.List;

import ru.divizdev.wallpapergallery.entities.ImageCategory;

public interface IListCategoriesView {

    void showListImages(List<ImageCategory> listImages);

    void navToListImageScreen(ImageCategory category);

    void showAboutDialog();

}
