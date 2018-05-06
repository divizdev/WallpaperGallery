package ru.divizdev.photogallery.interaction;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.divizdev.photogallery.API.IPixabayAPI;
import ru.divizdev.photogallery.API.PixabyResponse;
import ru.divizdev.photogallery.BuildConfig;
import ru.divizdev.photogallery.entities.ImageCategoryKey;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.utils.ICallBackListImages;

public class PhotoGalleryInteraction implements  IPhotoGalleryInteraction {

    private static final String IMAGE_TYPE_DEFAULT = "photo";
    private static final int TOP_DEFAULT = 200;
    private Map<Integer, ImageUI> _imageUIMap;


    public void loadListImages(@NonNull final ICallBackListImages callBack){

        if (_imageUIMap!= null && _imageUIMap.size() > 0){//TODO: Проверка на прокисший запрос
            callBack.onImages(new ArrayList<>(_imageUIMap.values()));
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPixabayAPI pixabayAPI = retrofit.create(IPixabayAPI.class);
        Call<PixabyResponse> data = pixabayAPI.getData(BuildConfig.API_KEY, IMAGE_TYPE_DEFAULT, ImageCategoryKey.animals.name(), TOP_DEFAULT, true);
        data.enqueue(new Callback<PixabyResponse>() {
            @Override
            public void onResponse(Call<PixabyResponse> call, @NonNull Response<PixabyResponse> response) {

                if (response.body() != null && response.body().getImages() != null) {
                    _imageUIMap = ImageUI.convertToMap(response.body().getImages());
                   callBack.onImages(new ArrayList<>(_imageUIMap.values()));
                }else{
                    callBack.onError("");//TODO: Заполнить ошибки
                }
            }

            @Override
            public void onFailure(Call<PixabyResponse> call, Throwable t) {
                Log.e("r", t.getMessage());
                callBack.onError(t.getMessage());

            }
        });
    }
    @Nullable
   public ImageUI getImageUI(Integer id){
        return _imageUIMap.get(id);
    }

}
