package ru.divizdev.wallpapergallery.di;

import android.content.Context;

import ru.divizdev.wallpapergallery.data.IPhotoGalleryRepository;
import ru.divizdev.wallpapergallery.data.IPhotoGalleryState;
import ru.divizdev.wallpapergallery.data.ManagerImageDownloader;
import ru.divizdev.wallpapergallery.data.ManagerWallpaperTask;
import ru.divizdev.wallpapergallery.data.PhotoGalleryRepository;
import ru.divizdev.wallpapergallery.data.PhotoGalleryState;
import ru.divizdev.wallpapergallery.presentation.Router;
import ru.divizdev.wallpapergallery.presentation.category.presenter.IListCategoryPresenter;
import ru.divizdev.wallpapergallery.presentation.category.presenter.ListCategoryPresenter;
import ru.divizdev.wallpapergallery.presentation.detail.presenter.DetailPresenter;
import ru.divizdev.wallpapergallery.presentation.detail.presenter.IDetailPresenter;
import ru.divizdev.wallpapergallery.presentation.list.presenter.IListImagesPresenter;
import ru.divizdev.wallpapergallery.presentation.list.presenter.ListImagesPresenter;

public class Factory implements IFactory {

    private IPhotoGalleryRepository _repository;
    private Router _router;
    private IListImagesPresenter _listImagesPresenter;
    private IDetailPresenter _detailPresenter;
    private IListCategoryPresenter _listCategoryPresenter;
    private IPhotoGalleryState _state;
    private ManagerWallpaperTask _managerWallpaperTask;
    private ManagerImageDownloader _managerImageDownloader;

    public Factory(Context context){
        _state = new PhotoGalleryState();
        _router = new Router(_state);
        _repository = new PhotoGalleryRepository();
        _managerWallpaperTask = new ManagerWallpaperTask(context);
        _managerImageDownloader = new ManagerImageDownloader(context);
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
            _detailPresenter = new DetailPresenter(_repository, _state, _managerWallpaperTask, _managerImageDownloader);
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
