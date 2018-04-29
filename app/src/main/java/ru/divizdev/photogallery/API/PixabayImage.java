package ru.divizdev.photogallery.API;

import com.google.gson.annotations.SerializedName;

public class PixabayImage {
    @SerializedName("webformatHeight")
    private int webformatHeight;

    @SerializedName("imageWidth")
    private int imageWidth;

    @SerializedName("favorites")
    private int favorites;

    @SerializedName("webformatURL")
    private String webformatURL;

    @SerializedName("previewHeight")
    private int previewHeight;

    @SerializedName("userImageURL")
    private String userImageURL;

    @SerializedName("comments")
    private int comments;

    @SerializedName("previewURL")
    private String previewURL;

    @SerializedName("type")
    private String type;

    @SerializedName("imageHeight")
    private int imageHeight;

    @SerializedName("tags")
    private String tags;

    @SerializedName("previewWidth")
    private int previewWidth;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("downloads")
    private int downloads;

    @SerializedName("largeImageURL")
    private String largeImageURL;

    @SerializedName("pageURL")
    private String pageURL;

    @SerializedName("id")
    private int id;

    @SerializedName("imageSize")
    private int imageSize;

    @SerializedName("webformatWidth")
    private int webformatWidth;

    @SerializedName("user")
    private String user;

    @SerializedName("views")
    private int views;

    @SerializedName("likes")
    private int likes;

    public void setWebformatHeight(int webformatHeight){
        this.webformatHeight = webformatHeight;
    }

    public int getWebformatHeight(){
        return webformatHeight;
    }

    public void setImageWidth(int imageWidth){
        this.imageWidth = imageWidth;
    }

    public int getImageWidth(){
        return imageWidth;
    }

    public void setFavorites(int favorites){
        this.favorites = favorites;
    }

    public int getFavorites(){
        return favorites;
    }

    public void setWebformatURL(String webformatURL){
        this.webformatURL = webformatURL;
    }

    public String getWebformatURL(){
        return webformatURL;
    }

    public void setPreviewHeight(int previewHeight){
        this.previewHeight = previewHeight;
    }

    public int getPreviewHeight(){
        return previewHeight;
    }

    public void setUserImageURL(String userImageURL){
        this.userImageURL = userImageURL;
    }

    public String getUserImageURL(){
        return userImageURL;
    }

    public void setComments(int comments){
        this.comments = comments;
    }

    public int getComments(){
        return comments;
    }

    public void setPreviewURL(String previewURL){
        this.previewURL = previewURL;
    }

    public String getPreviewURL(){
        return previewURL;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setImageHeight(int imageHeight){
        this.imageHeight = imageHeight;
    }

    public int getImageHeight(){
        return imageHeight;
    }

    public void setTags(String tags){
        this.tags = tags;
    }

    public String getTags(){
        return tags;
    }

    public void setPreviewWidth(int previewWidth){
        this.previewWidth = previewWidth;
    }

    public int getPreviewWidth(){
        return previewWidth;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public void setDownloads(int downloads){
        this.downloads = downloads;
    }

    public int getDownloads(){
        return downloads;
    }

    public void setLargeImageURL(String largeImageURL){
        this.largeImageURL = largeImageURL;
    }

    public String getLargeImageURL(){
        return largeImageURL;
    }

    public void setPageURL(String pageURL){
        this.pageURL = pageURL;
    }

    public String getPageURL(){
        return pageURL;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setImageSize(int imageSize){
        this.imageSize = imageSize;
    }

    public int getImageSize(){
        return imageSize;
    }

    public void setWebformatWidth(int webformatWidth){
        this.webformatWidth = webformatWidth;
    }

    public int getWebformatWidth(){
        return webformatWidth;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return user;
    }

    public void setViews(int views){
        this.views = views;
    }

    public int getViews(){
        return views;
    }

    public void setLikes(int likes){
        this.likes = likes;
    }

    public int getLikes(){
        return likes;
    }

    @Override
    public String toString(){
        return
                "HitsItem{" +
                        "webformatHeight = '" + webformatHeight + '\'' +
                        ",imageWidth = '" + imageWidth + '\'' +
                        ",favorites = '" + favorites + '\'' +
                        ",webformatURL = '" + webformatURL + '\'' +
                        ",previewHeight = '" + previewHeight + '\'' +
                        ",userImageURL = '" + userImageURL + '\'' +
                        ",comments = '" + comments + '\'' +
                        ",previewURL = '" + previewURL + '\'' +
                        ",type = '" + type + '\'' +
                        ",imageHeight = '" + imageHeight + '\'' +
                        ",tags = '" + tags + '\'' +
                        ",previewWidth = '" + previewWidth + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",downloads = '" + downloads + '\'' +
                        ",largeImageURL = '" + largeImageURL + '\'' +
                        ",pageURL = '" + pageURL + '\'' +
                        ",id = '" + id + '\'' +
                        ",imageSize = '" + imageSize + '\'' +
                        ",webformatWidth = '" + webformatWidth + '\'' +
                        ",user = '" + user + '\'' +
                        ",views = '" + views + '\'' +
                        ",likes = '" + likes + '\'' +
                        "}";
    }
}
