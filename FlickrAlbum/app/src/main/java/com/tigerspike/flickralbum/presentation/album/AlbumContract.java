package com.tigerspike.flickralbum.presentation.album;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.model.Album;
import com.tigerspike.flickralbum.data.model.Image;
import com.tigerspike.flickralbum.presentation.BasePresenter;
import com.tigerspike.flickralbum.presentation.BaseView;

/**
 * Created by admin on 1/16/17.
 * this is the contract between the View and the presenter
 */

public interface AlbumContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showAlbum(Album album);

        void showImageDetailsUi(Image image);

        void showLoadingError();

        void showNoImages();

    }

    interface Presenter extends BasePresenter {

        void loadAlbum(boolean forceUpdate);

        void loadAlbum(String tags);

        void openImageDetails(@NonNull Image image);

    }
}
