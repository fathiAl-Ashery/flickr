package com.tigerspike.flickralbum.presentation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumRepository;
import com.tigerspike.flickralbum.data.source.remote.flickr.FlickrDataSource;
import com.tigerspike.flickralbum.domain.Images.LoadAlbumUsecase;
import com.tigerspike.flickralbum.domain.UseCaseHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/16/17.
 */

public class Injection {
    public static AlbumRepository provideAlbumRepository(@NonNull Context context) {
        checkNotNull(context);
        return AlbumRepository.getInstance(FlickrDataSource.getInstance());
    }

    public static LoadAlbumUsecase provideLoadAlbumUsecase(@NonNull Context context) {
        return new LoadAlbumUsecase(provideAlbumRepository(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

}
