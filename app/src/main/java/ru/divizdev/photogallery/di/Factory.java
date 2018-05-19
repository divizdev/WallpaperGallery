package ru.divizdev.photogallery.di;

import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.data.PhotoGalleryRepository;
import ru.divizdev.photogallery.presentation.Router;
import ru.divizdev.photogallery.presentation.category.presenter.IListCategoryPresenter;
import ru.divizdev.photogallery.presentation.category.presenter.ListCategoryPresenter;
import ru.divizdev.photogallery.presentation.detail.presenter.DetailPresenter;
import ru.divizdev.photogallery.presentation.detail.presenter.IDetailPresenter;
import ru.divizdev.photogallery.presentation.list.presenter.IListImagesPresenter;
import ru.divizdev.photogallery.presentation.list.presenter.ListImagesPresenter;

public class Factory implements IFactory {

    private IPhotoGalleryRepository _repository;
    private Router _router;
    private IListImagesPresenter _listImagesPresenter;
    private IDetailPresenter _detailPresenter;
    private IListCategoryPresenter _listCategoryPresenter;

    public Factory(){
        _router = new Router();
        _repository = new PhotoGalleryRepository();
    }

    @Override
    public Router getRouter() {
        return _router;
    }

    @Override
    public IListImagesPresenter getListImagesPresenter() {
        if (_listImagesPresenter == null){
            _listImagesPresenter = new ListImagesPresenter(_repository);
        }
        return _listImagesPresenter;
    }

    @Override
    public IDetailPresenter getDetailPresenter() {
        if(_detailPresenter == null){
            _detailPresenter = new DetailPresenter(_repository);
        }
        return _detailPresenter;
    }

    @Override
    public IListCategoryPresenter getListCategoryPresenter() {
        if (_listCategoryPresenter == null) {
            _listCategoryPresenter = new ListCategoryPresenter(_repository);
        }
        return  _listCategoryPresenter;
    }
}
