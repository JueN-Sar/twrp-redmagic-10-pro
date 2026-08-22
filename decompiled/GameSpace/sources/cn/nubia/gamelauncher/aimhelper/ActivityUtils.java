package cn.nubia.gamelauncher.aimhelper;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.zte.ZteHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes.dex */
public class ActivityUtils {
    private static Method mGetTopPackages;
    private static Object mNubiaSysState;

    static {
        try {
            Class<?> cls = Class.forName("android.app.NubiaSysState");
            mNubiaSysState = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            mGetTopPackages = cls.getDeclaredMethod(TgkHelper.METHOD_NAME_VISIBLE_PACKAGE, new Class[0]);
        } catch (Exception unused) {
        }
    }

    public static String getCurrentTopPkg(Context context) {
        return (Util.isZte() || Util.isMyOs()) ? ZteHelper.getInstance().getCurrentTopPkg(context, false) : getVisiblePackageDatas(context);
    }

    private static String getCurrentTopPkgP(Context context) {
        try {
            Object invoke = ActivityManager.class.getMethod("getService", new Class[0]).invoke((ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY), new Object[0]);
            Object invoke2 = invoke.getClass().getMethod("getFocusedStackInfo", new Class[0]).invoke(invoke, new Object[0]);
            ComponentName componentName = (ComponentName) invoke2.getClass().getField("topActivity").get(invoke2);
            if (componentName == null) {
                return null;
            }
            LogUtil.i("ActivityUtils", " getCurrentTopPkgP == " + componentName.getPackageName());
            return componentName.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentTopPkgQ() {
        String str = null;
        try {
            Class<?> cls = Class.forName("android.app.ActivityTaskManager");
            Object invoke = cls.getDeclaredMethod("getService", new Class[0]).invoke(cls, new Object[0]);
            if (invoke != null) {
                str = (String) Class.forName("android.app.IActivityTaskManager").getDeclaredMethod("getFocusedStackResumedPkg", new Class[0]).invoke(invoke, new Object[0]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
        LogUtil.d("ActivityUtils", "getCurrentTopPkg:" + str);
        return str;
    }

    public static String getPackageFromComponent(String str) {
        return !TextUtils.isEmpty(str) ? str.split("/")[0] : "";
    }

    private static String getVisiblePackageDatas(Context context) {
        Method method;
        try {
            LogUtil.d("ActivityUtils", "\n");
            LogUtil.d("ActivityUtils", "------------------------------------begin-----------------------------------");
            Object obj = mNubiaSysState;
            List<Bundle> visiblePackageDates = (obj == null || (method = mGetTopPackages) == null) ? PackageHelper.getInstance().getVisiblePackageDates(context) : (List) method.invoke(obj, new Object[0]);
            if (visiblePackageDates == null || visiblePackageDates.isEmpty()) {
                LogUtil.w("ActivityUtils", "getVisiblePackageDatas() topPackageList : " + visiblePackageDates);
            } else {
                if (visiblePackageDates.contains("cn.nubia.gamehighlights")) {
                    visiblePackageDates.remove("cn.nubia.gamehighlights");
                }
                if (visiblePackageDates != null && !visiblePackageDates.isEmpty()) {
                    for (Bundle bundle : visiblePackageDates) {
                        String string = bundle.getString("packageName");
                        int i = bundle.getInt("windowMode");
                        LogUtil.d("ActivityUtils", string + " windowMode=" + i);
                        if (i == 1) {
                            LogUtil.d("ActivityUtils", "getVisiblePackageDatas : " + string);
                            return string;
                        }
                    }
                }
            }
            LogUtil.d("ActivityUtils", "------------------------------------end-----------------------------------\n");
            return null;
        } catch (Exception e) {
            LogUtil.e("ActivityUtils", "getVisiblePackageDatas error, message=" + e.getMessage());
            return null;
        }
    }
}
