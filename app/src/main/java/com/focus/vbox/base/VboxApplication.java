package com.focus.vbox.base;

import android.app.Application;
import android.content.Context;

import com.focus.vbox.manager.ConfigManager;

/**
 * Created by focus on 17-3-1.
 */

public class VboxApplication extends Application {

    private static VboxApplication sInstance;
    public static VboxApplication getInstance() {
        return sInstance;
    }
    public static final Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //TODO 检查读取文件系统权限

        initApp();

    }

    private void initApp() {
        ConfigManager.getInstance().init();
    }

}
