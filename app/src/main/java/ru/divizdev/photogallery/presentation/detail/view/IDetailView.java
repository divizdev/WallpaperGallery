package ru.divizdev.photogallery.presentation.detail.view;

import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.detail.presenter.IImageUIListAdapter;

public interface IDetailView {
    void showImages(Integer initPosition, IImageUIListAdapter imageUIListAdapter);
    void showAboutDialog();

    void showShare(ImageUI imageUI);
    void setTitle(ImageCategory category);
    void saveImage(ImageUI imageUI);
}
