package ru.divizdev.photogallery.presentation.detail.view;

import ru.divizdev.photogallery.entities.ImageUI;

public interface IDetailView {
    void showImage(ImageUI image);
    void showAboutDialog();
    void showShare(ImageUI imageUI);
}
