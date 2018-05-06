package ru.divizdev.photogallery.presentation.category;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageUI;

public interface IListCategoriesView {

    void showListCategory(List<ImageUI> listImages);

    void navToListImagesScreen(Integer id);

    void showAboutDialog();

}
