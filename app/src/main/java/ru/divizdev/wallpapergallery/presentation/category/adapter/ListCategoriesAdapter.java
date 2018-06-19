package ru.divizdev.wallpapergallery.presentation.category.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.divizdev.wallpapergallery.GlideApp;
import ru.divizdev.wallpapergallery.R;
import ru.divizdev.wallpapergallery.entities.ImageCategory;

public class ListCategoriesAdapter extends RecyclerView.Adapter<ListCategoriesAdapter.ViewHolder> {

    private final List<ImageCategory> _imageCategories;
    private final IImageCategoryClickListener _listener;

    public ListCategoriesAdapter(List<ImageCategory> imageCategories, IImageCategoryClickListener imageCategoryClickListener){
        _imageCategories = imageCategories;
        _listener = imageCategoryClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,
                parent, false);
        return new ViewHolder(view, _listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(_imageCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return _imageCategories.size();
    }


    public interface IImageCategoryClickListener {
        void onClick(ImageCategory category);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private IImageCategoryClickListener _listener;
        private View _itemView;
        private TextView _textView;
        private ImageCategory _imageCategory;
        private ImageView _imageView;

        ViewHolder(View itemView, IImageCategoryClickListener listener) {
            super(itemView);
            _itemView = itemView;
            _listener = listener;

            _textView = _itemView.findViewById(R.id.item_text);
            _imageView = _itemView.findViewById(R.id.item_category_image_view);
            _textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _listener.onClick(_imageCategory);
                }
            });

            _imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _listener.onClick(_imageCategory);
                }
            });
        }

        public void setData(ImageCategory category){
            _imageCategory = category;
            _textView.setText(_imageCategory.getName());

            int id =   _itemView.getResources().getIdentifier("ru.divizdev.photogallery:drawable/" + category.getKeyResourceImage(), null, null);

            GlideApp.with(itemView.getContext()).asBitmap().load(id).into(_imageView);

        }


    }
}
