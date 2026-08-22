package cn.nubia.zte;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import cn.nubia.gamelauncher.aimhelper.IActivityEventsCallback;
import cn.nubia.tgk.TgkHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ZteHelper {
    Class<?> ActivityEventsManager;
    Object ActivityEventsManagerInstance;
    String ReflectionClass;
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

    private static class ZteHelperHolder {
        public static final ZteHelper INSTANCE = new ZteHelper();

        private ZteHelperHolder() {
        }
    }

    private ZteHelper() {
        this.ReflectionClass = TgkHelper.CLASS_NAME_ACTIVITY_EVENT;
        this.ActivityEventsManager = null;
        this.ActivityEventsManagerInstance = null;
        this.mActivityEventCallback = null;
    }

    public static ZteHelper getInstance() {
        return ZteHelperHolder.INSTANCE;
    }

    private void initActivityEventsManager(Context context) {
        try {
            Class<?> cls = Class.forName(this.ReflectionClass);
            this.ActivityEventsManager = cls;
            Method declaredMethod = cls.getDeclaredMethod(TgkHelper.METHOD_NAME_INSTANCE, Context.class);
            Log.d("zteg", " getInstance " + declaredMethod);
            declaredMethod.setAccessible(true);
            this.ActivityEventsManagerInstance = declaredMethod.invoke(this.ActivityEventsManager, context);
        } catch (ClassNotFoundException e) {
            Log.d("zteg", " initActivityEventsManager(1) e " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            Log.d("zteg", " initActivityEventsManager(3) e " + e2.getMessage());
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            Log.d("zteg", " initActivityEventsManager(2) e " + e3.getMessage());
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            Log.d("zteg", " initActivityEventsManager(4) e " + e4.getMessage());
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
        Bundle next;
        if (list != null && !list.isEmpty()) {
            Log.d("zteg", "findTopPkg() : " + list.size());
            Iterator<Bundle> it = list.iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                String string = next.getString("packageName");
                if (1 == next.getInt("windowMode")) {
                    Log.d("zteg", "findTopPkg() pkg : " + string);
                    String string2 = next.getString("reserved");
                    Log.d("zteg", "findTopPkg() uid(reserved) : " + string2);
                    return (string2.contains("999") && z) ? string + "&clone" : string;
                }
            }
            return null;
        }
        return null;
    }

    public String getCurrentTopPkg(Context context, boolean z) {
        Log.d("zteg", "getCurrentTopPkg() context : " + context + ", attachClone : " + z);
        return findTopPkg(getVisiblePackageDates(context), z);
    }

    public List<Bundle> getVisiblePackageDates(Context context) {
        Log.d("zteg", "getVisiblePackageDates() context : " + context);
        if (this.ActivityEventsManager == null || this.ActivityEventsManagerInstance == null) {
            initActivityEventsManager(context);
        }
        if (this.ActivityEventsManager == null || this.ActivityEventsManagerInstance == null) {
            return null;
        }
        Log.d("zteg", "getVisiblePackageDates() manager : " + this.ActivityEventsManager + ", Instance : " + this.ActivityEventsManagerInstance);
        try {
            Method declaredMethod = this.ActivityEventsManager.getDeclaredMethod(TgkHelper.METHOD_NAME_VISIBLE_PACKAGE, new Class[0]);
            declaredMethod.setAccessible(true);
            Log.d("zteg", "getVisiblePackageDates() : " + declaredMethod.invoke(this.ActivityEventsManagerInstance, new Object[0]));
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
