package ru.divizdev.photogallery.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.divizdev.photogallery.API.PixabayImage;

public class ImageUI {

    private final PixabayImage _pixabayImage;

    public ImageUI(PixabayImage pixabayImage){
        _pixabayImage = pixabayImage;
    }

    public static List<ImageUI> convertList(List<PixabayImage> list){
        List<ImageUI> result = new ArrayList<>();

        for (PixabayImage item : list) {
            result.add(new ImageUI(item));
        }
        return result;
    }

    public static Map<Integer, ImageUI> convertToMap(List<PixabayImage> list){
        Map<Integer, ImageUI> result = new HashMap<>();

        for (PixabayImage item : list) {
            result.put(item.getId(), new ImageUI(item));
        }
        return result;
    }



    public String getPreviewImageUrl(){
        return _pixabayImage.getWebformatURL();
    }

    public String getDetailImageUrl(){
        return _pixabayImage.getLargeImageURL();
    }

    public int getID(){
        return _pixabayImage.getId();
    }

    public String getTags(){
        return _pixabayImage.getTags();
    }


}
