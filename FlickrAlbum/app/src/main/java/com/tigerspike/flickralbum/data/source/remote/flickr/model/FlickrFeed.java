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
}
