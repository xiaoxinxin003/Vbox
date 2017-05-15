package com.focus.vbox.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focus.vbox.R;
import com.focus.vbox.base.BaseFragment;

/**
 * Created by focus on 2017/5/10.
 */

public class RecomendFragment extends BaseFragment {

    public static RecomendFragment newInstance(Bundle args) {
        RecomendFragment fragment = new RecomendFragment();
        if (args == null) {
            args = new Bundle();
        }
        fragment.setArguments(args);
        return fragment;
    }

    public RecomendFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_net_videos, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {

    }

}
