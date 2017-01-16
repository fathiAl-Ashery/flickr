
package com.tigerspike.flickralbum.data.source.local;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumDataSource;

/**
 * Implementation of the data source that adds a latency simulating network.
 */
public class CachedDataSource implements AlbumDataSource {

    private static CachedDataSource INSTANCE;



    public static CachedDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CachedDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private CachedDataSource() {}

    @Override
    public void getAlbum(final @NonNull LoadAlbumCallback callback) {
        // Call the Networkings' classes to retrieve the Images from the remote service
    }

    @Override
    public void getAlbumWithTag(@NonNull String tag, final @NonNull LoadAlbumCallback callback) {

    }


    @Override
    public void refreshAlbum() {
        // Not required because the {@link AlbumRepository} handles the logic of refreshing the
        // images from all the available data sources.
    }


}
