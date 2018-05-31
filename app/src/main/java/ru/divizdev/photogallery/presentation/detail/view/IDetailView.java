package ru.divizdev.photogallery.presentation.detail.view;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageUI;

public interface IDetailView {
    void showImages(Integer initPosition, List<ImageUI> listImage);
    void showAboutDialog();
    void setWallpaper(ImageUI imageUI);
    void showShare(ImageUI imageUI);
}
