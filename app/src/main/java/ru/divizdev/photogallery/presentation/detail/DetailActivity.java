package ru.divizdev.photogallery.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private ImageView _imageView;
    private DetailPresenter _detailPresenter = DetailPresenter.getInstance();

    private ScaleGestureDetector _scaleGestureDetector;
    private float _scaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        _imageView = findViewById(R.id.image_detail);
        _scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        _detailPresenter.attachView(this);
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public void viewImage(ImageUI image){
        GlideApp.with(this).load(image.getDetailImageUrl()).centerInside().into(_imageView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        _scaleGestureDetector.onTouchEvent(ev);

        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            _scaleFactor *= scaleGestureDetector.getScaleFactor();
            _scaleFactor = Math.max(1f,
                    Math.min(_scaleFactor, 10.0f));
            _imageView.setScaleX(_scaleFactor);
            _imageView.setScaleY(_scaleFactor);
            return true;
        }
    }

}
