package com.tigerspike.flickralbum.data.model;

/**
 * Created by admin on 1/15/17.
 */

public class Image {
    private String id;
    private String title;

    public Image(String title){
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
