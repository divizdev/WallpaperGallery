package ru.divizdev.photogallery.presentation.detail.presenter;

import ru.divizdev.photogallery.entities.ImageUI;

public interface IImageUIListAdapter {

    ImageUI getImageUI(int position);
    Integer size();
}
