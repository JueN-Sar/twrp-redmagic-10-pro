package cn.nubia.tgk.util;

import android.util.Log;

/* loaded from: classes2.dex */
public class TgkFeatureUtil {
    private static final String FEATURE_CLASS_NAME = "com.zte.feature.Feature";
    public static final boolean IS_SUPPORT_TGK_PORTRAIT_LANDSCAPE_ENABLE = false;
    private static final String METHOD_GET_BOOLEAN = "getBoolean";
    private static final String METHOD_GET_INT = "getInt";
    private static final String METHOD_GET_STRING = "get";
    private static final String TAG = "Tgk_FeatureUtil";
    public static final String ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY_SUPPORT_PORTRAIT = "ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY_SUPPORT_PORTRAIT";

    public static String get(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName(FEATURE_CLASS_NAME).getDeclaredMethod(METHOD_GET_STRING, String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e = e;
        }
        try {
            Log.d(TAG, str + ":" + str3);
            return str3;
        } catch (Exception e2) {
            e = e2;
            str2 = str3;
            e.printStackTrace();
            return str2;
        }
    }

    public static Boolean getBoolean(String str, boolean z) {
        try {
            z = ((Boolean) Class.forName(FEATURE_CLASS_NAME).getMethod(METHOD_GET_BOOLEAN, String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
            Log.d(TAG, str + ":" + z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.valueOf(z);
    }

    public static Integer getInt(String str, int i) {
        try {
            i = ((Integer) Class.forName(FEATURE_CLASS_NAME).getDeclaredMethod(METHOD_GET_INT, String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
            Log.d(TAG, str + ":" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.valueOf(i);
    }

    public static boolean isSprd() {
        return "sprd".equals(get("SOC_VENDOR", ""));
    }

    public static Boolean isSupportTgkPortraitLandscapeEnable() {
        return false;
    }

    public static Boolean isTgkSupportPortrait() {
        return getBoolean(ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY_SUPPORT_PORTRAIT, false);
    }
}
