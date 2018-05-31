package ru.divizdev.photogallery.presentation.detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.presentation.detail.view.DetailFragment;

public class DetailPagerAdapter extends FragmentStatePagerAdapter {


    private final List<ImageUI> _imageUIList;

    public DetailPagerAdapter(FragmentManager fm, List<ImageUI> images) {
        super(fm);
        _imageUIList = images;
    }

    @Override
    public Fragment getItem(int position) {
        ImageUI imageUI = _imageUIList.get(position);
        return DetailFragment.newInstance(imageUI.getID(), imageUI.getDetailImageUrl());
    }

    @Override
    public int getCount() {
        return _imageUIList.size();
    }
}
