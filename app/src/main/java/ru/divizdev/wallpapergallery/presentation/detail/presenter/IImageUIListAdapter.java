package ru.divizdev.wallpapergallery.presentation.detail.presenter;

import ru.divizdev.wallpapergallery.entities.ImageUI;

public interface IImageUIListAdapter {

    ImageUI getImageUI(int position);
    Integer size();
}
