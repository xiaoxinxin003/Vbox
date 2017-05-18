package com.focus.vbox.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yxx on 2017/5/16.
 */

public class VideoInfo implements Parcelable {
     private String thumbPath;
     private String path;
     private String title;
     private String displayName;
     private String mimeType;
    private long duration;
    private String artist;
    private String album;
    private String description;
    private String isPrivate;
    private String language;
    private double latitude;
    private double longitude;
    private int dataToken;

    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel in) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.thumbPath = in.readString();
            videoInfo.path = in.readString();
            videoInfo.title = in.readString();
            videoInfo.displayName = in.readString();
            videoInfo.mimeType = in.readString();
            videoInfo.duration = in.readLong();
            videoInfo.artist = in.readString();
            videoInfo.album = in.readString();
            videoInfo.description = in.readString();
            videoInfo.isPrivate = in.readString();
            videoInfo.language = in.readString();
            videoInfo.latitude = in.readDouble();
            videoInfo.longitude = in.readDouble();
            videoInfo.dataToken = in.readInt();
            return videoInfo;
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDataToken() {
        return dataToken;
    }

    public void setDataToken(int dataToken) {
        this.dataToken = dataToken;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumbPath);
        dest.writeString(path);
        dest.writeString(title);
        dest.writeString(displayName);
        dest.writeString(mimeType);
        dest.writeLong(duration);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeString(description);
        dest.writeString(isPrivate);
        dest.writeString(language);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(dataToken);
    }
}
