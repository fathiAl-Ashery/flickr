package com.tigerspike.flickralbum.presentation.imagedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tigerspike.flickralbum.R;

public class AlbumImageDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "image";

    private ImageDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_image_details);

        ImageFragment fragment =
                (ImageFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            // Create the fragment
            fragment = ImageFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, fragment);
            transaction.commit();
        }

        // Create the presenter
        presenter = new ImageDetailPresenter(fragment);
    }
}
