package com.tigerspike.flickralbum.presentation.imagedetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tigerspike.flickralbum.R;

public class AlbumImageDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "image";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_image_details);
    }
}
