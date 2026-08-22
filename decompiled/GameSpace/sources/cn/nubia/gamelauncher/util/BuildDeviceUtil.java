package cn.nubia.gamelauncher.util;

import android.os.Build;

/* loaded from: classes.dex */
public final class BuildDeviceUtil {
    public static boolean is609Project() {
        return Build.DEVICE.equals("NX609J");
    }

    public static boolean is619Project() {
        return Build.DEVICE.equals("NX619J");
    }

    public static boolean isAndroidQ() {
        return true;
    }

    public static boolean isAndroidU() {
        return Build.VERSION.SDK_INT >= 34;
    }
}
