package cn.nubia.gamelauncher.gamecontrolpanel.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.media3.common.MimeTypes;
import cn.nubia.config.android.NubiaFeatureConfig;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.Util;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class Utils {
    public static final String GAMEBOX_MIRROR_DISPLAYID = "gamebox_mirror_displayid";
    static final String GPU_DRIVERS_PACKAGE_NAME = "cn.nubia.gpu.drivers";
    private static final int HIGH_HML_VERSION = 0;
    private static final int LOW_HML_VERSION = 1;
    public static final String LOW_POWER_MODE = "low_power";
    private static final String METHOD_HASCOLORFULLIGHTHW = "hasColorfulLightHw";
    private static final String NUBIA_COLORFULLIGHT_MANAGER = "nubia.hardware.ColorfulLightManager";
    static final String NUBIA_FAN_PACKAGE_NAME = "cn.nubia.fan";
    public static final String PKG_NAME_GAME_SPACE = "cn.nubia.gamelauncher";
    public static String PLATFORM_FLAG = "ro.vendor.feature.soc_vendor";
    private static final String TAG = "Utils";
    private static String mCurrentPkgName = "";
    private static boolean mHasNewVersion = false;
    private static String mHighLightViewId = null;
    private static boolean mIsShortcut = false;
    private static String shortCutLabel;
    private static CharSequence mCurrentAppName = "";
    private static List<String> mGpuPackageWhiteList = Arrays.asList(HighLightsUtils.WZRY_PACKAGE_NAME, HighLightsUtils.CJZC_PACKAGE_NAME, HighLightsUtils.PUBG_PACKAGE_NAME, HighLightsUtils.LOL_PACKAGE_NAME, "com.netease.aceracer", "com.dts.freefireth", "com.dts.freefiremax");

    public static int dpToPx(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    public static CharSequence getAppNameByPkgName(Context context, String str) {
        CharSequence charSequence;
        try {
            charSequence = context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 128));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, " getAppNameByPkgName --error---  " + e.toString());
            charSequence = null;
        }
        LogUtil.i(TAG, " --- getAppNameByPkgName --- appName = " + ((Object) charSequence));
        return charSequence;
    }

    public static int getAppUidByPkgName(Context context, String str) {
        int i = 0;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                i = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, " getAppUidByPkgName --error---  " + e.toString());
        }
        LogUtil.i(TAG, " --- getAppUidByPkgName --- appUid = " + i);
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0070, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (r6 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getCurPkgAutoOpenFanStatus(android.content.Context r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "autoOpenFan"
            java.lang.String r1 = "Utils"
            java.lang.String r2 = " getCurPkgAutoOpenFanStatus ---- autoOpenFan = "
            java.lang.String r3 = "component LIKE '"
            java.lang.String r4 = " getCurPkgAutoOpenFanStatus error : "
            r5 = 0
            r6 = 0
            android.content.ContentResolver r7 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            android.net.Uri r8 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.APPADD_URI     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13 = 1
            java.lang.String[] r9 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r9[r5] = r0     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r14 = "%' "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r10 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r11 = 0
            r12 = 0
            android.database.Cursor r6 = r7.query(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L40
            boolean r13 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r13 == 0) goto L40
            int r13 = r6.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            int r5 = r6.getInt(r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
        L40:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r5)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L70
        L52:
            r6.close()
            goto L70
        L56:
            r13 = move-exception
            goto L71
        L58:
            r13 = move-exception
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
            r14.<init>(r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r13 = r14.append(r13)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.e(r1, r13)     // Catch: java.lang.Throwable -> L56
            if (r6 == 0) goto L70
            goto L52
        L70:
            return r5
        L71:
            if (r6 == 0) goto L76
            r6.close()
        L76:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getCurPkgAutoOpenFanStatus(android.content.Context, java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0070, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (r6 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getCurPkgAutoOpenLiquidStatus(android.content.Context r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "autoOpenLiquid"
            java.lang.String r1 = "Utils"
            java.lang.String r2 = " getCurPkgAutoOpenLiquidStatus ---- autoOpenLiquid = "
            java.lang.String r3 = "component LIKE '"
            java.lang.String r4 = " getCurPkgAutoOpenLiquidStatus error : "
            r5 = 0
            r6 = 0
            android.content.ContentResolver r7 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            android.net.Uri r8 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.APPADD_URI     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13 = 1
            java.lang.String[] r9 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r9[r5] = r0     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r14 = "%' "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r10 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r11 = 0
            r12 = 0
            android.database.Cursor r6 = r7.query(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L40
            boolean r13 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r13 == 0) goto L40
            int r13 = r6.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            int r5 = r6.getInt(r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
        L40:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r5)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L70
        L52:
            r6.close()
            goto L70
        L56:
            r13 = move-exception
            goto L71
        L58:
            r13 = move-exception
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
            r14.<init>(r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r13 = r14.append(r13)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.e(r1, r13)     // Catch: java.lang.Throwable -> L56
            if (r6 == 0) goto L70
            goto L52
        L70:
            return r5
        L71:
            if (r6 == 0) goto L76
            r6.close()
        L76:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getCurPkgAutoOpenLiquidStatus(android.content.Context, java.lang.String):int");
    }

    public static CharSequence getCurrentAppName() {
        return mCurrentAppName;
    }

    public static String getCurrentPkgName() {
        return mCurrentPkgName;
    }

    public static int getDefaultDisplayDensity() {
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            Method method = cls.getMethod("getWindowManagerService", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(cls, new Object[0]);
            Method method2 = invoke.getClass().getMethod("getInitialDisplayDensity", Integer.TYPE);
            method2.setAccessible(true);
            return ((Integer) method2.invoke(invoke, 0)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getHighLightViewId() {
        return mHighLightViewId;
    }

    public static String getShortCutLabel() {
        return shortCutLabel;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0070, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (r6 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getShortcutAutoOpenFanStatus(android.content.Context r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "autoOpenFan"
            java.lang.String r1 = "Utils"
            java.lang.String r2 = " getShortcutAutoOpenFanStatus ---- autoOpenFan = "
            java.lang.String r3 = "label LIKE '"
            java.lang.String r4 = " getShortcutAutoOpenFanStatus error : "
            r5 = 0
            r6 = 0
            android.content.ContentResolver r7 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            android.net.Uri r8 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.SHORTCUT_URI     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13 = 1
            java.lang.String[] r9 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r9[r5] = r0     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r14 = "%' "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r10 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r11 = 0
            r12 = 0
            android.database.Cursor r6 = r7.query(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L40
            boolean r13 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r13 == 0) goto L40
            int r13 = r6.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            int r5 = r6.getInt(r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
        L40:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r5)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L70
        L52:
            r6.close()
            goto L70
        L56:
            r13 = move-exception
            goto L71
        L58:
            r13 = move-exception
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
            r14.<init>(r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r13 = r14.append(r13)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.e(r1, r13)     // Catch: java.lang.Throwable -> L56
            if (r6 == 0) goto L70
            goto L52
        L70:
            return r5
        L71:
            if (r6 == 0) goto L76
            r6.close()
        L76:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortcutAutoOpenFanStatus(android.content.Context, java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0070, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (r6 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getShortcutAutoOpenLiquidStatus(android.content.Context r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "autoOpenLiquid"
            java.lang.String r1 = "Utils"
            java.lang.String r2 = " getShortcutAutoOpenLiquidStatus ---- autoOpenLiquid = "
            java.lang.String r3 = "label LIKE '"
            java.lang.String r4 = " getShortcutAutoOpenLiquidStatus error : "
            r5 = 0
            r6 = 0
            android.content.ContentResolver r7 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            android.net.Uri r8 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.SHORTCUT_URI     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13 = 1
            java.lang.String[] r9 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r9[r5] = r0     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r14 = "%' "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r10 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r11 = 0
            r12 = 0
            android.database.Cursor r6 = r7.query(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L40
            boolean r13 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r13 == 0) goto L40
            int r13 = r6.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            int r5 = r6.getInt(r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
        L40:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            r13.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.StringBuilder r13 = r13.append(r5)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r13)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            if (r6 == 0) goto L70
        L52:
            r6.close()
            goto L70
        L56:
            r13 = move-exception
            goto L71
        L58:
            r13 = move-exception
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
            r14.<init>(r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r13 = r14.append(r13)     // Catch: java.lang.Throwable -> L56
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L56
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.e(r1, r13)     // Catch: java.lang.Throwable -> L56
            if (r6 == 0) goto L70
            goto L52
        L70:
            return r5
        L71:
            if (r6 == 0) goto L76
            r6.close()
        L76:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortcutAutoOpenLiquidStatus(android.content.Context, java.lang.String):int");
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
            return null;
        } catch (Exception e) {
            LogUtil.e(TAG, "getVersionName, " + e.toString());
            return null;
        }
    }

    public static boolean hasColorfulLightHw() {
        try {
            Class<?> cls = Class.forName(NUBIA_COLORFULLIGHT_MANAGER);
            Method declaredMethod = cls.getDeclaredMethod(METHOD_HASCOLORFULLIGHTHW, new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(cls.newInstance(), new Object[0])).booleanValue();
        } catch (Exception e) {
            LogUtil.e(TAG, "hasColorfulLightHw " + e.toString());
            return true;
        }
    }

    public static boolean hasNewVersion() {
        return mHasNewVersion;
    }

    public static boolean isAppExist(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static Boolean isDtsSupportedForUid(Context context, int i) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        try {
            Method declaredMethod = audioManager.getClass().getDeclaredMethod("isDtsESupportedForUid", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Boolean bool = (Boolean) declaredMethod.invoke(audioManager, Integer.valueOf(i));
            LogUtil.i(TAG, " isDtsSupported  value = " + bool);
            return bool;
        } catch (Exception unused) {
            LogUtil.e(TAG, "isDtsESupportedForUid -- error --- ");
            return null;
        }
    }

    public static boolean isGameboxMode(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "gamebox_mirror_displayid", 0) != 0;
    }

    public static boolean isHighVersion() {
        try {
            return new File("/proc/driver/micropump/enable").exists();
        } catch (Exception e) {
            LogUtil.e(TAG, "getHmlVersion: file not found", e);
            return false;
        }
    }

    public static boolean isInternalVersion() {
        return CommonUtil.isInternalVersion();
    }

    public static boolean isLowPowerMode(Context context) {
        return context != null && Settings.Global.getInt(context.getContentResolver(), LOW_POWER_MODE, 0) == 1;
    }

    public static boolean isNX669SProduct() {
        return "NX669S_V1A".equals(SystemProperties.get("persist.sys.pcb_version_ext", "0"));
    }

    public static boolean isNubiaOS() {
        return "nubia".equals(SystemProperties.get("ro.build.user", "nubia"));
    }

    public static boolean isQcomPlatform() {
        return "qcom".equals(SystemProperties.get(PLATFORM_FLAG, ""));
    }

    public static boolean isRedMagicPad(Context context) {
        return false;
    }

    public static boolean isShortcut() {
        return mIsShortcut;
    }

    public static boolean isSprdPlatform() {
        return "sprd".equals(SystemProperties.get(PLATFORM_FLAG, ""));
    }

    public static boolean isSupport4DVibrate() {
        String subValue;
        return (Build.DEVICE.contains("NX627") || (subValue = NubiaFeatureConfig.getSubValue("nubia_4d_vibration_feature", "is_open_4d_vibration", "false")) == null || !subValue.equals("true")) ? false : true;
    }

    public static boolean isSupportColorfulLight() {
        String subValue = NubiaFeatureConfig.getSubValue("nubia_colorfullight_feature", "support_colorful_light", "false");
        LogUtil.d(TAG, "colorfulLightFeature = " + subValue);
        return subValue != null && subValue.equals("true");
    }

    public static boolean isSupportFan(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("cn.nubia.fan", 8192);
            LogUtil.d(TAG, "isSupportFan: " + (applicationInfo != null));
            return applicationInfo != null;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "isSupportFan: Package not found", e);
            return false;
        }
    }

    public static boolean isSupportGpu(String str) {
        if (str == null) {
            return false;
        }
        Iterator<String> it = mGpuPackageWhiteList.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupportLTM() {
        return Build.DEVICE.contains("NX679S") || Build.DEVICE.contains("NX709S") || Build.DEVICE.contains("NX729J");
    }

    public static boolean isSupportSnapdragonAdrenoGpu(Context context) {
        boolean z = false;
        try {
            if (context.getPackageManager().getApplicationInfo(GPU_DRIVERS_PACKAGE_NAME, 8192) == null) {
                return false;
            }
            z = true;
            LogUtil.i(TAG, "isSupportSnapdragonAdrenoGpu: isSupport = true");
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "isSupportSnapdragonAdrenoGpu: " + e.toString());
            return z;
        }
    }

    public static boolean isSupportTencentLight() {
        String value = NubiaFeatureConfig.getValue("nubia_with_tencent_coop");
        LogUtil.d(TAG, "tencentLightFeature = " + value);
        return value != null && value.equals("true");
    }

    public static boolean isSupportVirtualGameKey() {
        return "1".equals(SystemProperties.get("ro.nubia.virtualgamekey.enable", "0"));
    }

    public static boolean isWhiteListDefaultOnWifi() {
        if (Build.DEVICE.contains("NX679S") || Build.DEVICE.contains("NX709S")) {
            return true;
        }
        Build.DEVICE.contains("NX729J");
        return true;
    }

    public static boolean isZte() {
        return Util.isZte();
    }

    public static void saveHighLightViewId(String str) {
        mHighLightViewId = str;
    }

    public static void setCurrentAppName(CharSequence charSequence) {
        LogUtil.d(TAG, " --- setCurrentAppName --- mCurrentAppName = " + ((Object) charSequence));
        mCurrentAppName = charSequence;
    }

    public static void setCurrentPkgName(String str) {
        mCurrentPkgName = str;
    }

    public static void setNewVersion(boolean z) {
        mHasNewVersion = z;
    }

    public static void setShortCutLabel(String str) {
        shortCutLabel = str;
    }

    public static void setShortcut(boolean z) {
        mIsShortcut = z;
    }

    public static boolean supportGameDock() {
        try {
            if (!Boolean.valueOf(NubiaFeatureConfig.getSubValue("nubia_controlcenter_feature", "supportGameDock", "false")).booleanValue()) {
                if (!Build.DEVICE.contains("NX629")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "get nubia_support_game_finger_point error", e);
            return false;
        }
    }

    public static boolean supportResourceSettings(String str) {
        if (PerformanceConstant.SPREAD_CHIP) {
            return false;
        }
        return ControlPanelFeatureHelper.isTouchGameKeySupported() && str.contains(ControlPanelFeatureHelper.MenuHelper.ResourceSettings.toString());
    }

    public static void updateCurPkgAutoOpenFanStatus(Context context, String str, int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("autoOpenFan", Integer.valueOf(i));
            LogUtil.d(TAG, " updateCurPkgAutoOpenFanStatus ---  autoOpenFan = " + i);
            context.getContentResolver().update(ConstantVariable.APPADD_URI, contentValues, "component LIKE '" + str + "%' ", null);
        } catch (Exception e) {
            LogUtil.e(TAG, " updateCurPkgAutoOpenFanStatus error : " + e.toString());
        }
    }

    public static void updateCurPkgAutoOpenLiquidStatus(Context context, String str, int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("autoOpenLiquid", Integer.valueOf(i));
            LogUtil.d(TAG, " updateCurPkgAutoOpenLiquidStatus ---  autoOpenLiquid = " + i);
            context.getContentResolver().update(ConstantVariable.APPADD_URI, contentValues, "component LIKE '" + str + "%' ", null);
        } catch (Exception e) {
            LogUtil.e(TAG, " updateCurPkgAutoOpenLiquidStatus error : " + e.toString());
        }
    }

    public static void updateDensity(Context context) {
        if (context == null) {
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        float f2 = displayMetrics.scaledDensity;
        float f3 = (displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels) / 360.0f;
        displayMetrics.density = f3;
        displayMetrics.scaledDensity = (f2 / f) * f3;
        displayMetrics.densityDpi = ((int) f3) * 160;
        Configuration configuration = context.getResources().getConfiguration();
        configuration.densityDpi = getDefaultDisplayDensity();
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

    public static void updateShortcutAutoOpenFanStatus(Context context, String str, int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("autoOpenFan", Integer.valueOf(i));
            LogUtil.d(TAG, " updateShortcutAutoOpenFanStatus ---  autoOpenFan = " + i);
            context.getContentResolver().update(ConstantVariable.SHORTCUT_URI, contentValues, "label LIKE '" + str + "%' ", null);
        } catch (Exception e) {
            LogUtil.e(TAG, " updateShortcutAutoOpenFanStatus error : " + e.toString());
        }
    }

    public static void updateShortcutAutoOpenLiquidStatus(Context context, String str, int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("autoOpenLiquid", Integer.valueOf(i));
            LogUtil.d(TAG, " updateShortcutAutoOpenLiquidStatus ---  autoOpenLiquid = " + i);
            context.getContentResolver().update(ConstantVariable.SHORTCUT_URI, contentValues, "label LIKE '" + str + "%' ", null);
        } catch (Exception e) {
            LogUtil.e(TAG, " updateShortcutAutoOpenLiquidStatus error : " + e.toString());
        }
    }
}
