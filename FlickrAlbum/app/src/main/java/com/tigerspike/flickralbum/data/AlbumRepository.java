package com.tigerspike.flickralbum.data;

/**
 * Created by admin on 1/15/17.
 */

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.model.Album;

import static com.google.common.base.Preconditions.checkNotNull;


public class AlbumRepository implements AlbumDataSource {

    private static AlbumRepository INSTANCE = null;

    private final AlbumDataSource remoteAlbumDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Album cachedAlbum = null;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean invalidCache = false;

    // Prevent direct instantiation.
    private AlbumRepository(@NonNull AlbumDataSource albumRemoteDataSource) {
        remoteAlbumDataSource = checkNotNull(albumRemoteDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param remoteAlbumDataSource the Remote data source for the Album Data
     * @return the {@link AlbumRepository} instance
     */
    public static AlbumRepository getInstance(AlbumDataSource remoteAlbumDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new AlbumRepository(remoteAlbumDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(AlbumDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets images from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadAlbumCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getAlbum(@NonNull final LoadAlbumCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (cachedAlbum != null && !invalidCache) {
            callback.onAlbumLoaded(cachedAlbum);
            return;
        }

        remoteAlbumDataSource.getAlbum(new LoadAlbumCallback() {
            @Override
            public void onAlbumLoaded(Album album) {
                refreshCache(album);
                callback.onAlbumLoaded(cachedAlbum);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }


    /**
     * Gets Images from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     * <p>
     * Note: {@link LoadAlbumCallback#onDataNotAvailable()} is fired if both data sources fail to
     * get the data.
     */
    @Override
    public void getAlbumWithTag(@NonNull final String tag, @NonNull final LoadAlbumCallback callback) {
        checkNotNull(tag);
        checkNotNull(callback);


        // Load from server if needed.
        remoteAlbumDataSource.getAlbumWithTag(tag, new LoadAlbumCallback() {
            @Override
            public void onAlbumLoaded(Album image) {
                callback.onAlbumLoaded(image);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void refreshAlbum() {
        invalidCache = true;
    }

    private void refreshCache(Album album) {
        cachedAlbum = album;
        invalidCache = false;
    }


}
