package ru.divizdev.photogallery.presentation.category.presenter;

import java.lang.ref.WeakReference;

import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.presentation.category.adapter.ListCategoriesAdapter;
import ru.divizdev.photogallery.presentation.category.view.IListCategoriesView;

public class ListCategoryPresenter implements  ListCategoriesAdapter.IImageCategoryClickListener, IListCategoryPresenter {

    private final IPhotoGalleryRepository _repository;
    private WeakReference<IListCategoriesView> _view;

    @Override
    public void onClick(ImageCategory category) {
        IListCategoriesView view = _view.get();
        if (view != null) {
            view.navToListImageScreen(category);
        }

    }

    public ListCategoryPresenter(IPhotoGalleryRepository repository){
        _repository = repository;
    }

    @Override
    public void attachView(IListCategoriesView view){
        _view = new WeakReference<>(view);
        view.showListImages(_repository.getCategories());
    }

    @Override
    public void detachView(){
        _view.clear();
    }


    @Override
    public void actionShowAbout(){

        IListCategoriesView view = _view.get();
        if (view != null) {
            view.showAboutDialog();
        }
    }
}
