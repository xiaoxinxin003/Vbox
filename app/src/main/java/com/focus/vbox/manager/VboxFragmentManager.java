package com.focus.vbox.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.focus.vbox.R;
import com.focus.vbox.view.fragment.LocalVideoFragment;
import com.focus.vbox.view.fragment.RecomendFragment;
import com.focus.vbox.view.fragment.SettingsFragment;

import java.util.List;

/**
 * Created by yxx on 2017/5/10.
 */

public class VboxFragmentManager {

    public static final String TAG_LOCAL_FRAGMENT = "tag_local_fragment";
    public static final String TAG_RECOMEND_FRAGMENT = "tag_recomend_fragment";
    public static final String TAG_SETTINGS = "tag_settings";

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private Fragment mLastFragment;

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

    public void init(FragmentActivity activity) {
        if (activity != null) {
            mFragmentManager = activity.getSupportFragmentManager();
        }
    }

    public VboxFragmentManager() {

    }



    public void showFragment(String tag) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        mCurrentFragment = mFragmentManager.findFragmentByTag(tag);
        if (mCurrentFragment == null) {
            mCurrentFragment = newInstanceFragmentByTag(tag);
            ft.add(R.id.content_layout, mCurrentFragment, tag);
        }

        List<Fragment> list = mFragmentManager.getFragments();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null && list.get(i) != mCurrentFragment) {
                    ft.hide(list.get(i));
                }
            }
        }

        if (mLastFragment != null) {
            ft.hide(mLastFragment);
        }
        if (mCurrentFragment.isDetached()) {
            ft.attach(mCurrentFragment);
        }
        ft.show(mCurrentFragment);
        mLastFragment = mCurrentFragment;
        ft.commitAllowingStateLoss();
    }


    public String getCurrentFragmentTag() {
        return ConfigManager.getCurrentFragmentTag();
    }

    private Fragment newInstanceFragmentByTag(String tag) {
        Fragment fragment = null;
        switch (tag) {
            case TAG_LOCAL_FRAGMENT:
                fragment = LocalVideoFragment.newInstance(null);
                break;
            case TAG_RECOMEND_FRAGMENT:
                fragment = RecomendFragment.newInstance(null);
                break;
            case TAG_SETTINGS:
                fragment = SettingsFragment.newInstance(null);
                break;
            default:
                break;
        }
        return fragment;
    }




















}
