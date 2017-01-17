package com.tigerspike.flickralbum.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 1/16/17.
 */

public class Image implements Parcelable{

    private String title;
    private String link;
    private String thumbLink;
    private String date_taken;
    private String description;
    private String author;

    public Image(){

    }


    public Image(String title, String description, String author, String date_taken, String url){
        this.title = title;
        this.description = description;
        this.author = author;
        this.date_taken = date_taken;
        this.link = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbLink() {
        return thumbLink;
    }

    public void setThumbLink(String thumbLink) {
        this.thumbLink = thumbLink;
    }
    public String getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(String date_taken) {
        this.date_taken = date_taken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.thumbLink);
        dest.writeString(this.date_taken);
        dest.writeString(this.description);
        dest.writeString(this.author);
    }

    protected Image(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.thumbLink = in.readString();
        this.date_taken = in.readString();
        this.description = in.readString();
        this.author = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
