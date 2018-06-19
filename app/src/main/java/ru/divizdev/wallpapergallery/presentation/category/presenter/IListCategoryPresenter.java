package ru.divizdev.wallpapergallery.presentation.category.presenter;

import ru.divizdev.wallpapergallery.presentation.category.adapter.ListCategoriesAdapter;
import ru.divizdev.wallpapergallery.presentation.category.view.IListCategoriesView;

public interface IListCategoryPresenter extends  ListCategoriesAdapter.IImageCategoryClickListener {
    void attachView(IListCategoriesView view);

    void detachView();

    void actionShowAbout();
}
