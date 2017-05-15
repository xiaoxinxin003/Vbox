package com.focus.vbox.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focus.vbox.R;
import com.focus.vbox.base.BaseFragment;

/**
 * Created by yxx on 2017/3/7.
 */

public class LocalVideoFragment extends BaseFragment {

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

    }




}
