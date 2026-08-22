package com.zte.gameassist.ext.system;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import cn.nubia.gamelauncher.service.GameFeatureService;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.ext.common.GAControllerProxy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class TopActivityMonitor {
    private static final Map<Object, SystemCallback> mSystemCallbackMap = new HashMap();

    public interface FocusWindowCallback {
        void onFocusWindowChanged(String str);

        default void onFocusWindowChanged(String str, int i, Rect rect, Bundle bundle) {
            onFocusWindowChanged(str);
        }
    }

    public interface FullActivityFirstCreateCallback {
        void onFullActivityFirstCreate(ComponentName componentName);

        default void onFullActivityFirstCreate(ComponentName componentName, ActivityInfo activityInfo, int i, int i2, int i3, int i4, int i5, int i6, Bundle bundle) {
            onFullActivityFirstCreate(componentName);
        }
    }

    public interface FullActivityResumedCallback {
        void onFullActivityResumed(ComponentName componentName);

        default void onFullActivityResumed(ComponentName componentName, ActivityInfo activityInfo, int i, int i2, int i3, int i4, int i5, int i6, Bundle bundle) {
            onFullActivityResumed(componentName);
        }
    }

    public static class SystemCallback extends ICallback.Stub {
        private final FocusWindowCallback mFocusWindowCallback;
        private final FullActivityFirstCreateCallback mFullActivityFirstCreateCallback;
        private final FullActivityResumedCallback mFullActivityResumedCallback;
        private final SystemWindowCallback mSystemWindowCallback;

        public SystemCallback(SystemWindowCallback systemWindowCallback, FocusWindowCallback focusWindowCallback, FullActivityFirstCreateCallback fullActivityFirstCreateCallback, FullActivityResumedCallback fullActivityResumedCallback) {
            this.mSystemWindowCallback = systemWindowCallback;
            this.mFocusWindowCallback = focusWindowCallback;
            this.mFullActivityFirstCreateCallback = fullActivityFirstCreateCallback;
            this.mFullActivityResumedCallback = fullActivityResumedCallback;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            char c;
            if (bundle == null || str == null) {
                return;
            }
            str.hashCode();
            switch (str.hashCode()) {
                case 500814517:
                    if (str.equals("systemWindowChanged")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 547798587:
                    if (str.equals("focuesWindowChanged")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 838878766:
                    if (str.equals("fullActivityFirstCreate")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1623335880:
                    if (str.equals("activityResumed")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    if (this.mSystemWindowCallback != null && bundle.containsKey("show") && bundle.containsKey("title") && bundle.containsKey("package_name") && bundle.containsKey("window_type")) {
                        this.mSystemWindowCallback.onSystemWindowChanged(bundle.getBoolean("show"), bundle.getString("title"), bundle.getString("package_name"), bundle.getInt("window_type"), bundle);
                        break;
                    }
                    break;
                case 1:
                    if (this.mFocusWindowCallback != null && bundle.containsKey("package_name") && bundle.containsKey("window_type") && bundle.containsKey("vis_rect")) {
                        this.mFocusWindowCallback.onFocusWindowChanged(bundle.getString("package_name"), bundle.getInt("window_type"), (Rect) bundle.getParcelable("vis_rect", Rect.class), bundle);
                        break;
                    }
                    break;
                case 2:
                    if (this.mFullActivityFirstCreateCallback != null && bundle.containsKey("window_mode") && bundle.containsKey("activity_type") && bundle.containsKey("display_id") && bundle.containsKey("pid") && bundle.containsKey("user_id") && bundle.containsKey(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY) && bundle.containsKey("info")) {
                        this.mFullActivityFirstCreateCallback.onFullActivityFirstCreate((ComponentName) bundle.getParcelable(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY, ComponentName.class), (ActivityInfo) bundle.getParcelable("info", ActivityInfo.class), bundle.getInt("stack_id", -1), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("display_id"), bundle.getInt("activity_type"), bundle.getInt("window_mode"), bundle);
                        break;
                    }
                    break;
                case 3:
                    if (this.mFullActivityResumedCallback != null && bundle.containsKey("window_mode") && bundle.containsKey("activity_type") && bundle.containsKey("display_id") && bundle.containsKey("pid") && bundle.containsKey("user_id") && bundle.containsKey(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY) && bundle.containsKey("info")) {
                        this.mFullActivityResumedCallback.onFullActivityResumed((ComponentName) bundle.getParcelable(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY, ComponentName.class), (ActivityInfo) bundle.getParcelable("info", ActivityInfo.class), bundle.getInt("stack_id", -1), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("display_id"), bundle.getInt("activity_type"), bundle.getInt("window_mode"), bundle);
                        break;
                    }
                    break;
            }
        }

        public void registerAppCallback() {
            try {
                GAControllerProxy.getInstance().invake(GAControllerProxy.INVAKE_REGISTER_APP_CALLBACK, null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void unregisterAppCallback() {
            try {
                GAControllerProxy.getInstance().invake(GAControllerProxy.INVAKE_UNREGISTER_APP_CALLBACK, null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface SystemWindowCallback {
        void onSystemWindowChanged(boolean z, String str);

        default void onSystemWindowChanged(boolean z, String str, String str2, int i, Bundle bundle) {
            onSystemWindowChanged(z, str);
        }
    }

    public static void registerFocusWindowCallback(FocusWindowCallback focusWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(focusWindowCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, focusWindowCallback, null, null);
        map.put(focusWindowCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void registerFullActivityFirstCreateCallback(FullActivityFirstCreateCallback fullActivityFirstCreateCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityFirstCreateCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, null, fullActivityFirstCreateCallback, null);
        map.put(fullActivityFirstCreateCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void registerFullActivityResumedCallback(FullActivityResumedCallback fullActivityResumedCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityResumedCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, null, null, fullActivityResumedCallback);
        map.put(fullActivityResumedCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void registerSystemWindowCallback(SystemWindowCallback systemWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(systemWindowCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(systemWindowCallback, null, null, null);
        map.put(systemWindowCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void unregisterFocusWindowCallback(FocusWindowCallback focusWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(focusWindowCallback)) {
            map.remove(focusWindowCallback).unregisterAppCallback();
        }
    }

    public static void unregisterFullActivityFirstCreateCallback(FullActivityFirstCreateCallback fullActivityFirstCreateCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityFirstCreateCallback)) {
            map.remove(fullActivityFirstCreateCallback).unregisterAppCallback();
        }
    }

    public static void unregisterFullActivityResumedCallback(FullActivityResumedCallback fullActivityResumedCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityResumedCallback)) {
            map.remove(fullActivityResumedCallback).unregisterAppCallback();
        }
    }

    public static void unregisterSystemWindowCallback(SystemWindowCallback systemWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(systemWindowCallback)) {
            map.remove(systemWindowCallback).unregisterAppCallback();
        }
    }
}
