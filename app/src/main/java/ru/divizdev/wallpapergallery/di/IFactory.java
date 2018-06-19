package ru.divizdev.wallpapergallery.di;

import ru.divizdev.wallpapergallery.presentation.Router;
import ru.divizdev.wallpapergallery.presentation.category.presenter.IListCategoryPresenter;
import ru.divizdev.wallpapergallery.presentation.detail.presenter.IDetailPresenter;
import ru.divizdev.wallpapergallery.presentation.list.presenter.IListImagesPresenter;

public interface IFactory {

    Router getRouter();

    IListImagesPresenter getListImagesPresenter();

    IDetailPresenter getDetailPresenter();

    IListCategoryPresenter getListCategoryPresenter();

}
