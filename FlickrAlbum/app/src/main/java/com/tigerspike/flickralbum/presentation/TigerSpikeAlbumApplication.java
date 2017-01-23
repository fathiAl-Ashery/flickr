package com.tigerspike.flickralbum.presentation;

import android.app.Application;

/**
 * Created by admin on 1/16/17.
 */
public class TigerSpikeAlbumApplication extends Application{
    private static TigerSpikeAlbumApplication ourInstance = new TigerSpikeAlbumApplication();

    public static TigerSpikeAlbumApplication getInstance() {
        return ourInstance;
    }

    private TigerSpikeAlbumApplication() {
    }
}
