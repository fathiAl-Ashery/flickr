package com.tigerspike.flickralbum.data;

/**
 * Created by admin on 1/15/17.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tigerspike.flickralbum.data.model.Image;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


public class ImagesRepository implements ImagesDataSource {

    private static ImagesRepository INSTANCE = null;

    private final ImagesDataSource mImagesRemoteDataSource;

    private final ImagesDataSource mImagesLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Image> mCachedImages;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private ImagesRepository(@NonNull ImagesDataSource imagesRemoteDataSource,
                             @NonNull ImagesDataSource imagesLocalDataSource) {
        mImagesRemoteDataSource = checkNotNull(imagesRemoteDataSource);
        mImagesLocalDataSource = checkNotNull(imagesLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param imagesRemoteDataSource the backend data source
     * @param imagesLocalDataSource  the device storage data source
     * @return the {@link ImagesRepository} instance
     */
    public static ImagesRepository getInstance(ImagesDataSource imagesRemoteDataSource,
                                               ImagesDataSource imagesLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ImagesRepository(imagesRemoteDataSource, imagesLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(ImagesDataSource, ImagesDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets images from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadImagesCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getImages(@NonNull final LoadImagesCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedImages != null && !mCacheIsDirty) {
            callback.onImagesLoaded(new ArrayList<>(mCachedImages.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getImagesFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mImagesLocalDataSource.getImages(new LoadImagesCallback() {
                @Override
                public void onImagesLoaded(List<Image> images) {
                    refreshCache(images);
                    callback.onImagesLoaded(new ArrayList<>(mCachedImages.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getImagesFromRemoteDataSource(callback);
                }
            });
        }
    }


    /**
     * Gets Images from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     * <p>
     * Note: {@link LoadImagesCallback#onDataNotAvailable()} is fired if both data sources fail to
     * get the data.
     */
    @Override
    public void getImage(@NonNull final String imageId, @NonNull final GetImageCallback callback) {
        checkNotNull(imageId);
        checkNotNull(callback);

        Image cachedImage = getImageWithId(imageId);

        // Respond immediately with cache if available
        if (cachedImage != null) {
            callback.onImageLoaded(cachedImage);
            return;
        }

        // Load from server/persisted if needed.

        // Is the Image in the local data source? If not, query the network.
        mImagesLocalDataSource.getImage(imageId, new GetImageCallback() {
            @Override
            public void onImageLoaded(Image image) {
                callback.onImageLoaded(image);
            }

            @Override
            public void onDataNotAvailable() {
                mImagesRemoteDataSource.getImage(imageId, new GetImageCallback() {
                    @Override
                    public void onImageLoaded(Image image) {
                        callback.onImageLoaded(image);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    private void getImagesFromRemoteDataSource(@NonNull final LoadImagesCallback callback) {
        mImagesRemoteDataSource.getImages(new LoadImagesCallback() {
            @Override
            public void onImagesLoaded(List<Image> images) {
                refreshCache(images);
                callback.onImagesLoaded(new ArrayList<>(mCachedImages.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void refreshImages() {
        mCacheIsDirty = true;
    }

    private void refreshCache(List<Image> images) {
        if (mCachedImages == null) {
            mCachedImages = new LinkedHashMap<>();
        }
        mCachedImages.clear();
        for (Image image : images) {
            mCachedImages.put(image.getId(), image);
        }
        mCacheIsDirty = false;
    }


    @Nullable
    private Image getImageWithId(@NonNull String id) {
        checkNotNull(id);
        if (mCachedImages == null || mCachedImages.isEmpty()) {
            return null;
        } else {
            return mCachedImages.get(id);
        }
    }
}
