package com.tigerspike.flickralbum.presentation;

import android.app.Application;

/**
 * Created by admin on 1/16/17.
 */
public class TigerSpikeAlbum extends Application{
    private static TigerSpikeAlbum ourInstance = new TigerSpikeAlbum();

    public static TigerSpikeAlbum getInstance() {
        return ourInstance;
    }

    private TigerSpikeAlbum() {
    }

}
