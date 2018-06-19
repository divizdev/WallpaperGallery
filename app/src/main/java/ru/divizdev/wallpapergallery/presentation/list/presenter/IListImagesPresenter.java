package ru.divizdev.wallpapergallery.presentation.list.presenter;

import ru.divizdev.wallpapergallery.presentation.list.adapter.ListImagesAdapter;
import ru.divizdev.wallpapergallery.presentation.list.view.IListImagesView;

public interface IListImagesPresenter extends ListImagesAdapter.IImageClickListener {
    void attachView(IListImagesView view);

    void detachView();

    void refreshImageList();

    void actionShowAbout();


}
