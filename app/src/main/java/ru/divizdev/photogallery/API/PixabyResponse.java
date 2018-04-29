package ru.divizdev.photogallery.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PixabyResponse {

    @SerializedName("hits")
    private List<PixabayImage> _images;

    @SerializedName("totalHits")
    private int _totalHits;

    @SerializedName("total")
    private int _total;

    public int getTotal() {
        return _total;
    }

    public void setTotal(int total) {
        this._total = total;
    }

    public void setImages(List<PixabayImage> images) {
        this._images = images;
    }

    public List<PixabayImage> getImages() {
        return _images;
    }

    public void setTotalHits(int totalHits) {
        this._totalHits = totalHits;
    }

    public int getTotalHits() {
        return _totalHits;
    }

    @Override
    public String toString() {
        return
                "PixabyResponse{" +
                        "_images = '" + _images + '\'' +
                        ",_totalHits = '" + _totalHits + '\'' +
                        "}";
    }
}