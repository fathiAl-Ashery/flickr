package com.tigerspike.flickralbum.presentation.album;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tigerspike.flickralbum.R;
import com.tigerspike.flickralbum.presentation.Injection;
import com.tigerspike.flickralbum.util.EspressoIdlingResource;

public class AlbumActivity extends AppCompatActivity {

    private AlbumContract.Presenter albumPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        AlbumFragment fragment =
                (AlbumFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            // Create the fragment
            fragment = AlbumFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, fragment);
            transaction.commit();
        }

        // Create the presenter
        albumPresenter = new AlbumPresenter(
                Injection.provideUseCaseHandler(),
                fragment,
                Injection.provideLoadAlbumUsecase(getApplicationContext()));

    }


    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
