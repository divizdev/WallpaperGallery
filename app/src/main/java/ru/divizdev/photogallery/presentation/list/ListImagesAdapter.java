package ru.divizdev.photogallery.presentation.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ru.divizdev.photogallery.GlideApp;
import ru.divizdev.photogallery.R;
import ru.divizdev.photogallery.entities.ImageUI;

public class ListImagesAdapter extends RecyclerView.Adapter<ListImagesAdapter.ViewHolder> {

    final private List<ImageUI> _list;
    private IImageClickListener _listener;

    public ListImagesAdapter(List<ImageUI> list, IImageClickListener listener) {
        _list = list;
        _listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,
                parent, false);


        return new ViewHolder(view, _listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setData(_list.get(position));

    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    interface IImageClickListener {
        void onClick(ImageUI imageUI);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View _view;
        private IImageClickListener _listener;
        private ImageView _imageView;
        private ImageUI _imageUI;

        public ViewHolder(View itemView, IImageClickListener listener) {
            super(itemView);
            _view = itemView;
            _listener = listener;

            bind();
        }

        public void setData(ImageUI image) {
            _imageUI = image;
            GlideApp.with(this._view).load(image.getPreviewImageUrl()).centerInside().into(_imageView);
        }

        private void bind() {
            _imageView = _view.findViewById(R.id.item_image_view);
            _imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (_listener != null) {
                _listener.onClick(_imageUI);
            }
        }
    }
}
