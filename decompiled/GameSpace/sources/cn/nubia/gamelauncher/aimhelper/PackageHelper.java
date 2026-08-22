package cn.nubia.gamelauncher.aimhelper;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.aimhelper.IActivityEventsCallback;
import cn.nubia.tgk.TgkHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes.dex */
public class PackageHelper {
    Class<?> ActivityEventsManager;
    Object ActivityEventsManagerInstance;
    String ReflectionClass;
    private final String TAG;
    private AppActivityEventCallback mActivityEventCallback;

    class AppActivityEventCallback extends IActivityEventsCallback.BaseStub {
        public AppActivityEventCallback() {
            Log.d("", "AppActivityEventCallback init");
        }

        @Override // cn.nubia.gamelauncher.aimhelper.IActivityEventsCallback
        public void notifiyActivityEvent(int i, String str, String str2, String str3) {
            Log.d("", " notifiyActivityEvent ok ");
        }
    }

    private static class PackageHelperHolder {
        public static final PackageHelper INSTANCE = new PackageHelper();

        private PackageHelperHolder() {
        }
    }

    private PackageHelper() {
        this.TAG = "PackageHelper";
        this.ReflectionClass = TgkHelper.CLASS_NAME_ACTIVITY_EVENT;
        this.ActivityEventsManager = null;
        this.ActivityEventsManagerInstance = null;
        this.mActivityEventCallback = null;
    }

    public static PackageHelper getInstance() {
        return PackageHelperHolder.INSTANCE;
    }

    private void initActivityEventsManager(Context context) {
        try {
            Class<?> cls = Class.forName(this.ReflectionClass);
            this.ActivityEventsManager = cls;
            Method declaredMethod = cls.getDeclaredMethod(TgkHelper.METHOD_NAME_INSTANCE, Context.class);
            Log.d("PackageHelper", " getInstance " + declaredMethod);
            declaredMethod.setAccessible(true);
            this.ActivityEventsManagerInstance = declaredMethod.invoke(this.ActivityEventsManager, context);
        } catch (ClassNotFoundException e) {
            Log.d("PackageHelper", " initActivityEventsManager(1) e " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            Log.d("PackageHelper", " initActivityEventsManager(3) e " + e2.getMessage());
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            Log.d("PackageHelper", " initActivityEventsManager(2) e " + e3.getMessage());
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            Log.d("PackageHelper", " initActivityEventsManager(4) e " + e4.getMessage());
            e4.printStackTrace();
        }
    }

    public void addCallback(Context context) {
        Class<?> cls = this.ActivityEventsManager;
        if (cls == null || this.ActivityEventsManagerInstance == null) {
            initActivityEventsManager(context);
            return;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("addCallBack", String.class, IBinder.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.ActivityEventsManagerInstance, "ActivityLifeShowObserver", this.mActivityEventCallback, 128);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public String findTopPkg(List<Bundle> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            Log.d("PackageHelper", "findTopPkg() : " + list.size());
            for (Bundle bundle : list) {
                String string = bundle.getString("packageName");
                if (1 == bundle.getInt("windowMode")) {
                    Log.d("PackageHelper", "findTopPkg() pkg : " + string);
                    String string2 = bundle.getString("reserved");
                    Log.d("PackageHelper", "findTopPkg() uid(reserved) : " + string2);
                    return (string2.contains("999") && z) ? string + "&clone" : string;
                }
            }
        }
        return null;
    }

    public String getCurrentTopPkg(Context context, boolean z) {
        Log.d("PackageHelper", "getCurrentTopPkg() context : " + context + ", attachClone : " + z);
        return findTopPkg(getVisiblePackageDates(context), z);
    }

    public long getFirstInstallTime(String str) {
        Log.d("PackageHelper", "getFirstInstallTime() pkg : " + str);
        if (this.ActivityEventsManager == null || this.ActivityEventsManagerInstance == null) {
            initActivityEventsManager(GameLauncherApplication.getAppContext());
        }
        long j = 0;
        if (this.ActivityEventsManager == null || this.ActivityEventsManagerInstance == null) {
            return 0L;
        }
        Log.d("PackageHelper", "getFirstInstallTime() manager : " + this.ActivityEventsManager + ", Instance : " + this.ActivityEventsManagerInstance);
        try {
            Method declaredMethod = this.ActivityEventsManager.getDeclaredMethod("getFirstInstallTime", String.class);
            declaredMethod.setAccessible(true);
            j = ((Long) declaredMethod.invoke(this.ActivityEventsManagerInstance, str)).longValue();
            Log.d("PackageHelper", "getFirstInstallTime() : " + j);
            return j;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return j;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return j;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return j;
        }
    }

    public List<Bundle> getVisiblePackageDates(Context context) {
        Log.d("PackageHelper", "getVisiblePackageDates() context : " + context);
        if (this.ActivityEventsManager == null || this.ActivityEventsManagerInstance == null) {
            initActivityEventsManager(context);
        }
        if (this.ActivityEventsManager == null || this.ActivityEventsManagerInstance == null) {
            return null;
        }
        Log.d("PackageHelper", "getVisiblePackageDates() manager : " + this.ActivityEventsManager + ", Instance : " + this.ActivityEventsManagerInstance);
        try {
            Method declaredMethod = this.ActivityEventsManager.getDeclaredMethod(TgkHelper.METHOD_NAME_VISIBLE_PACKAGE, new Class[0]);
            declaredMethod.setAccessible(true);
            Log.d("PackageHelper", "getVisiblePackageDates() : " + declaredMethod.invoke(this.ActivityEventsManagerInstance, new Object[0]));
            return (List) declaredMethod.invoke(this.ActivityEventsManagerInstance, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
