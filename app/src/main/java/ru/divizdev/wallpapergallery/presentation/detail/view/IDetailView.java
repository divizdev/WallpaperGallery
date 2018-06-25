package ru.divizdev.wallpapergallery.presentation.detail.view;

import ru.divizdev.wallpapergallery.entities.ImageCategory;
import ru.divizdev.wallpapergallery.entities.ImageUI;
import ru.divizdev.wallpapergallery.presentation.detail.presenter.IImageUIListAdapter;

public interface IDetailView {
    void showImages(Integer initPosition, IImageUIListAdapter imageUIListAdapter);

    void showAboutDialog();

    void showDialogConfirm();

    void navToDetailScreen();

    void showErrorPermissionMessage();

    void showShare(ImageUI imageUI);

    void setTitle(ImageCategory category);

    void requestPermission();
}
