package cn.nubia.gamecenter.settings.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.config.android.NubiaFeatureConfig;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class Utils {
    public static final String CHANNEL_INTER = "inter";
    private static final String CLASS_PLATFORM_CONFIG = "com.zte.PlatformConfig";
    private static final String CUST_VARIANT_ID_PROP = "persist.vendor.custom.variant.id";
    private static final String METHOD_HASCOLORFULLIGHTHW = "hasColorfulLightHw";
    private static final String METHOD_IS_CUSTOMIZE_FOR_CN_IP_TFOP = "isCustomizeForCN_IP_TFOP";
    private static final String NUBIA_COLORFULLIGHT_MANAGER = "nubia.hardware.ColorfulLightManager";
    public static final String PACKAGE_ARKBASE = "cn.nubia.arkbase";
    public static final String PACKAGE_INTELLITEXT = "com.zte.intellitext";
    public static final String PACKAGE_MIPOP = "com.android.mipop";
    public static final String PACKAGE_ONE_MORE_THING = "com.zte.onemorething";
    public static final String PACKAGE_ZGESTURE = "com.zte.zgesture";
    public static final String PKG_NAME_GAME_SPACE = "cn.nubia.gamelauncher";
    private static final String TAG = "Utils";
    private static boolean mHasNewVersion;
    static String micropumpIdPathForTest;

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
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

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
            return null;
        } catch (Exception e) {
            LogUtil.e(TAG, "getVersionName, " + e);
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
            LogUtil.e(TAG, "hasColorfulLightHw " + e);
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
            LogUtil.i(TAG, "Not Found " + str);
            return false;
        }
    }

    public static boolean isCustomizeForCN_IP_TFOP() {
        boolean z = false;
        try {
            z = ((Boolean) Class.forName(CLASS_PLATFORM_CONFIG).getDeclaredMethod(METHOD_IS_CUSTOMIZE_FOR_CN_IP_TFOP, new Class[0]).invoke(null, new Object[0])).booleanValue();
            LogUtil.i(TAG, "isCustomizeForCN_IP_TFOP:" + z);
            return z;
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
            return z;
        }
    }

    public static boolean isCustomizeIP_PB_CN() {
        boolean contains = Build.PRODUCT.contains("NX789J");
        if (!contains) {
            return contains;
        }
        String str = SystemProperties.get(CUST_VARIANT_ID_PROP, "");
        LogUtil.i(TAG, "persist.vendor.custom.variant.id:" + str);
        return "IP_PB_CN".equals(str) || "GEN_PB_US".equals(str) || "GEN_PB_EU".equals(str);
    }

    public static boolean isInternal(Context context) {
        return CommonUtil.isInter();
    }

    public static boolean isInternalVersion() {
        return "1".equals(SystemProperties.get("sys.nubia_internal_version_flag", "0"));
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isMTKLiquidCool() {
        /*
            java.lang.String r0 = android.os.Build.PRODUCT
            java.lang.String r1 = "P688F02"
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L54
            r0 = 0
            java.lang.String r1 = cn.nubia.gamecenter.settings.utils.Utils.micropumpIdPathForTest     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            if (r1 == 0) goto L10
            goto L12
        L10:
            java.lang.String r1 = "/proc/driver/micropump_id"
        L12:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L48
            r2.close()     // Catch: java.io.IOException -> L24
            goto L41
        L24:
            r1 = move-exception
            r1.printStackTrace()
            goto L41
        L29:
            r0 = move-exception
            goto L32
        L2b:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L49
        L2f:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L32:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L48
            if (r2 == 0) goto L3f
            r2.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L3b:
            r0 = move-exception
            r0.printStackTrace()
        L3f:
            java.lang.String r0 = "-1"
        L41:
            java.lang.String r1 = "1"
            boolean r0 = r1.equals(r0)
            return r0
        L48:
            r0 = move-exception
        L49:
            if (r2 == 0) goto L53
            r2.close()     // Catch: java.io.IOException -> L4f
            goto L53
        L4f:
            r1 = move-exception
            r1.printStackTrace()
        L53:
            throw r0
        L54:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.utils.Utils.isMTKLiquidCool():boolean");
    }

    public static boolean isNX669() {
        return Build.DEVICE.contains("NX669");
    }

    public static boolean isNX669SProduct() {
        return "NX669S_V1A".equals(SystemProperties.get("persist.sys.pcb_version_ext", "0"));
    }

    public static boolean isNX679() {
        return Build.DEVICE.contains("NX679");
    }

    public static boolean isNX709() {
        return Build.DEVICE.contains("NX709");
    }

    public static boolean isNotSupportMoJiHeiHuaPlug() {
        return Build.DEVICE.contains("NX679") || Build.DEVICE.contains("NX709");
    }

    public static boolean isNubiaOS() {
        return "nubia".equals(SystemProperties.get("ro.build.user", "nubia"));
    }

    public static boolean isPQ83A06() {
        return Build.DEVICE.equals("PQ83A06");
    }

    public static boolean isPQ84P01() {
        return Build.DEVICE.equals("PQ84P01");
    }

    public static boolean isRedMagicGamePhone() {
        return Build.DEVICE.contains("NX659") || Build.DEVICE.contains("NX669") || Build.DEVICE.contains("NX709") || Build.DEVICE.contains("NX679") || Build.DEVICE.contains("NX729") || Build.DEVICE.contains("MAGIC");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isSupport2Sim(android.content.Context r4) {
        /*
            java.lang.String r0 = "phoneCount:"
            r1 = 0
            java.lang.String r2 = "phone"
            java.lang.Object r4 = r4.getSystemService(r2)     // Catch: java.lang.Exception -> L26
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch: java.lang.Exception -> L26
            int r4 = r4.getPhoneCount()     // Catch: java.lang.Exception -> L26
            java.lang.String r2 = "Utils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L24
            r3.<init>(r0)     // Catch: java.lang.Exception -> L24
            java.lang.StringBuilder r0 = r3.append(r4)     // Catch: java.lang.Exception -> L24
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L24
            cn.nubia.gamecenter.settings.utils.LogUtil.i(r2, r0)     // Catch: java.lang.Exception -> L24
            goto L2b
        L24:
            r0 = move-exception
            goto L28
        L26:
            r0 = move-exception
            r4 = r1
        L28:
            r0.printStackTrace()
        L2b:
            r0 = 2
            if (r4 != r0) goto L2f
            r1 = 1
        L2f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.utils.Utils.isSupport2Sim(android.content.Context):boolean");
    }

    public static boolean isSupport4DVibrate() {
        String subValue;
        return (Build.DEVICE.contains("NX627") || (subValue = NubiaFeatureConfig.getSubValue("nubia_4d_vibration_feature", "is_open_4d_vibration", "false")) == null || !subValue.equals("true")) ? false : true;
    }

    public static boolean isSupportColorfulLight(Context context) {
        String subValue = NubiaFeatureConfig.getSubValue("nubia_colorfullight_feature", "support_colorful_light", "false");
        LogUtil.d(TAG, "colorfulLightFeature = " + subValue);
        if (subValue == null || !subValue.equals("true")) {
            return false;
        }
        String subValue2 = NubiaFeatureConfig.getSubValue("nubia_colorfullight_feature", "support_all_device_version_colorful_light", "false");
        LogUtil.d(TAG, "supportAllDevice = " + subValue2);
        if (subValue2 != null && subValue2.equals("true")) {
            return true;
        }
        int i = Settings.System.getInt(context.getContentResolver(), "nubia_fan_light_support", -1);
        LogUtil.i(TAG, "nubia_fan_light_support = " + i);
        return 1 != i;
    }

    public static boolean isSupportLTM() {
        return Build.DEVICE.contains("NX679S") || Build.DEVICE.contains("NX709S") || Build.DEVICE.contains("NX729J") || Build.DEVICE.contains("MAGIC");
    }

    public static boolean isSupportTencentLight() {
        String value = NubiaFeatureConfig.getValue("nubia_with_tencent_coop");
        LogUtil.d(TAG, "tencentLightFeature = " + value);
        return value != null && value.equals("true");
    }

    public static boolean isSupportVirtualGameKey() {
        return "1".equals(SystemProperties.get("ro.nubia.virtualgamekey.enable", "0"));
    }

    public static boolean isZte(Context context) {
        return false;
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void resetDensity(Context context, float f, float f2, int i, int i2) {
        if (f == 0.0f || i == 0 || f2 == 0.0f || context == null) {
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        displayMetrics.density = f;
        displayMetrics.scaledDensity = f2;
        displayMetrics.densityDpi = i;
        LogUtil.i(TAG, " resetDensity :" + displayMetrics.toString());
        Configuration configuration = context.getResources().getConfiguration();
        configuration.densityDpi = i2;
        LogUtil.i(TAG, " resetDensity configuration :" + i2);
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

    public static void setNewVersion(boolean z) {
        mHasNewVersion = z;
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

    public int millisToHour(long j) {
        long j2 = 86400000;
        long j3 = j / j2;
        return (int) (((j - (j2 * j3)) / 3600000) + (j3 * 24));
    }
}
