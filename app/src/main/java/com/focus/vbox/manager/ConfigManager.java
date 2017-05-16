package com.focus.vbox.manager;

import com.focus.vbox.common.ConfigDefine;
import com.focus.vbox.common.ConfigWraper;

/**
 * Created by yxx on 2017/5/10.
 */

public class ConfigManager {


    private static final boolean DEFAULT_FALSE_VALUE = false;
    private static final boolean DEFAULT_TRUE_VALUE = true;
    private static ConfigManager mInstance;

    public ConfigManager() {
        ConfigWraper.init();
    }

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
        ConfigWraper.put(ConfigDefine.IS_AUTO_SCAN, autoScan);
        ConfigWraper.commit();
    }

    public boolean getAutoScan() {
        return ConfigWraper.get(ConfigDefine.IS_AUTO_SCAN, DEFAULT_FALSE_VALUE);
    }


    public void init() {

    }
}
