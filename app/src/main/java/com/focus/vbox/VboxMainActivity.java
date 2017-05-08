package com.focus.vbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.focus.vbox.base.BaseActivity;
import com.focus.vbox.view.PlayActivity;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class VboxMainActivity extends BaseActivity implements View.OnClickListener {

    private StandardGSYVideoPlayer gsyVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vbox_main);
        gsyVideoPlayer = new StandardGSYVideoPlayer(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_bottom_local_videos).setOnClickListener(this);
       findViewById(R.id.tv_bottom_resources).setOnClickListener(this);
        findViewById(R.id.tv_bottom_settings).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
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
