package com.focus.vbox.view.custom;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focus.vbox.R;
import com.focus.vbox.manager.VboxFragmentManager;

/**
 * 自定义底部工具栏
 * Created by focus on 2017/5/15.
 */

public class CustomBottomView extends LinearLayout implements View.OnClickListener {

    private TextView mBtnLocalVideo;
    private TextView mBtnRecomendVideo;
    private TextView mBtnSettings;
    private VboxFragmentManager mVboxFragmentManager;

    private FragmentActivity mActivity;

    /**
     * @param of type null
     * @return mActivity of type FragmentActivity
     * getter function for mActivity
     * @since May 3, 2013
     * @author rajeshcp
     */
    public FragmentActivity getmActivity() {
        return mActivity;
    }

    /**
     * @param mActivity of type FragmentActivity
     * @return of type null
     * setter function for mActivity
     * @since May 3, 2013
     * @author rajeshcp
     */
    public void setmActivity(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    public CustomBottomView(Context context) {
        super(context);
        init();
    }
    public CustomBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mVboxFragmentManager = VboxFragmentManager.getInstance();
        Context context = getContext();
        inflate(context, R.layout.view_bottom_bar, this);
        mBtnLocalVideo = (TextView) findViewById(R.id.tv_bottom_local_videos);
        mBtnRecomendVideo = (TextView) findViewById(R.id.tv_bottom_resources);
        mBtnSettings = (TextView) findViewById(R.id.tv_bottom_settings);
        mBtnLocalVideo.setTextColor(Color.RED);
        initListener();
    }

    private void initListener() {
        mBtnLocalVideo.setOnClickListener(this);
        mBtnRecomendVideo.setOnClickListener(this);
        mBtnSettings.setOnClickListener(this);
    }

    private void setBtnSelected(TextView textView) {
        mBtnLocalVideo.setTextColor(getResources().getColor(R.color.black54));
        mBtnRecomendVideo.setTextColor(getResources().getColor(R.color.black54));
        mBtnSettings.setTextColor(getResources().getColor(R.color.black54));
        textView.setTextColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_local_videos:
                setBtnSelected(mBtnLocalVideo);
                mVboxFragmentManager.showFragment(VboxFragmentManager.TAG_LOCAL_FRAGMENT);
                break;
            case R.id.tv_bottom_resources:
                setBtnSelected(mBtnRecomendVideo);
                mVboxFragmentManager.showFragment(VboxFragmentManager.TAG_RECOMEND_FRAGMENT);
                break;
            case R.id.tv_bottom_settings:
                setBtnSelected(mBtnSettings);
                mVboxFragmentManager.showFragment(VboxFragmentManager.TAG_SETTINGS);
                break;
        }
    }
}
