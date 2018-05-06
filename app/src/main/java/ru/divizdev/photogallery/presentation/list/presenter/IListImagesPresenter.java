package ru.divizdev.photogallery.presentation.list.presenter;

import ru.divizdev.photogallery.presentation.list.adapter.ListImagesAdapter;
import ru.divizdev.photogallery.presentation.list.view.IListImagesView;

public interface IListImagesPresenter extends ListImagesAdapter.IImageClickListener {
    void attachView(IListImagesView view);

    void detachView();

    void refreshImageList();

    void actionShowAbout();
}
