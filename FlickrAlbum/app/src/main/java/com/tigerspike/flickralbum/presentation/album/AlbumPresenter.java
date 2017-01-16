package com.tigerspike.flickralbum.presentation.album;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.domain.Images.LoadAlbumUsecase;
import com.tigerspike.flickralbum.domain.UseCase;
import com.tigerspike.flickralbum.domain.UseCaseHandler;
import com.tigerspike.flickralbum.domain.model.Album;
import com.tigerspike.flickralbum.domain.model.Image;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/16/17.
 */

public class AlbumPresenter implements AlbumContract.Presenter {

    private final AlbumContract.View albumView;
    private final LoadAlbumUsecase loadAlbumUsecase;

    private boolean mFirstLoad = true;

    private final UseCaseHandler mUseCaseHandler;

    public AlbumPresenter(@NonNull UseCaseHandler useCaseHandler,
                          @NonNull AlbumContract.View albumView, @NonNull LoadAlbumUsecase loadAlbum) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "usecaseHandler cannot be null");
        this.albumView = checkNotNull(albumView, "View cannot be null!");
        this.loadAlbumUsecase = checkNotNull(loadAlbum, "use case cannot be null!");

        this.albumView.setPresenter(this);
    }

    @Override
    public void loadAlbum(boolean forceUpdate) {
        loadAlbum(forceUpdate || mFirstLoad, true, null);
        mFirstLoad = false;
    }

    @Override
    public void loadAlbum(String tags) {
        //load for tags, will enfore the cached to be updated
        loadAlbum(true, true, tags);
        mFirstLoad = false;
    }


    private void loadAlbum(boolean forceUpdate, final boolean showLoadingUI, String tags) {
        if (showLoadingUI) {
            this.albumView.setLoadingIndicator(true);
        }

        LoadAlbumUsecase.RequestValues requestValue = new LoadAlbumUsecase.RequestValues(forceUpdate, tags);

        mUseCaseHandler.execute(loadAlbumUsecase, requestValue,
                new UseCase.UseCaseCallback<LoadAlbumUsecase.ResponseValue>() {
                    @Override
                    public void onSuccess(LoadAlbumUsecase.ResponseValue response) {
                        Album album = response.getAlbum();

                        if (showLoadingUI) {
                            albumView.setLoadingIndicator(false);
                        }

                        if (album.getImages() == null || album.getImages().size() == 0){
                            albumView.showNoImages();
                        } else {
                            albumView.showAlbum(album);
                        }
                    }

                    @Override
                    public void onError() {

                        albumView.showLoadingError();
                    }
                });
    }

    @Override
    public void openImageDetails(@NonNull Image image) {
        checkNotNull(image, "requestedTask cannot be null!");
        albumView.showImageDetailsUi(image);
    }

    @Override
    public void start() {
        loadAlbum(false);
    }
}
