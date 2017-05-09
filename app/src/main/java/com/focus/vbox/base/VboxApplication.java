package com.focus.vbox.base;

import android.app.Application;
import android.content.ContextWrapper;

import java.security.Permission;

/**
 * Created by focus on 17-3-1.
 */

public class VboxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO 检查读取文件系统权限

    }
}
