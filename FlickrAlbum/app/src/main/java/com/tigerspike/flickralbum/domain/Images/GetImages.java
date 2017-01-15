package com.tigerspike.flickralbum.domain.Images;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.ImagesDataSource;
import com.tigerspike.flickralbum.data.ImagesRepository;
import com.tigerspike.flickralbum.data.model.Image;
import com.tigerspike.flickralbum.domain.UseCase;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/15/17.
 */

public class GetImages extends UseCase<GetImages.RequestValues, GetImages.ResponseValue> {

    private final ImagesRepository mImagesRepository;

    public GetImages(@NonNull ImagesRepository imagesRepository) {
        mImagesRepository = checkNotNull(imagesRepository, "imagesRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        if (values.isForceUpdate()) {
            mImagesRepository.refreshImages();
        }

        mImagesRepository.getImages(new ImagesDataSource.LoadImagesCallback() {
            @Override
            public void onImagesLoaded(List<Image> images) {

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

        private final List<Image> mImages;

        public ResponseValue(@NonNull List<Image> images) {
            mImages = checkNotNull(images, "images cannot be null!");
        }

        public List<Image> getImages() {
            return mImages;
        }
    }
}
