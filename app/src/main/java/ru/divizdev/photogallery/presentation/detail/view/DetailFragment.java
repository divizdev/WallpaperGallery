package ru.divizdev.photogallery.presentation.detail.view;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_ID_IMAGE = "DetailFragment.idImage";
    private static final String ARG_PARAM_URL_IMAGE = "DetailFragment.urlImage";

    // TODO: Rename and change types of parameters
    private String _urlImage;
    private Integer _idImage;

    private ImageView _imageView;
    private ProgressBar _progressBar;

    private OnFragmentInteractionListener mListener;


    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance(Integer idImage, String urlImage) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_ID_IMAGE, idImage);
        args.putString(ARG_PARAM_URL_IMAGE, urlImage);
        fragment.setArguments(args);
        return fragment;
    }

    public void showImage(String urlImage) {
        GlideApp.with(this).load(urlImage).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                _progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                _progressBar.setVisibility(View.GONE);
                return false;
            }
        }).centerInside().into(_imageView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _urlImage = getArguments().getString(ARG_PARAM_URL_IMAGE);
            _idImage = getArguments().getInt(ARG_PARAM_ID_IMAGE);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(
                R.layout.fragment_detail, container, false);
        _imageView = view.findViewById(R.id.image_detail);
        _progressBar = view.findViewById(R.id.progress_bar_detail_image);
        showImage(_urlImage);
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
