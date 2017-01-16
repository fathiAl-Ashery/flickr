package com.tigerspike.flickralbum.data.source.remote.flickr.networking;

import com.tigerspike.flickralbum.data.source.remote.flickr.model.FlickrFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface FlickrEndPoints {

    @GET("photos_public.gne?nojsoncallback=1&format=json")
    Call<FlickrFeed> getPublicAlbum();

    @GET("photos_public.gne?nojsoncallback=1&format=json&tag={tags}")
    Call<FlickrFeed> getPublicAlbumWithTags(@Path("tags") String tags);

}
