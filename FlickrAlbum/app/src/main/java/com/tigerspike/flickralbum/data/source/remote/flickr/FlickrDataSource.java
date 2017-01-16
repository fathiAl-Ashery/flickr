
package com.tigerspike.flickralbum.data.source.remote.flickr;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumDataSource;
import com.tigerspike.flickralbum.data.source.remote.flickr.model.AlbumDataMapper;
import com.tigerspike.flickralbum.data.source.remote.flickr.model.FlickrFeed;
import com.tigerspike.flickralbum.data.source.remote.flickr.networking.FlickrEndPoints;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private FlickrDataSource() {

    }

    @Override
    public void getAlbum(final @NonNull LoadAlbumCallback callback) {
        // Call the Networkings' classes to retrieve the Images from the remote service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/feeds/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlickrEndPoints service = retrofit.create(FlickrEndPoints.class);
        try {
            Response<FlickrFeed> flickrAlbum = service.getPublicAlbum().execute();
            callback.onAlbumLoaded(AlbumDataMapper.getInstance().transform(flickrAlbum.body()));
        } catch (IOException e) {
            e.printStackTrace();
            callback.onDataNotAvailable();
        }

    }

    @Override
    public void getAlbumWithTag(@NonNull String tags, final @NonNull LoadAlbumCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/feeds/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlickrEndPoints service = retrofit.create(FlickrEndPoints.class);
        try {
            Response<FlickrFeed> flickrAlbum = service.getPublicAlbumWithTags(tags).execute();
            callback.onAlbumLoaded(AlbumDataMapper.getInstance().transform(flickrAlbum.body()));
        } catch (IOException e) {
            e.printStackTrace();
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void refreshAlbum() {
        //remove in-memory cache if any
    }
}
