package com.tigerspike.flickralbum.presentation.album;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tigerspike.flickralbum.R;
import com.tigerspike.flickralbum.domain.model.Image;

import java.util.List;

public class ImagesAlbumRecyclerViewAdapter extends RecyclerView.Adapter<ImagesAlbumRecyclerViewAdapter.ViewHolder> {

    private List<Image> items;
    private AlbumFragment.AlbumItemListener mListener;
    private Context context;

    public ImagesAlbumRecyclerViewAdapter(List<Image> items, AlbumFragment.AlbumItemListener listener) {
        this.items = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = items.get(position);
        holder.titleView.setText(items.get(position).getAuthor());
        Picasso.with(context).load(items.get(position).getLink()).placeholder(R.drawable.placeholder).into( holder.imageView );

        holder.view.setOnClickListener(new View.OnClickListener() {
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
        public final View view;
        public final TextView titleView;
        public final ImageView imageView;
        public Image mItem;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            titleView = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
