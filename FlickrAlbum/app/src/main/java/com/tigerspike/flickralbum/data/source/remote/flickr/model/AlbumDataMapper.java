package com.tigerspike.flickralbum.data.source.remote.flickr.model;

import com.tigerspike.flickralbum.domain.model.Album;
import com.tigerspike.flickralbum.domain.model.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 1/16/17.
 *
 * This class it to map from the Flickr Model to our Album Domain Models.
 * may be this class is not useful in this implementation as we are using only one data source.
 * but I added it to show the possiblilty of adding any other data source independent of the domain and presentation model
 */

public class AlbumDataMapper {

    private static AlbumDataMapper INSTANCE;

    public static AlbumDataMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AlbumDataMapper();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private AlbumDataMapper() {

    }

    /**
     * Transform a {@link FlickrFeed} into an {@link Album}.
     *
     * @param flickrFeed Object to be transformed.
     * @return {@link Album} if valid {@link FlickrFeed} otherwise null.
     */
    public Album transform(FlickrFeed flickrFeed) {
        Album album = null;
        if (flickrFeed != null) {

            album = new Album();
            album.setAlbumTitle(flickrFeed.getTitle());
            album.setDescription(flickrFeed.getDescription() + " (" + flickrFeed.getGenerator() +")");
            album.setTags(flickrFeed.getItems().get(0).getTags());
            album.setImages(transform(flickrFeed.getItems()));
        }
        return album;
    }


    /**
     * Transform a {@link FlickrItem} into an {@link Image}.
     *
     * @param flickrItem Object to be transformed.
     * @return {@link Image} if valid {@link FlickrItem} otherwise null.
     */
    private Image transform(FlickrItem flickrItem) {
        Image image = new Image();
        if (flickrItem != null) {
            image.setTitle(flickrItem.getTitle());
            image.setDescription(flickrItem.getDescription() + ", " + flickrItem.getPublished() + ".");
            image.setAuthor(flickrItem.getAuthor());
            image.setDate_taken(flickrItem.getDate_taken());
            image.setLink( flickrItem.getMedia().getUrl());
        }
        return image;
    }


    private List<Image> transform(Collection<FlickrItem> flickrItemsCollection){
        final List<Image> images = new ArrayList<>();

        for (FlickrItem userEntity : flickrItemsCollection) {
            final Image image = transform(userEntity);
            if (image != null) {
                images.add(image);
            }
        }
        return images;
    }

}
