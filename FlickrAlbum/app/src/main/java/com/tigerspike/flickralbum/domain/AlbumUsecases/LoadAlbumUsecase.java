package com.tigerspike.flickralbum.domain.AlbumUsecases;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.AlbumDataSource;
import com.tigerspike.flickralbum.data.AlbumRepository;
import com.tigerspike.flickralbum.data.model.Album;
import com.tigerspike.flickralbum.domain.UseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/15/17.
 */

public class LoadAlbumUsecase extends UseCase<LoadAlbumUsecase.RequestValues, LoadAlbumUsecase.ResponseValue> {

    private final AlbumRepository mImagesRepository;

    public LoadAlbumUsecase(@NonNull AlbumRepository albumRepository) {
        mImagesRepository = checkNotNull(albumRepository, "AlbumRespository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        if (values.isForceUpdate()) {
            mImagesRepository.refreshAlbum();
        }

        if (values.tags != null && values.tags.length() > 0){
            mImagesRepository.getAlbumWithTag(values.tags, new AlbumDataSource.LoadAlbumCallback() {
                @Override
                public void onAlbumLoaded(Album album) {

                    ResponseValue responseValue = new ResponseValue(album);
                    getUseCaseCallback().onSuccess(responseValue);
                }

                @Override
                public void onDataNotAvailable() {
                    getUseCaseCallback().onError();
                }
            });
        } else {
            mImagesRepository.getAlbum(new AlbumDataSource.LoadAlbumCallback() {
                @Override
                public void onAlbumLoaded(Album album) {

                    ResponseValue responseValue = new ResponseValue(album);
                    getUseCaseCallback().onSuccess(responseValue);
                }

                @Override
                public void onDataNotAvailable() {
                    getUseCaseCallback().onError();
                }
            });
        }

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final boolean forceUpdate;
        private final String tags;

        public RequestValues(boolean forceUpdate, String tags) {
            this.forceUpdate = forceUpdate;
            this.tags = tags;
        }

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public String tags(){
            return tags;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Album album;

        public ResponseValue(@NonNull Album album) {
            this.album = checkNotNull(album, "images cannot be null!");
        }

        public Album getAlbum() {
            return album;
        }
    }
}
