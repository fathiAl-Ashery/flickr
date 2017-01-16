
package com.tigerspike.flickralbum.data.source.remote.flickr;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumDataSource;

/**
 * Implementation of the data source For Flickr public Fields
 */
public class FlickrDataSource implements AlbumDataSource {

    private static FlickrDataSource INSTANCE;



    public static FlickrDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FlickrDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private FlickrDataSource() {}

    @Override
    public void getAlbum(final @NonNull LoadAlbumCallback callback) {
        // Call the Networkings' classes to retrieve the Images from the remote service
    }

    //TODO:: Add another method to retrieve images with tag

    @Override
    public void getAlbumWithTag(@NonNull String tag, final @NonNull LoadAlbumCallback callback) {

    }


    @Override
    public void refreshAlbum() {
        // Not required because the {@link AlbumRepository} handles the logic of refreshing the
        // images from all the available data sources.
    }


}
