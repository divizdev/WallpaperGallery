package ru.divizdev.photogallery.Interaction;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.divizdev.photogallery.API.IPixabayAPI;
import ru.divizdev.photogallery.API.PixabyResponse;
import ru.divizdev.photogallery.BuildConfig;
import ru.divizdev.photogallery.Entities.ImageUI;
import ru.divizdev.photogallery.ui.IViewListPhoto;

public class InteractorPhotoGallery {

    private final static InteractorPhotoGallery _interactorPhotoGallery = new InteractorPhotoGallery();
    private WeakReference<IViewListPhoto> _viewListPhoto;
    private List<ImageUI> _imageUIList;

    public static InteractorPhotoGallery getInstance() {
        return _interactorPhotoGallery;
    }

    public void attache(IViewListPhoto view) {
        _viewListPhoto = new WeakReference<>(view);
        if (_imageUIList == null) {
            loadImage();
        } else{
            showListImages(_imageUIList);
        }
    }

    private void showListImages(List<ImageUI> imageUIList) {
        IViewListPhoto iViewListPhoto = _viewListPhoto.get();
        if (iViewListPhoto != null){
            iViewListPhoto.showListImages(imageUIList);
            iViewListPhoto.showLoadingProgress(false);
        }
    }


    public void detache() {
        _viewListPhoto = null;
    }

    private void loadImage() {

        IViewListPhoto iViewListPhoto = _viewListPhoto.get();
        if (iViewListPhoto != null) {
            iViewListPhoto.showLoadingProgress(true);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        IPixabayAPI pixabayAPI = retrofit.create(IPixabayAPI.class);
        Call<PixabyResponse> data = pixabayAPI.getData(BuildConfig.API_KEY, "photo", "sports", 200, true);
        data.enqueue(new Callback<PixabyResponse>() {
            @Override
            public void onResponse(Call<PixabyResponse> call, @NonNull Response<PixabyResponse> response) {

                if (response.body() != null && response.body().getImages() != null) {

                    _imageUIList = ImageUI.convertList(response.body().getImages());
                    showListImages(_imageUIList);
                }
            }

            @Override
            public void onFailure(Call<PixabyResponse> call, Throwable t) {
                Log.e("r", t.getMessage());
                IViewListPhoto iViewListPhoto = _viewListPhoto.get();
                if (iViewListPhoto != null) {
                    iViewListPhoto.showErrorLoading();
                }

            }
        });

    }
}
