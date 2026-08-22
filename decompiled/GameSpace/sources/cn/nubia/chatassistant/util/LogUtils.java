package cn.nubia.chatassistant.util;

import android.util.Log;

/* loaded from: classes.dex */
public class LogUtils {
    private static final String TAG = "ChatAssistant";
    private static final String TAG_PANEL = "PowerPanel";

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void d(String str, String str2) {
        Log.d(TAG, str + " : " + str2);
    }

    public static void debugPowerPanel(String str) {
        Log.d(TAG_PANEL, str);
    }

    public static void debugPowerPanel(String str, String str2) {
        Log.d(TAG_PANEL, str + " : " + str2);
    }

    public static void e(String str) {
        Log.e(TAG, str);
    }

    public static void e(String str, String str2) {
        Log.e(TAG, str + " : " + str2);
    }

    public static void errorPowerPanel(String str) {
        Log.e(TAG_PANEL, str);
    }

    public static void errorPowerPanel(String str, String str2) {
        Log.e(TAG_PANEL, str + " : " + str2);
    }

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void i(String str, String str2) {
        Log.i(TAG, str + " : " + str2);
    }

    public static void infoPowerPanel(String str) {
        Log.i(TAG_PANEL, str);
    }

    public static void infoPowerPanel(String str, String str2) {
        Log.i(TAG_PANEL, str + " : " + str2);
    }
}
