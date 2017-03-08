package com.focus.vbox;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.focus.vbox.base.BaseActivity;

public class VboxMainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vbox_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_bottom_local_videos).setOnClickListener(this);
       findViewById(R.id.tv_bottom_resources).setOnClickListener(this);
        findViewById(R.id.tv_bottom_settings).setOnClickListener(this);
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
        }
    }
}
