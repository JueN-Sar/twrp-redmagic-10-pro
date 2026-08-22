package cn.nubia.gameassist.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManagerGlobal;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ActivityManagerWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AppsHelper {

    /* renamed from: a, reason: collision with root package name */
    static PackageManager f7648a;

    public static List a(Context context) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        try {
            for (LauncherActivityInfo launcherActivityInfo : ((LauncherApps) context.getSystemService("launcherapps")).getActivityList(null, UserHandle.getUserHandleForUid(0))) {
                String str = launcherActivityInfo.getApplicationInfo().packageName;
                if (packageManager.getLaunchIntentForPackage(str) != null && ActivityManagerWrapper.checkTaskSupportWr(str)) {
                    arrayList.add(launcherActivityInfo);
                }
            }
        } catch (Error e2) {
            e2.printStackTrace();
        }
        GaLog.a("AppsHelper", "getAllAppsKey:" + arrayList.size());
        return arrayList;
    }

    public static String b(String str) {
        try {
            return h().getApplicationLabel(h().getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static UserHandle c(Context context, String str, int i2) {
        try {
            PackageInfo packageInfoAsUser = context.getPackageManager().getPackageInfoAsUser(str, 0, i2);
            if (packageInfoAsUser != null) {
                return UserHandle.getUserHandleForUid(packageInfoAsUser.applicationInfo.uid);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Context d() {
        return GameAssistApplication.j();
    }

    public static Rect e(Context context) {
        Rect rect = new Rect();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (RotationMgr.k()) {
            int i4 = (i2 * 4) / 5;
            rect.left = i2 / 10;
            int a2 = CommonUtil.a(context, 110.0f);
            rect.top = a2;
            rect.right = rect.left + i4;
            rect.bottom = a2 + i4;
        } else {
            int i5 = (i3 * 4) / 5;
            int a3 = CommonUtil.a(context, 56.0f);
            rect.left = a3;
            int i6 = i3 / 10;
            rect.top = i6;
            rect.right = a3 + i5;
            rect.bottom = i6 + i5;
        }
        return rect;
    }

    public static Drawable f(Context context, String str) {
        Bitmap t;
        try {
            LauncherActivityInfo g2 = g(context, str, 0, null);
            return (g2 == null || g2.getApplicationInfo() == null || !"com.android.calendar".equals(g2.getApplicationInfo().packageName) || (t = IconResource.h(context).t(true)) == null) ? g2 != null ? g2.getIcon(WindowManagerGlobal.getWindowManagerService().getInitialDisplayDensity(0)) : k(str, 0) : new BitmapDrawable(context.getResources(), t);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static LauncherActivityInfo g(Context context, String str, int i2, String str2) {
        List<LauncherActivityInfo> activityList;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ("com.android.contacts".equals(str)) {
            str2 = "DialtactsActivity";
        }
        try {
            activityList = ((LauncherApps) context.getSystemService("launcherapps")).getActivityList(str, new UserHandle(i2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activityList != null && activityList.size() != 0) {
            if (TextUtils.isEmpty(str2)) {
                return activityList.get(0);
            }
            for (LauncherActivityInfo launcherActivityInfo : activityList) {
                if (launcherActivityInfo.getName().contains(str2)) {
                    return launcherActivityInfo;
                }
            }
            return null;
        }
        return null;
    }

    public static PackageManager h() {
        if (f7648a == null) {
            f7648a = d().getPackageManager();
        }
        return f7648a;
    }

    private static List i(Context context, Intent intent, int i2) {
        ArrayList arrayList = new ArrayList();
        try {
            List queryIntentActivitiesAsUser = context.getPackageManager().queryIntentActivitiesAsUser(intent, 131072, i2 == 999 ? 1000 : i2);
            return queryIntentActivitiesAsUser != null ? queryIntentActivitiesAsUser : arrayList;
        } catch (Exception e2) {
            GaLog.b("AppsHelper", "getResolveInfosForUserId excep intent:" + intent + ",userId:" + i2 + ",e:" + e2);
            return arrayList;
        }
    }

    public static Drawable j(Context context, String str) {
        Drawable f2 = f(context, str);
        PackageManager packageManager = context.getPackageManager();
        UserHandle c2 = c(context, str, 999);
        return (f2 == null || c2 == null) ? f2 : packageManager.getUserBadgedIcon(f2, c2);
    }

    private static Drawable k(String str, int i2) {
        PackageManager h2 = h();
        try {
            PackageInfo packageInfoAsUser = h2.getPackageInfoAsUser(str, 0, i2);
            if (packageInfoAsUser != null) {
                return packageInfoAsUser.applicationInfo.loadUnbadgedIcon(h2);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void l(Context context, String str, int i2) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if ("com.android.contacts".equals(str)) {
                launchIntentForPackage = new Intent("zte.mfv.intent.action.DIAL");
            }
            if (launchIntentForPackage == null) {
                GaLog.b("AppsHelper", "start window free form intent null");
                return;
            }
            launchIntentForPackage.setComponent(null);
            List i3 = i(context, launchIntentForPackage, i2);
            if (i3.isEmpty()) {
                GaLog.b("AppsHelper", "resolve info fail");
                return;
            }
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.INFO");
            intent.setPackage(str);
            intent.setClassName(((ResolveInfo) i3.get(0)).activityInfo.packageName, ((ResolveInfo) i3.get(0)).activityInfo.name);
            intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setLaunchWindowingMode(5);
            makeBasic.setLaunchBounds(e(context));
            Bundle bundle = makeBasic.toBundle();
            if (bundle == null) {
                GaLog.b("AppsHelper", "start window free form intent or bundle null");
                return;
            }
            intent.putExtra("start_from_heartservice_app_lock", true);
            bundle.putBoolean("WindowReply", true);
            context.startActivityAsUser(intent, bundle, UserHandle.of(i2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
