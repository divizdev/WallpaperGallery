package ru.divizdev.photogallery.presentation.category.presenter;

import ru.divizdev.photogallery.presentation.category.adapter.ListCategoriesAdapter;
import ru.divizdev.photogallery.presentation.category.view.IListCategoriesView;

public interface IListCategoryPresenter extends  ListCategoriesAdapter.IImageCategoryClickListener {
    void attachView(IListCategoriesView view);

    void detachView();

    void actionShowAbout();
}
