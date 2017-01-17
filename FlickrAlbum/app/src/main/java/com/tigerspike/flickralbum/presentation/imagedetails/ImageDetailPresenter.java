package com.tigerspike.flickralbum.presentation.imagedetails;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by admin on 1/16/17.
 */

public class ImageDetailPresenter implements ImageDetailContract.Presenter {

    private final ImageDetailContract.View albumView;

    public ImageDetailPresenter(@NonNull ImageDetailContract.View albumView) {

        this.albumView = checkNotNull(albumView, "View cannot be null!");
        this.albumView.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
