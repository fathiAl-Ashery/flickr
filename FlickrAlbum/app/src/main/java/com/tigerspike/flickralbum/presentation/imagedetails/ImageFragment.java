package com.tigerspike.flickralbum.presentation.imagedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tigerspike.flickralbum.R;
import com.tigerspike.flickralbum.data.model.Image;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment implements ImageDetailContract.View{

    ImageDetailContract.Presenter presenter;
    private Image imageItem;

    private ImageView image;
    private TextView title;
    private WebView description;
    private TextView author;
    private TextView date;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance() {
        return new ImageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageItem = getActivity().getIntent().getExtras().getParcelable(AlbumImageDetailsActivity.EXTRA_IMAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_image, container, false);

        image = (ImageView) root.findViewById(R.id.photo_image);

        author = (TextView) root.findViewById(R.id.photo_author);

        title = (TextView) root.findViewById(R.id.photo_title);
        description = (WebView) root.findViewById(R.id.photo_description);

        Picasso.with(getActivity()).load(imageItem.getThumbLink()).placeholder(R.drawable.placeholder).into(image);
        title.setText(imageItem.getTitle());
        description.loadData(imageItem.getDescription(), null, null);

        author.setText(imageItem.getAuthor());

        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(ImageDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
