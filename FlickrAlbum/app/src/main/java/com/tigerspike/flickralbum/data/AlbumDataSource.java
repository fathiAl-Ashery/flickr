package com.tigerspike.flickralbum.data;

/**
 * Created by admin on 1/15/17.
 */

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.model.Album;

public interface AlbumDataSource {

    interface LoadAlbumCallback {

        void onAlbumLoaded(Album album);

        void onDataNotAvailable();
    }

    void getAlbum(@NonNull LoadAlbumCallback callback);

    void getAlbumWithTag(@NonNull String tag, @NonNull LoadAlbumCallback callback);

    void refreshAlbum();

}
