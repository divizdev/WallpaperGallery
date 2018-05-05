package ru.divizdev.photogallery.presentation.detail;

import ru.divizdev.photogallery.entities.ImageUI;

interface IDetailView {
    void showImage(ImageUI image);
    void showAboutDialog();

}
