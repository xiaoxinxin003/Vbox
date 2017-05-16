package com.focus.vbox.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.focus.vbox.base.VboxApplication;

/**
 * Created by yxx on 2017/5/10.
 */

public class ConfigWraper {

    private static final String PREF_NAME = "config";

    private static SharedPreferences pref;

    private static SharedPreferences.Editor editor;

    public static void init() {
//        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // 跨进程实时读取~
        pref = VboxApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
        editor = pref.edit();
    }

    public static boolean get(String isAutoScan) {
        return false;
    }

    public synchronized static boolean contains(String key) {
        return pref.contains(key);
    }

    public synchronized static boolean get(String key, boolean defValue) {
        return pref.getBoolean(key, defValue);
    }

    public synchronized static float get(String key, float defValue) {
        return pref.getFloat(key, defValue);
    }

    public synchronized static int get(String key, int defValue) {
        return pref.getInt(key, defValue);
    }

    public synchronized static long get(String key, long defValue) {
        return pref.getLong(key, defValue);
    }

    public synchronized static String get(String key, String defValue) {
        return pref.getString(key, defValue);
    }

    public synchronized static void put(String key, boolean value) {
        editor.putBoolean(key, value);
    }

    public synchronized static void put(String key, float value) {
        editor.putFloat(key, value);
    }

    public synchronized static void put(String key, int value) {
        editor.putInt(key, value);
    }

    public synchronized static void put(String key, long value) {
        editor.putLong(key, value);
    }

    public synchronized static void put(String key, String value) {
        editor.putString(key, value);
    }

    public synchronized static boolean commit() {
        return editor.commit();
    }

    public synchronized static void apply() {
        editor.apply();
    }
    public synchronized static void clear() {
        editor.clear();
    }

    public synchronized static void remove(String key) {
        editor.remove(key);
    }






























}
