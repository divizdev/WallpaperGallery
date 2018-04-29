package ru.divizdev.photogallery.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ru.divizdev.photogallery.Entities.ImageUI;
import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder> {

    final private List<ImageUI> _list;

    public PhotoGalleryAdapter(List<ImageUI> list) {
        _list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.setData(_list.get(position));

    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View _view;
        private ImageView _imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            _view = itemView;
            bind();
        }

        public void setData(ImageUI image) {
            GlideApp.with(this._view).load(image.getPreviewImageUrl()).centerInside().into(_imageView);
        }

        private void bind() {
            _imageView = _view.findViewById(R.id.photo_image_view);

        }
    }
}
