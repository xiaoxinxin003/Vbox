package com.focus.vbox.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focus.vbox.R;
import com.focus.vbox.base.BaseFragment;

/**
 * Created by yxx on 2017/3/7.
 */

public class SettingsFragment extends BaseFragment {

    public static SettingsFragment newInstance(Bundle args) {
        SettingsFragment fragment = new SettingsFragment();
        if (args == null) {
            args = new Bundle();
        }
        fragment.setArguments(args);
        return fragment;
    }


    public SettingsFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {

    }




}
