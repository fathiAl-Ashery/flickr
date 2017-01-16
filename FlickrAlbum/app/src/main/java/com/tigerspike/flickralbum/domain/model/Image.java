package com.tigerspike.flickralbum.domain.model;

/**
 * Created by admin on 1/16/17.
 */

public class Image {

    private String title;
    private String link;
    private String date_taken;
    private String description;
    private String author;

    public Image(String title, String description, String author, String date_taken, String url){
        this.title = title;
        this.description = description;
        this.author = author;
        this.date_taken = date_taken;
        this.link = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(String date_taken) {
        this.date_taken = date_taken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
