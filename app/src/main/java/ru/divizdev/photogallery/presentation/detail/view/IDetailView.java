package ru.divizdev.photogallery.presentation.detail.view;

import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.detail.presenter.IImageUIListAdapter;

public interface IDetailView {
    void showImages(Integer initPosition, IImageUIListAdapter imageUIListAdapter);
    void showAboutDialog();
    void setWallpaper(ImageUI imageUI);
    void showShare(ImageUI imageUI);
}
