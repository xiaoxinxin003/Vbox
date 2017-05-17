package com.focus.vbox.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.focus.vbox.R;
import com.focus.vbox.base.VboxApplication;
import com.focus.vbox.bean.VideoInfo;
import com.focus.vbox.utils.FileUtils;
import com.focus.vbox.view.activity.PlayActivity;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yxx on 2017/5/16.
 */

public class VideoAdapter extends BaseAdapter {
    private List<VideoInfo> mList;
    private LayoutInflater inflater;
    private Context mContext;
    public VideoAdapter(List videos, Context context) {
        this.mList = videos;
        this.mContext = context;
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
    public VideoInfo getItem(int position) {
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
        final VideoInfo info = mList.get(position);
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

        holder.videoCover.setTag(info.getPath());
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
                    if (holder.videoCover.getTag() != null && holder.videoCover.getTag().equals(info.getPath())) {
                        holder.videoCover.setImageBitmap(bitmap);
                    }
                }
            });
        holder.videoName.setText(info.getDisplayName());
        holder.videoSize.setText("60MB");
        holder.videoTime.setText("2:23");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "CLICK" + info.getDisplayName());
                play(holder.videoCover, info.getPath());
            }
        });

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

    private void play(ImageView videoCover, String filePath) {
        Intent intent = new Intent(mContext, PlayActivity.class);
        intent.putExtra("file_path", filePath);
        intent.putExtra(PlayActivity.TRANSITION, true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(videoCover, PlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    (FragmentActivity)mContext, pair);
            ActivityCompat.startActivity(mContext, intent, activityOptions.toBundle());
        } else {
            mContext.startActivity(intent);
            ((FragmentActivity)mContext).overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    static class ViewHolder {
        ImageView videoCover;
        TextView videoName;
        TextView videoSize;
        TextView videoTime;
    }
}
