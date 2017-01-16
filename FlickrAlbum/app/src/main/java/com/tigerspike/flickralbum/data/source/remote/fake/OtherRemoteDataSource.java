
package com.tigerspike.flickralbum.data.source.remote.fake;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumDataSource;

/**
 * Implementation of the data source For any other Data source in case we need to provide multiple sources
 */
public class OtherRemoteDataSource implements AlbumDataSource {

    private static OtherRemoteDataSource INSTANCE;



    public static OtherRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OtherRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private OtherRemoteDataSource() {}

    @Override
    public void getAlbum(final @NonNull AlbumDataSource.LoadAlbumCallback callback) {
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
