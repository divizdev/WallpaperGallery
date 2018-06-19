package ru.divizdev.wallpapergallery.presentation.detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.divizdev.wallpapergallery.entities.ImageUI;
import ru.divizdev.wallpapergallery.presentation.detail.presenter.IImageUIListAdapter;
import ru.divizdev.wallpapergallery.presentation.detail.view.DetailFragment;

public class DetailPagerAdapter extends FragmentStatePagerAdapter {


    private final IImageUIListAdapter _imageUIList;

    public DetailPagerAdapter(FragmentManager fm, IImageUIListAdapter imageUIListAdapter) {
        super(fm);
        _imageUIList = imageUIListAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        ImageUI imageUI = _imageUIList.getImageUI(position);
        return DetailFragment.newInstance(imageUI.getID(), imageUI.getDetailImageUrl());
    }

    @Override
    public int getCount() {
        return _imageUIList.size();
    }


}
