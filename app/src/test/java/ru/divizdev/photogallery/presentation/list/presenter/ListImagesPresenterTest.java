package ru.divizdev.photogallery.presentation.list.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.divizdev.photogallery.API.PixabayImage;
import ru.divizdev.photogallery.data.ICallBackListImages;
import ru.divizdev.photogallery.data.IPhotoGalleryRepository;
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageCategoryKey;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.entities.TypeErrorLoad;

public class ListImagesPresenterTest {

    private ListImagesPresenter _listImagesPresenter;

    private IPhotoGalleryRepository _stubPhotoGalleryRepositoryCorrect;
    private IPhotoGalleryRepository _stubPhotoGalleryRepositoryError;

    private List<ImageUI> result;
    private Boolean errorNoBody;

    @Before
    public void setUp() {

        _stubPhotoGalleryRepositoryCorrect = new IPhotoGalleryRepository() {

            @Override
            public void loadListImages(ImageCategoryKey categoryKey, @NonNull ICallBackListImages callBack) {

            }

            @Override
            public void loadListImages(@NonNull ICallBackListImages callBack, Boolean isRefresh, ImageCategoryKey categoryKey) {
                List<ImageUI> imageUIS = new ArrayList<>();
                PixabayImage pixabayImage = new PixabayImage();
                pixabayImage.setId(1);
                imageUIS.add(new ImageUI(pixabayImage));
                callBack.onImages(imageUIS);
            }

            @Nullable
            @Override
            public ImageUI getImageUI(Integer id) {
                return null;
            }

            @Override
            public List<ImageCategory> getCategories() {
                return null;
            }

            @Override
            public ImageCategory getCategories(ImageCategoryKey key) {
                return null;
            }
        };

        _stubPhotoGalleryRepositoryError = new IPhotoGalleryRepository() {


            @Override
            public void loadListImages(ImageCategoryKey categoryKey, @NonNull ICallBackListImages callBack) {

            }

            @Override
            public void loadListImages(@NonNull ICallBackListImages callBack, Boolean isRefresh, ImageCategoryKey categoryKey) {
                callBack.onError(TypeErrorLoad.NoBody, "");

            }

            @Nullable
            @Override
            public ImageUI getImageUI(Integer id) {
                return null;
            }

            @Override
            public List<ImageCategory> getCategories() {
                return null;
            }

            @Override
            public ImageCategory getCategories(ImageCategoryKey key) {
                return null;
            }
        };






    }

    @Test
    public void attachView() {
//        result = null;
//        IListImagesView fakeView = new IListImagesView() {
//
//            @Override
//            public void showListImages(List<ImageUI> listImages) {
//                result = listImages;
//            }
//
//            @Override
//            public void showLoadingProgress(Boolean isView) {
//
//            }
//
//            @Override
//            public void showErrorLoading(String error) {
//
//            }
//
//            @Override
//            public void showErrorNoBody() {
//
//            }
//
//            @Override
//            public void showErrorBadConnect() {
//
//            }
//
//            @Override
//            public void navToDetailScreen(ImageCategory category, Integer id) {
//
//            }
//
//            @Override
//            public void showAboutDialog() {
//
//            }
//
//            @Override
//            public void setTitle(ImageCategory key) {
//
//            }
//        };
//
//        _listImagesPresenter = new ListImagesPresenter(_stubPhotoGalleryRepositoryCorrect, _state);
//        _listImagesPresenter.attachView(fakeView);
//
//        Assert.assertNotNull(result);
//        Assert.assertEquals(1, result.size());
//        Assert.assertEquals(1, result.get(0).getID());

    }

    @Test
    public void detachView() {
    }

    @Test
    public void refreshImageList() {
    }

    @Test
    public void onImages() {
    }

    @Test
    public void onError() {
//        errorNoBody = false;
//        IListImagesView fakeView = new IListImagesView() {
//
//            @Override
//            public void showListImages(List<ImageUI> listImages) {
//                result = listImages;
//            }
//
//            @Override
//            public void showLoadingProgress(Boolean isView) {
//
//            }
//
//            @Override
//            public void showErrorLoading(String error) {
//
//            }
//
//            @Override
//            public void showErrorNoBody() {
//                errorNoBody = true;
//            }
//
//            @Override
//            public void showErrorBadConnect() {
//
//            }
//
//            @Override
//            public void navToDetailScreen(Integer id) {
//
//            }
//
//            @Override
//            public void showAboutDialog() {
//
//            }
//        };
//
//        _listImagesPresenter = new ListImagesPresenter(_stubPhotoGalleryRepositoryCorrect, state);
//        _listImagesPresenter.attachView(fakeView);
//
//        Assert.assertFalse(errorNoBody);
//        _listImagesPresenter = new ListImagesPresenter(_stubPhotoGalleryRepositoryError, state);
//        _listImagesPresenter.attachView(fakeView);
//        Assert.assertTrue(errorNoBody);


    }

    @Test
    public void onClick() {
    }

    @Test
    public void actionShowAbout() {
    }
}