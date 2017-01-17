package com.tigerspike.flickralbum.presentation.imagedetails;

import com.tigerspike.flickralbum.presentation.BasePresenter;
import com.tigerspike.flickralbum.presentation.BaseView;

/**
 * Created by admin on 1/16/17.
 * this is the contract between the View and the presenter
 */

public interface ImageDetailContract {
    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {


    }
}
