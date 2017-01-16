package com.tigerspike.flickralbum.data.source.remote.flickr.networking;

import com.tigerspike.flickralbum.data.source.remote.flickr.model.Flickr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface FlickrEndPoints {

    @GET("format=json")
    Call<Flickr> getPublicAlbum();

    @GET("format=json&tag={tags}")
    Call<Flickr> getPublicAlbumWithTags(@Path("tags") String tags);

}
