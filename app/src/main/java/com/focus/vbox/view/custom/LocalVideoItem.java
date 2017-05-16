package com.focus.vbox.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.focus.vbox.R;
import com.focus.vbox.bean.VideoInfo;

/**
 * 本地视频item
 * Created by yxx on 2017/5/16.
 */

public class LocalVideoItem extends RelativeLayout {

    private static final String TAG = "my_log";
    private ImageView mVideoCover;
    private TextView mVideoName;
    private TextView mVideoSize;
    private TextView mVideoTime;

    public LocalVideoItem(Context context) {
        super(context);
        init();
    }

    public LocalVideoItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LocalVideoItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        inflate(getContext(), R.layout.item_video, this);
        mVideoCover = (ImageView) findViewById(R.id.iv_video_cover);
        mVideoName = (TextView) findViewById(R.id.tv_video_name);
        mVideoSize = (TextView) findViewById(R.id.tv_video_size);
        mVideoTime = (TextView) findViewById(R.id.tv_video_time);
    }

    private void bind(VideoInfo info) {
        mVideoCover.setImageResource(R.mipmap.ic_launcher);
        mVideoName.setText(info.getName());
        mVideoSize.setText(info.getSize());
        mVideoTime.setText(info.getTime());
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        Log.d(TAG, "CLICK   CLICK");
    }
}
