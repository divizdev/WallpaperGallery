package ru.divizdev.wallpapergallery.entities;

public class ImageCategory {

    private static final String PREFIX_RESOURCE_NAME = "category_name_";
    private static final String PREFIX_RESOURCE_IMAGE = "category_image_";

    private final ImageCategoryKey _key;
    private final String _name;
    private String _image;

    public ImageCategory(ImageCategoryKey key, String name, String image) {
        _key = key;
        _name = name;
        _image = image;
    }

    public ImageCategory(ImageCategoryKey key) {
        _key = key;
        _name = key.name();
        _image = "";
    }

    public ImageCategoryKey getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public String getImage() {
        return _image;
    }

    public void setImage(String image) {
        _image = image;
    }

    public String getKeyResourceName(){
        return PREFIX_RESOURCE_NAME + getKey().name();
    }


    public String getKeyResourceImage(){
        return  PREFIX_RESOURCE_IMAGE + getKey().name();
    }




    @Override
    public String toString() {
        return _name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageCategory that = (ImageCategory) o;

        if (getKey() != that.getKey()) return false;
        if (!getName().equals(that.getName())) return false;
        return getImage() != null ? getImage().equals(that.getImage()) : that.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }
}
