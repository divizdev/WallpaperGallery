package ru.divizdev.wallpapergallery.presentation.category.presenter;

import java.lang.ref.WeakReference;

import ru.divizdev.wallpapergallery.data.IPhotoGalleryRepository;
import ru.divizdev.wallpapergallery.entities.ImageCategory;
import ru.divizdev.wallpapergallery.presentation.category.adapter.ListCategoriesAdapter;
import ru.divizdev.wallpapergallery.presentation.category.view.IListCategoriesView;

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
