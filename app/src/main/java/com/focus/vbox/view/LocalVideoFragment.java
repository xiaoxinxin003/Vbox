package com.focus.vbox.view;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.focus.vbox.R;
import com.focus.vbox.base.BaseFragment;
import com.focus.vbox.manager.ConfigManager;
import com.focus.vbox.utils.FileUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 本地视频列表
 * Created by yxx on 2017/3/7.
 */

public class LocalVideoFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "my_log";
    private ListView mVideoList;
    private List<File> mVideoData;
    private ProgressBar mLoadingPb;
    private ImageButton mScanBtn;
    private TextView mCurrentScanFile;

    public static LocalVideoFragment newInstance(Bundle args) {
        LocalVideoFragment fragment = new LocalVideoFragment();
        if (args == null) {
            args = new Bundle();
        }
        fragment.setArguments(args);
        return fragment;
    }

    public LocalVideoFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_local_videos, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mVideoList = (ListView) rootView.findViewById(R.id.lv_local_videos);
        mLoadingPb = (ProgressBar) rootView.findViewById(R.id.pb_loading_local);
        mScanBtn = (ImageButton) rootView.findViewById(R.id.ib_begin_scan);
        mCurrentScanFile = (TextView)rootView.findViewById(R.id.tv_current_scan_file);
        if (!ConfigManager.getInstance().getAutoScan()) {
            mScanBtn.setVisibility(View.VISIBLE);
        }
        initListener();
        initData();
    }

    private void initListener() {
        mScanBtn.setOnClickListener(this);
    }

    private void initData() {
        if (ConfigManager.getInstance().getAutoScan()) {
            scan();
        }
    }

    private void scan() {
        //创建一个上游 Observable：
        Observable.create(new ObservableOnSubscribe<List<File>>() {
            @Override
            public void subscribe(ObservableEmitter<List<File>> emitter) throws Exception {
                mVideoData = FileUtils.searchAllMedias(Environment.getExternalStorageDirectory(), mCurrentScanFile);
                emitter.onNext(mVideoData);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Observer<List<File>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "subscribe");
                            }

                            @Override
                            public void onNext(List videos) {
                                Log.d(TAG, "videos size is : " + videos.size());
                                setScanSuc(videos);
                                Log.d("my_log", "current thread is :" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void setScanSuc(List videos) {
        if (videos.size() <= 0) {
            Toast.makeText(getContext(), "no video low b!", Toast.LENGTH_SHORT).show();
            mScanBtn.setVisibility(View.VISIBLE);
            mLoadingPb.setVisibility(View.GONE);
            mCurrentScanFile.setVisibility(View.GONE);
            return;
        }
        mScanBtn.setVisibility(View.GONE);
        mLoadingPb.setVisibility(View.GONE);
        mVideoList.setVisibility(View.VISIBLE);
//        mVideoList.setAdapter();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ib_begin_scan:
                scan();
                mScanBtn.setVisibility(View.GONE);
                mLoadingPb.setVisibility(View.VISIBLE);
                break;
        }
    }
}
