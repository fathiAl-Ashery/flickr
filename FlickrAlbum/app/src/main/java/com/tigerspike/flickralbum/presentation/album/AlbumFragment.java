package com.tigerspike.flickralbum.presentation.album;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tigerspike.flickralbum.R;
import com.tigerspike.flickralbum.data.model.Album;
import com.tigerspike.flickralbum.data.model.Image;
import com.tigerspike.flickralbum.presentation.imagedetails.AlbumImageDetailsActivity;
import com.tigerspike.flickralbum.util.ActivityUtils;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link AlbumItemListener}
 * interface.
 */
public class AlbumFragment extends Fragment implements AlbumContract.View {

    private AlbumContract.Presenter presenter;
    private ImagesAlbumRecyclerViewAdapter albumAdapter;
    private AlbumItemListener listener = new AlbumItemListener() {
        @Override
        public void onImageClick(Image clickedImage) {
            presenter.openImageDetails(clickedImage);
        }


    };

    private ProgressDialog progress;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlbumFragment() {
    }

    public static AlbumFragment newInstance() {
        return new AlbumFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumAdapter = new ImagesAlbumRecyclerViewAdapter(new ArrayList<Image>(0), listener);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);

        int rowCount = 1;
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (metrics.densityDpi >= DisplayMetrics.DENSITY_HIGH ) {
            rowCount = 2;
        } else if (ActivityUtils.isTablet(getActivity())) {
            rowCount = 3;
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(rowCount, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(albumAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            if (progress == null) {
                progress = new ProgressDialog(getActivity());
                progress.setTitle(getString(R.string.loading));
                progress.setMessage(getString(R.string.loading_album));
            }
            progress.show();
        } else {
            if (progress != null){
                progress.hide();
            }
        }

    }

    @Override
    public void showAlbum(Album album) {
        albumAdapter.replaceData(album.getImages());
    }

    @Override
    public void showImageDetailsUi(Image image) {
        // in it's own Activity, since it makes more sense that way and it gives us the flexibility
        // to show some Intent stubbing.
        Intent intent = new Intent(getContext(), AlbumImageDetailsActivity.class);
        intent.putExtra(AlbumImageDetailsActivity.EXTRA_IMAGE, image);
        startActivity(intent);
    }

    @Override
    public void showLoadingError() {
        Snackbar.make(getView(), getString(R.string.loading_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoImages() {
        //TODO create empty view and handle it visibility according to the data availaility
    }

    @Override
    public void setPresenter(AlbumContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public interface AlbumItemListener {
        void onImageClick(Image clickedImage);
    }
}
