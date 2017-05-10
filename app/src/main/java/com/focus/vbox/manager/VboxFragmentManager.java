package com.focus.vbox.manager;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by yxx on 2017/5/10.
 */

public class VboxFragmentManager {

    public static final String TAG_LOCAL_FRAGMENT = "tag_local_fragment";
    public static final String TAG_RECOMEND_FRAGMENT = "tag_recomend_fragment";
    public static final String TAG_SETTINGS = "tag_settings";

    private FragmentManager mFragmentManager;

    private static VboxFragmentManager mInstance;

    public static VboxFragmentManager getInstance() {
        if (mInstance == null) {
            synchronized (VboxFragmentManager.class) {
                if (mInstance == null) {
                    mInstance = new VboxFragmentManager();
                }
            }
        }
        return mInstance;
    }

    public void init(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public VboxFragmentManager() {

    }



    public void showFragment(String fragmentTag) {

    }


    public String getCurrentFragmentTag() {
        return ConfigManager.getCurrentFragmentTag();
    }






















}
