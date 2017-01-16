package com.tigerspike.flickralbum.data.source.remote.flickr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 1/16/17.
 */

public class FlickrFeed {
    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private List<FlickrItem> items = new ArrayList<FlickrItem>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<FlickrItem> getItems() {
        return items;
    }

    public void setItems(List<FlickrItem> items) {
        this.items = items;
    }
}
