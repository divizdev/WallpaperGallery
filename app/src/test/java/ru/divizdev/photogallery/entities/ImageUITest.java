package ru.divizdev.photogallery.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.divizdev.photogallery.API.PixabayImage;

public class ImageUITest {

    private List<PixabayImage> _pixabayImages = new ArrayList<>();
    @Before
    public void setUp() {
        PixabayImage newPixabayIamge = new PixabayImage();
        newPixabayIamge.setId(1);
        newPixabayIamge.setWebformatURL("1");
        _pixabayImages.add(newPixabayIamge);

        newPixabayIamge = new PixabayImage();
        newPixabayIamge.setId(2);
        newPixabayIamge.setWebformatURL("2");
        _pixabayImages.add(newPixabayIamge);
    }


    @Test
    public void convertToMap() {


        Map<Integer, ImageUI> uiMap = ImageUI.convertToMap(_pixabayImages);

        Assert.assertEquals(2, uiMap.get(2).getID());



    }
}