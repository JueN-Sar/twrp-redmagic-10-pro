package cn.nubia.common.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.UserHandle;
import android.util.Log;
import cn.nubia.common.CommonApplication;
import java.util.List;

/* loaded from: classes.dex */
public class AppsHelper {
    static PackageManager mPackageManager;

    public static Drawable getAppIcon(String str) {
        Drawable contactsIcon;
        try {
            return (!str.equals("com.android.contacts") || (contactsIcon = getContactsIcon(str)) == null) ? getPackageManager().getApplicationIcon(str) : contactsIcon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppLabel(String str) {
        try {
            return getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getClassName(String str) {
        PackageInfo packageInfo;
        PackageManager packageManager = getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(str, 0);
        } catch (Exception e) {
            Log.e("Common", "Exception" + e);
            packageInfo = null;
        }
        if (packageInfo != null) {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(packageInfo.packageName);
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            if (queryIntentActivities.size() > 0) {
                return queryIntentActivities.iterator().next().activityInfo.name;
            }
        }
        return null;
    }

    public static Drawable getCloneAppIcon(String str, Drawable drawable) {
        PackageManager packageManager = getPackageManager();
        if (drawable == null) {
            return null;
        }
        try {
            drawable = packageManager.getUserBadgedIcon(drawable, UserHandle.getUserHandleForUid(getPackageManager().getApplicationInfo(str, 128).uid));
            Log.d("Pips", "getCloneAppIcon() success!");
            return drawable;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("Pips", "getCloneAppIcon() e : " + e);
            return drawable;
        }
    }

    public static ComponentName getComponentName(String str) {
        return new ComponentName(str, getClassName(str));
    }

    public static Drawable getContactsIcon(String str) {
        if (str == null) {
            return null;
        }
        for (LauncherActivityInfo launcherActivityInfo : ((LauncherApps) getContext().getSystemService("launcherapps")).getActivityList(str, UserHandle.getUserHandleForUid(0))) {
            if (launcherActivityInfo.getName().contains("DialtactsActivity")) {
                return launcherActivityInfo.getIcon(getContext().getResources().getDisplayMetrics().densityDpi);
            }
        }
        return null;
    }

    public static Context getContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    public static PackageManager getPackageManager() {
        if (mPackageManager == null) {
            mPackageManager = getContext().getPackageManager();
        }
        return mPackageManager;
    }
}
