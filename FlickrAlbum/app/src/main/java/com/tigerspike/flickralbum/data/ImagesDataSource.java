package com.tigerspike.flickralbum.data;

/**
 * Created by admin on 1/15/17.
 */

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.model.Image;

import java.util.List;

public interface ImagesDataSource {

    interface LoadImagesCallback {

        void onImagesLoaded(List<Image> images);

        void onDataNotAvailable();
    }

    interface GetImageCallback {

        void onImageLoaded(Image task);

        void onDataNotAvailable();
    }

    void getImages(@NonNull LoadImagesCallback callback);

    void getImage(@NonNull String imageID, @NonNull GetImageCallback callback);

    void refreshImages();

}
