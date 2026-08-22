package cn.nubia.gamelauncher.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.TextView;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CommonUtil {
    public static final String GAMESPACE_PACKAGENAME = "cn.nubia.gamelauncher";
    public static final String TX_TRACE_PACKAGENAME = "cn.tencent.nubia";
    private static String mVideoTotalTime;

    public static String convertPackageName(String str) {
        if (str == null || !str.contains(",")) {
            return str;
        }
        try {
            return str.substring(0, str.indexOf(","));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToShowStateText(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (NeoGameDBColumns.STATUS_CONNECT.equals(str)) {
            return GameLauncherApplication.CONTEXT.getResources().getString(R.string.connecting);
        }
        if (NeoGameDBColumns.STATUS_DOWNLOADING.equals(str)) {
            return GameLauncherApplication.CONTEXT.getResources().getString(R.string.downloading);
        }
        if (NeoGameDBColumns.STATUS_PAUSE.equals(str)) {
            return GameLauncherApplication.CONTEXT.getResources().getString(R.string.paused);
        }
        if (NeoGameDBColumns.STATUS_IN_INSTALLTION.equals(str)) {
            return GameLauncherApplication.CONTEXT.getResources().getString(R.string.installing);
        }
        return null;
    }

    public static ComponentName createComponentName(String str) {
        try {
            return new ComponentName(str.substring(0, str.indexOf(",")), str.substring(str.indexOf(",") + 1, str.length()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 1), Math.max(drawable.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
        drawable.draw(canvas);
        canvas.setBitmap(null);
        return createBitmap;
    }

    public static String getTime(int i) {
        int i2 = i / 1000;
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        int i5 = i2 % 60;
        return i3 != 0 ? String.format("%02d:%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)) : String.format("%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i5));
    }

    public static boolean isAbroad() {
        return cn.nubia.common.util.CommonUtil.isInter();
    }

    public static boolean isAndroidVersionAtLeastVanillaIceCream() {
        return Build.VERSION.SDK_INT >= 35;
    }

    public static boolean isInstalled(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(installedPackages.get(i).packageName);
            }
        }
        return arrayList.contains(str);
    }

    public static boolean isInternalVersion() {
        return isAbroad();
    }

    public static boolean isMYOS_Project() {
        return Build.DEVICE != null && Build.DEVICE.contains("MAGIC");
    }

    public static boolean isNX669J_Project() {
        return Build.DEVICE != null && Build.DEVICE.contains("669");
    }

    public static boolean isNX679J_Project() {
        return Build.DEVICE != null && Build.DEVICE.contains("679");
    }

    public static boolean isNX709J_Project() {
        return Build.DEVICE != null && Build.DEVICE.contains("709");
    }

    public static boolean isNX729J_Project() {
        return Build.DEVICE != null && Build.DEVICE.contains("729");
    }

    public static void setVideoTotalTime(String str) {
        mVideoTotalTime = str;
    }

    public static boolean supportGameLauncherCC(Context context) {
        if (context == null) {
            return false;
        }
        int i = Settings.Global.getInt(context.getContentResolver(), "nubia_systemui_cc", 1);
        LogUtil.d(cn.nubia.common.util.CommonUtil.TAG, "---------->supportGameLauncherCC() : " + (i == 0));
        return i == 0 || Util.isZte();
    }

    public static void updateTime(TextView textView, int i) {
        textView.setText(getTime(i) + "/" + mVideoTotalTime);
    }
}
