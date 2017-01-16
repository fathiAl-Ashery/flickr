package com.tigerspike.flickralbum.presentation;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

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


    public static boolean isTablet(Context ctx){
        return (ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
