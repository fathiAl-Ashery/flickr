package com.tigerspike.flickralbum.presentation.album;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tigerspike.flickralbum.R;
import com.tigerspike.flickralbum.domain.model.Image;
import com.tigerspike.flickralbum.presentation.album.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ImagesAlbumRecyclerViewAdapter extends RecyclerView.Adapter<ImagesAlbumRecyclerViewAdapter.ViewHolder> {

    private List<Image> items;
    private AlbumFragment.AlbumItemListener mListener;

    public ImagesAlbumRecyclerViewAdapter(List<Image> items, AlbumFragment.AlbumItemListener listener) {
        this.items = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = items.get(position);
        holder.mIdView.setText(items.get(position).getTitle());
        holder.mContentView.setText(items.get(position).getLink());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.inImageClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void replaceData(List<Image> images) {
        this.items = images;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Image mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
