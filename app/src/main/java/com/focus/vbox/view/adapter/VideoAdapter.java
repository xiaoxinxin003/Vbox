package com.focus.vbox.view.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.focus.vbox.R;
import com.focus.vbox.utils.FileUtils;

import java.io.File;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by yxx on 2017/5/16.
 */

public class VideoAdapter implements ListAdapter {
    private List<File> mList;
    private LayoutInflater inflater;
    public VideoAdapter(List videos, Context context) {
        this.mList = videos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public File getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final File info = mList.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_video, null);
            holder.videoCover = (ImageView) convertView.findViewById(R.id.iv_video_cover);
            holder.videoName = (TextView) convertView.findViewById(R.id.tv_video_name);
            holder.videoSize = (TextView)convertView.findViewById(R.id.tv_video_size);
            holder.videoTime = (TextView)convertView.findViewById(R.id.tv_video_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        Glide.with(VboxApplication.getAppContext())
//                .load(FileUtils.getVideoThumbnail(info.getPath()))
//                .into(holder.videoCover);
//        holder.videoCover.setImageBitmap(FileUtils.getVideoThumbnail(info.getPath()));
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap bitmap = FileUtils.getVideoThumbnail(info.getPath());
                if (bitmap != null) {
                    e.onNext(bitmap);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Consumer<Bitmap>() {
                @Override
                public void accept(Bitmap bitmap) throws Exception {
                    holder.videoCover.setImageBitmap(bitmap);
                }
            })
        ;
        holder.videoName.setText(info.getName());
        holder.videoSize.setText("60MB");
        holder.videoTime.setText("2:23");
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    static class ViewHolder {
        ImageView videoCover;
        TextView videoName;
        TextView videoSize;
        TextView videoTime;
    }
}
