package ru.divizdev.photogallery.interaction;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.divizdev.photogallery.API.IPixabayAPI;
import ru.divizdev.photogallery.API.PixabyResponse;
import ru.divizdev.photogallery.BuildConfig;
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.utils.ICallBackListImages;

public class PhotoGalleryInteraction implements  IPhotoGalleryInteraction {

    private static final String IMAGE_TYPE_DEFAULT = "photo";
    private static final int TOP_DEFAULT = 200;
    private List<ImageUI> _imageUIList;

    private ImageUI _selectImage;




    public void loadListImages(@NonNull final ICallBackListImages callBack){

        if (_imageUIList!= null && _imageUIList.size() > 0){
            callBack.onImages(_imageUIList);
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        IPixabayAPI pixabayAPI = retrofit.create(IPixabayAPI.class);
        Call<PixabyResponse> data = pixabayAPI.getData(BuildConfig.API_KEY, IMAGE_TYPE_DEFAULT, ImageCategory.animals.name(), TOP_DEFAULT, true);
        data.enqueue(new Callback<PixabyResponse>() {
            @Override
            public void onResponse(Call<PixabyResponse> call, @NonNull Response<PixabyResponse> response) {

                if (response.body() != null && response.body().getImages() != null) {

                    _imageUIList = ImageUI.convertList(response.body().getImages());
                   callBack.onImages(_imageUIList);
                }
            }

            @Override
            public void onFailure(Call<PixabyResponse> call, Throwable t) {
                Log.e("r", t.getMessage());
                callBack.onError(t.getMessage());

            }
        });
    }

    @Override
    public void selectImage(@NonNull ImageUI image) {
        _selectImage = image;


    }

    @Override
    public ImageUI getSelectImage() {
        return _selectImage;
    }
}
