package com.focus.vbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.view.View;

import com.focus.vbox.base.BaseActivity;
import com.focus.vbox.common.CommonTitleBar;
import com.focus.vbox.manager.VboxFragmentManager;
import com.focus.vbox.view.activity.PlayActivity;

public class VboxMainActivity extends BaseActivity {

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
