package com.focus.vbox.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.focus.vbox.base.VboxApplication;
import com.focus.vbox.bean.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 媒体库操作
 * Created by focus on 2017/5/17.
 */

public class MediaUtils {

    public static List<VideoInfo> getVideoList() {
        List<VideoInfo> sysVideoList = new ArrayList<VideoInfo>();// 视频信息集合
        // MediaStore.Video.Thumbnails.DATA:视频缩略图的文件路径
        String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID};

        // MediaStore.Video.Media.DATA：视频文件路径；
        // MediaStore.Video.Media.DISPLAY_NAME : 视频文件名，如 testVideo.mp4
        // MediaStore.Video.Media.TITLE: 视频标题 : testVideo
        String[] mediaColumns = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION
        };
        ContentResolver resolver = VboxApplication.getAppContext().getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);

        if (cursor == null) {
            return null;
        }
        if (cursor.moveToFirst()) {
            do {
                VideoInfo info = new VideoInfo();
                int id = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.Video.Media._ID));
                Cursor thumbCursor = resolver.query(
                        MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                        thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID
                                + "=" + id, null, null);
                if (thumbCursor.moveToFirst()) {
                    info.setThumbPath(thumbCursor.getString(thumbCursor
                            .getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                }
                info.setPath(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                info.setTitle(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));

                info.setDisplayName(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                info.setMimeType(cursor
                        .getString(cursor
                                .getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)));
                try{
                    info.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
                    info.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM)));
                    info.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)));
                    info.setDataToken(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)));
                    info.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DESCRIPTION)));
                    info.setIsPrivate(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_PRIVATE)));
                    info.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.LATITUDE)));
                    info.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.LONGITUDE)));
                    info.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.LANGUAGE)));
                }catch (Exception e) {
                    e.printStackTrace();
                }

                sysVideoList.add(info);
            } while (cursor.moveToNext());
        }
        return sysVideoList;
    }

}
