package com.focus.vbox.manager;

import com.focus.vbox.common.ConfigDefine;
import com.focus.vbox.common.ConfigWraper;

/**
 * Created by yxx on 2017/5/10.
 */

public class ConfigManager {


    private static ConfigManager mInstance;

    public static ConfigManager getInstance() {
        if (mInstance == null) {
            synchronized (ConfigManager.class) {
                if (mInstance == null) {
                    mInstance = new ConfigManager();
                }
            }
        }
        return mInstance;
    }

    public static String getCurrentFragmentTag() {

        return null;
    }




    public void setAutoScan(boolean autoScan) {

    }

    public boolean getAutoScan() {
        return ConfigWraper.get(ConfigDefine.IS_AUTO_SCAN);
    }







}
