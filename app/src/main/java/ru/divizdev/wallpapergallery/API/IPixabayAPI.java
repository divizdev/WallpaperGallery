package ru.divizdev.wallpapergallery.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPixabayAPI {

    @GET("api/")
    Call<PixabyResponse> getData(@Query("key") String key, @Query("image_type") String imageType, @Query("category") String category, @Query("order") String order, @Query("per_page") int top, @Query("safesearch") Boolean safesearch);



}
