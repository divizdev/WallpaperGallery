package ru.divizdev.photogallery.di;

import android.content.Context;

import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.data.IPhotoGalleryState;
import ru.divizdev.photogallery.data.ManagerWallpaperTask;
import ru.divizdev.photogallery.data.PhotoGalleryRepository;
import ru.divizdev.photogallery.data.PhotoGalleryState;
import ru.divizdev.photogallery.data.WallpaperSetTask;
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
    private IPhotoGalleryState _state;
    private ManagerWallpaperTask _wallpaperSetTask;

    public Factory(Context context){
        _state = new PhotoGalleryState();
        _router = new Router(_state);
        _repository = new PhotoGalleryRepository();
        _wallpaperSetTask = new ManagerWallpaperTask(context);
    }

    @Override
    public Router getRouter() {
        return _router;
    }

    @Override
    public IListImagesPresenter getListImagesPresenter() {
        if (_listImagesPresenter == null){
            _listImagesPresenter = new ListImagesPresenter(_repository, _state);
        }
        return _listImagesPresenter;
    }

    @Override
    public IDetailPresenter getDetailPresenter() {
        if(_detailPresenter == null){
            _detailPresenter = new DetailPresenter(_repository, _state, _wallpaperSetTask);
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
