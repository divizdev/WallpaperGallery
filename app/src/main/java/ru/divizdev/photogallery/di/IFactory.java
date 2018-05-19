package ru.divizdev.photogallery.di;

import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.category.presenter.IListCategoryPresenter;
import ru.divizdev.photogallery.presentation.detail.presenter.IDetailPresenter;
import ru.divizdev.photogallery.presentation.list.presenter.IListImagesPresenter;

public interface IFactory {

    Router getRouter();

    IListImagesPresenter getListImagesPresenter();

    IDetailPresenter getDetailPresenter();

    IListCategoryPresenter getListCategoryPresenter();

}
