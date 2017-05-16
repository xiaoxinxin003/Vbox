package com.focus.vbox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.focus.vbox.common.CommonTitleBar;
import com.focus.vbox.manager.ConfigManager;
import com.focus.vbox.manager.VboxFragmentManager;
import com.focus.vbox.utils.FileUtils;
import com.focus.vbox.view.PlayActivity;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VboxMainActivity extends FragmentActivity {

    private static final String TAG = "my_log";
    private VboxFragmentManager mVboxFragmentManager;
    private CommonTitleBar mCommonTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vbox_main);
        mVboxFragmentManager = VboxFragmentManager.getInstance();
        mVboxFragmentManager.init(this);
        initView();
    }

    private void initView() {
        mVboxFragmentManager.showFragment(VboxFragmentManager.TAG_LOCAL_FRAGMENT);
        initCustomBottomBar();
        initListener();
        initData();
        initTitleBar();
    }

    private void initCustomBottomBar() {

    }

    private void initTitleBar() {
        mCommonTitleBar = (CommonTitleBar) findViewById(R.id.title_bar);
        mCommonTitleBar.findViewById(R.id.common_img_back).setVisibility(View.GONE);
    }

    private void initListener() {
    }

    private void initData() {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
