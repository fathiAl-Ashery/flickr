package com.tigerspike.flickralbum.data.model;


import java.util.List;

/**
 * Created by admin on 1/16/17.
 *
 * these model classes added as to remove dependancy of flickr. In case we need to provide multiple sources for images or change the current source
 */

public class Album {
    private String albumTitle;
    private String description;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String tags;

    public Album(){

    }

    public Album(String title, String description, String tags, List<Image> images){
        this.albumTitle = title;
        this.description = description;
        this.images = images;
        this.tags = tags;
    }

    private List<Image> images;

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
