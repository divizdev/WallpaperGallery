package ru.divizdev.photogallery.data;

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
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.entities.TypeErrorLoad;

public class PhotoGalleryRepository implements IPhotoGalleryRepository {

    private static final String IMAGE_TYPE_DEFAULT = "photo";
    private static final int TOP_DEFAULT = 200;
    private static final String ORDER = "latest";
    private Map<Integer, ImageUI> _imageUIMap;



    @Override
    public void loadListImages(@NonNull final ICallBackListImages callBack) {
        loadListImages(callBack, false);
    }
    @Override
    public void loadListImages(@NonNull final ICallBackListImages callBack, Boolean isRefresh){

        if ( !isRefresh && _imageUIMap!= null && _imageUIMap.size() > 0){
            callBack.onImages(new ArrayList<>(_imageUIMap.values()));
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPixabayAPI pixabayAPI = retrofit.create(IPixabayAPI.class);
        Call<PixabyResponse> data = pixabayAPI.getData(BuildConfig.API_KEY, IMAGE_TYPE_DEFAULT, ImageCategory.animals.name(), ORDER, TOP_DEFAULT, true);
        data.enqueue(new Callback<PixabyResponse>() {
            @Override
            public void onResponse(@NonNull Call<PixabyResponse> call, @NonNull Response<PixabyResponse> response) {

                if (response.body() != null && response.body().getImages() != null) {
                    _imageUIMap = ImageUI.convertToMap(response.body().getImages());
                   callBack.onImages(new ArrayList<>(_imageUIMap.values()));
                }else{

                    callBack.onError(TypeErrorLoad.NoBody, "");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixabyResponse> call, @NonNull Throwable throwable) {
                Log.e("r", throwable.getMessage());
                callBack.onError(TypeErrorLoad.BadConnect, throwable.getMessage());

            }
        });
    }
    @Nullable
   public ImageUI getImageUI(Integer id){
        return _imageUIMap.get(id);
    }

}
