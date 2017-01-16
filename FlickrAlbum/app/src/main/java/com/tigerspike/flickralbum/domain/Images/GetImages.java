package com.tigerspike.flickralbum.domain.Images;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumDataSource;
import com.tigerspike.flickralbum.data.AlbumRepository;
import com.tigerspike.flickralbum.domain.UseCase;
import com.tigerspike.flickralbum.domain.model.Album;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/15/17.
 */

public class GetImages extends UseCase<GetImages.RequestValues, GetImages.ResponseValue> {

    private final AlbumRepository mImagesRepository;

    public GetImages(@NonNull AlbumRepository imagesRepository) {
        mImagesRepository = checkNotNull(imagesRepository, "imagesRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        if (values.isForceUpdate()) {
            mImagesRepository.refreshAlbum();
        }

        mImagesRepository.getAlbum(new AlbumDataSource.LoadAlbumCallback() {
            @Override
            public void onAlbumLoaded(Album images) {

                ResponseValue responseValue = new ResponseValue(images);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final boolean mForceUpdate;

        public RequestValues(boolean forceUpdate) {
            mForceUpdate = forceUpdate;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Album mImages;

        public ResponseValue(@NonNull Album album) {
            mImages = checkNotNull(album, "images cannot be null!");
        }

        public Album getImages() {
            return mImages;
        }
    }
}
