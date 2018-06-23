package ru.divizdev.wallpapergallery.presentation.detail.presenter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.divizdev.wallpapergallery.data.ICallBackListImages;
import ru.divizdev.wallpapergallery.data.IPhotoGalleryRepository;
import ru.divizdev.wallpapergallery.data.IPhotoGalleryState;
import ru.divizdev.wallpapergallery.data.ManagerImageDownloader;
import ru.divizdev.wallpapergallery.data.ManagerWallpaperTask;
import ru.divizdev.wallpapergallery.entities.ImageUI;
import ru.divizdev.wallpapergallery.entities.TypeErrorLoad;
import ru.divizdev.wallpapergallery.presentation.detail.view.IDetailView;

public class DetailPresenter implements IDetailPresenter, IImageUIListAdapter {


    private final IPhotoGalleryRepository _repository;
    private final IPhotoGalleryState _state;
    private final ManagerWallpaperTask _managerWallpaperTask;
    private final ManagerImageDownloader _managerImageDownloader;
    private List<ImageUI> _imageUIList;
    private WeakReference<IDetailView> _viewDetail;


    public DetailPresenter(IPhotoGalleryRepository repository, IPhotoGalleryState state, ManagerWallpaperTask managerWallpaperTask, ManagerImageDownloader managerImageDownloader) {
        _repository = repository;
        _state = state;
        _managerWallpaperTask = managerWallpaperTask;
        _managerImageDownloader = managerImageDownloader;
    }

    @Override
    public void attachView(@NonNull final IDetailView view) {
        _viewDetail = new WeakReference<>(view);
        view.setTitle(_repository.getCategories(_state.getCurrentCategory()));
        _repository.loadListImages(_state.getCurrentCategory(), new ICallBackListImages() {
            @Override
            public void onImages(List<ImageUI> imagesList) {
                _imageUIList = imagesList;
                for (int i = 0; i < _imageUIList.size(); i++) {

                    if (_imageUIList.get(i).getID() == _state.getIdCurrentImages()) {
                        view.showImages(i, getImageUIListAdapter());
                        break;
                    }
                }
            }

            @Override
            public void onError(TypeErrorLoad typeError, String error) {

            }
        });
    }


    @Override
    public void detachView() {
        _viewDetail.clear();
    }

    @Override
    public void actionShowAbout() {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showAboutDialog();
        }
    }

    @Override
    public void actionShare(Integer numberImage) {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showShare(_imageUIList.get(numberImage));
        }
    }

    @Override
    public void actionSetWallpaper(Integer numberImage) {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.showDialogConfirm();
        }
    }

    @Override
    public void actionSaveFile(Integer numberImage) {
        IDetailView view = _viewDetail.get();
        if (view != null) {
            view.requestPermission();
        }
    }

    @Override
    public void resultPermission(Boolean result, Integer numberImage) {

        if (result) {
            AsyncTask<ImageUI, Void, String> imageDownloaderTask = _managerImageDownloader.getImageDownloaderTask();
            if (imageDownloaderTask != null) {
                imageDownloaderTask.execute(_imageUIList.get(numberImage));
            }
        }else{
            IDetailView view = _viewDetail.get();
            if (view != null) {
                view.showErrorPermissionMessage();
            }
        }

    }

    @Override
    public void resultConfirmInstallWallpaper(Boolean result, Integer numberImage) {
        if(result){
            AsyncTask<ImageUI, Void, Boolean> execute = _managerWallpaperTask.getWallpaperSetTask();
            if (execute != null) {
                execute.execute(_imageUIList.get(numberImage));
            }
        }
    }

    @Override
    public IImageUIListAdapter getImageUIListAdapter() {
        return this;
    }

    @Override
    public ImageUI getImageUI(int position) {

        return _imageUIList.get(position);
    }

    @Override
    public Integer size() {
        return _imageUIList.size();
    }
}
