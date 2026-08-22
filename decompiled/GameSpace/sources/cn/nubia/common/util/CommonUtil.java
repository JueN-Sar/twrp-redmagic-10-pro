package cn.nubia.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import cn.nubia.common.BuildConfig;
import cn.nubia.common.CommonApplication;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.tgk.TgkHelper;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class CommonUtil {
    private static final String DOCUMENT_AUTHORITY = "com.android.providers.media.documents";
    private static String DOCUMENT_URI = "content://com.android.providers.media.documents/document/";
    private static final int HIGH_HML_VERSION = 0;
    private static final String SETTING_HANDHELD = "is_handheld";
    public static final String TAG = "CommonUtil";

    public static String getChannel() {
        return BuildConfig.FLAVOR;
    }

    public static Context getContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    public static Rect getDisplayRect(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(((Activity) context).getWindowManager().getDefaultDisplay(), displayMetrics);
        } catch (Exception e) {
            Log.d("common", "initDisplayRect() err : " + e);
            e.printStackTrace();
        }
        Rect rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        Log.d("common", "initDisplayRect() mRect : " + rect);
        return rect;
    }

    public static String getGameSpaceVersionName(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getLeftLayoutTranslationX(Context context) {
        int i = isLayoutRTL(context) ? 200 : NeoDownloadHelper.CHANGE_ALL;
        Log.d("common", "getLeftLayoutTranslationX  leftLayoutTranslationX = " + i);
        return i;
    }

    public static int getRightLayoutTranslationX(Context context) {
        int i = isLayoutRTL(context) ? NeoDownloadHelper.CHANGE_ALL : 200;
        Log.d("common", "getRightLayoutTranslationX  rightLayoutTranslationX = " + i);
        return i;
    }

    public static float getScaleXByMatrix(Matrix matrix) {
        if (matrix == null) {
            return 0.0f;
        }
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return fArr[0];
    }

    public static Uri getSecureUri(Uri uri) {
        String uri2;
        if (uri == null || (uri2 = uri.toString()) == null || uri2.contains("../") || uri2.contains("./")) {
            return null;
        }
        if (uri2.startsWith("/storage") || uri2.startsWith("/data") || uri2.startsWith("file:///") || uri2.startsWith("content://") || uri2.startsWith("content://media") || uri2.startsWith(DOCUMENT_URI)) {
            return Uri.parse(uri2);
        }
        return null;
    }

    public static boolean isAndroidU() {
        return Build.VERSION.SDK_INT >= 34;
    }

    public static boolean isAppInstalled(String str) {
        try {
            getContext().getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isHandleConnected() {
        if (!FeatureUtil.handHeldEnable()) {
            return false;
        }
        String string = Settings.Global.getString(getContext().getContentResolver(), "db_hand_held_mode");
        if (string == null || !string.equals("0")) {
            return isHandleDeviceConnected();
        }
        return false;
    }

    public static boolean isHandleDeviceConnected() {
        if (!FeatureUtil.handHeldEnable()) {
            return false;
        }
        String string = Settings.Global.getString(getContext().getContentResolver(), "nubia_operation_device_infos");
        if (TextUtils.isEmpty(string)) {
            Log.d(TAG, "--------->isHandleConnected() deviceInfo : " + string);
            return false;
        }
        for (String str : string.split(";")) {
            if (TextUtils.isEmpty(str)) {
                Log.d(TAG, "--------->isHandleConnected() info : " + str);
                return false;
            }
            String[] split = str.split(",");
            if (split.length == 4 && !TextUtils.isEmpty(split[0]) && Integer.parseInt(split[0]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHighVersion() {
        try {
            return new File("/proc/driver/micropump/enable").exists();
        } catch (Exception e) {
            Log.e(TAG, "getHmlVersion: file not found", e);
            return false;
        }
    }

    public static boolean isInter() {
        return !TextUtils.isEmpty(BuildConfig.FLAVOR);
    }

    public static boolean isK68() {
        return Build.DEVICE != null && Build.DEVICE.contains("K68");
    }

    public static boolean isLayoutRTL(Context context) {
        int layoutDirection = context.getResources().getConfiguration().getLayoutDirection();
        Log.d("common", "isLayoutRTL = " + layoutDirection);
        return layoutDirection == 1;
    }

    public static boolean isMyOs() {
        return !isNubiaOs();
    }

    public static boolean isNubia() {
        return !TextUtils.isEmpty(BuildConfig.FLAVOR);
    }

    public static boolean isNubiaChina() {
        return isNubia() && !isInter();
    }

    public static boolean isNubiaOs() {
        return isRedMagicLegacyProject();
    }

    public static boolean isP658F01() {
        return Build.DEVICE != null && Build.DEVICE.contains("P658F01");
    }

    public static boolean isP720P01() {
        return Build.DEVICE != null && Build.DEVICE.contains("P720P01");
    }

    public static boolean isP820S01() {
        return Build.DEVICE != null && Build.DEVICE.contains("P820S01");
    }

    public static boolean isPQ83P01() {
        return Build.DEVICE != null && Build.DEVICE.contains("PQ83P01");
    }

    public static boolean isRedMagicLegacyProject() {
        String str = Build.DEVICE;
        Log.d(TAG, "isRedMagicLegacyProject: device = " + str);
        if (str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("679") || str.contains("709");
    }

    public static boolean isRedMagicRunOnMyOs() {
        boolean z = false;
        try {
            Class<?> cls = Class.forName("com.zte.feature.Feature");
            z = ((Boolean) cls.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls, "ZTE_FEATURE_REDMAGIC_GAMEKEY", false)).booleanValue();
            Log.d(TAG, "--------->isRedMagicRunOnMyOs result : " + z);
            return z;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "isRedMagicRunOnMyOs() but ClassNotFoundException : " + e.getMessage());
            return z;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.d(TAG, "isRedMagicRunOnMyOs() but IllegalAccessException : " + e2.getMessage());
            return z;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            Log.d(TAG, "isRedMagicRunOnMyOs() but NoSuchMethodException : " + e3.getMessage());
            return z;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            Log.d(TAG, "isRedMagicRunOnMyOs() but InvocationTargetException : " + e4.getMessage());
            return z;
        }
    }

    public static boolean isSecurePath(String str) {
        if (str == null || str.contains("../") || str.contains("./") || str.contains("~/")) {
            return false;
        }
        return str.startsWith("/storage") || str.startsWith("/data") || str.startsWith("file:///") || str.startsWith("content://") || str.startsWith("android.resource://") || str.startsWith("https://") || str.startsWith("content://media") || str.startsWith("default_url") || str.startsWith("SELF_URL") || str.startsWith(DOCUMENT_URI);
    }

    public static boolean isSetupComplete() {
        return Settings.Secure.getInt(getContext().getContentResolver(), "user_setup_complete", 0) == 1;
    }

    public static boolean isSlenderPhone() {
        return Build.DEVICE != null && Build.DEVICE.contains(TgkHelper.P720F10_DEVICE);
    }

    public static boolean isZte() {
        TextUtils.isEmpty(BuildConfig.FLAVOR);
        return false;
    }

    public static void notifyHandheldModeChanged(boolean z) {
        String str = z ? "1" : "0";
        if (str.equals(Settings.Global.getString(getContext().getContentResolver(), SETTING_HANDHELD))) {
            return;
        }
        Log.d(TAG, "notifyHandheldModeChanged() isHandheldMode : ".concat(str));
        Settings.Global.putString(getContext().getContentResolver(), SETTING_HANDHELD, str);
    }

    public static void startPcPlay(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.zte.pcgame", "com.zte.pcgame.activity.PCGameLauncher");
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, " startPcPlay exception ----- ", e);
        }
    }

    public static void startStreamPlay(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.zte.streamgame", "com.zte.streamgame.StreamGameActivity");
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, " startHandlePlay exception ----- ", e);
        }
    }

    public static void test() {
        Log.d("common", "CommonUtil - test()");
    }
}
