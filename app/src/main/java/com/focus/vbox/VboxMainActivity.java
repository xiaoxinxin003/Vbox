package com.focus.vbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.focus.vbox.base.BaseActivity;
import com.focus.vbox.utils.FileUtils;
import com.focus.vbox.view.PlayActivity;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VboxMainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "my_log";
    private StandardGSYVideoPlayer gsyVideoPlayer;
    private List<File> mVideoList;
    private long startTime;
    private TextView mVideoCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vbox_main);
        gsyVideoPlayer = new StandardGSYVideoPlayer(this);
        initView();
    }

    private void initView() {
        mVideoCounts = (TextView) findViewById(R.id.tv_count);
        findViewById(R.id.tv_bottom_local_videos).setOnClickListener(this);
        findViewById(R.id.tv_bottom_resources).setOnClickListener(this);
        findViewById(R.id.tv_bottom_settings).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
        init();
    }

    private void init() {

        //创建一个上游 Observable：
        Observable.create(new ObservableOnSubscribe<List<File>>() {
            @Override
            public void subscribe(ObservableEmitter<List<File>> emitter) throws Exception {
                mVideoList = FileUtils.searchAllMedias(Environment.getExternalStorageDirectory());
                emitter.onNext(mVideoList);
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
                        mVideoCounts.setText(String.valueOf(videos.size()));
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



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_local_videos:

                break;
            case R.id.tv_bottom_resources:

                break;
            case R.id.tv_bottom_settings:

                break;
            case R.id.btn_play:
                play();
                break;
        }
    }

    private void play() {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(PlayActivity.TRANSITION, true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(this, PlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, pair);
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else {
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }
}
