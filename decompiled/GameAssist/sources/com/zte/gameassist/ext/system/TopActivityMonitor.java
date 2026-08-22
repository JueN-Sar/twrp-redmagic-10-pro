package com.zte.gameassist.ext.system;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.ext.common.GAControllerProxy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class TopActivityMonitor {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f16678a = new HashMap();

    public interface FocusWindowCallback {
        void a(String str);

        default void onFocusWindowChanged(String str, int i2, Rect rect, Bundle bundle) {
            a(str);
        }
    }

    public interface FullActivityFirstCreateCallback {
        void a(ComponentName componentName);

        default void onFullActivityFirstCreate(ComponentName componentName, ActivityInfo activityInfo, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle) {
            a(componentName);
        }
    }

    public interface FullActivityResumedCallback {
        void a(ComponentName componentName);

        default void onFullActivityResumed(ComponentName componentName, ActivityInfo activityInfo, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle) {
            a(componentName);
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
            char c2;
            if (bundle == null || str == null) {
                return;
            }
            switch (str.hashCode()) {
                case 500814517:
                    if (str.equals("systemWindowChanged")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 547798587:
                    if (str.equals("focuesWindowChanged")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 838878766:
                    if (str.equals("fullActivityFirstCreate")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1623335880:
                    if (str.equals("activityResumed")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
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
                    if (this.mFullActivityFirstCreateCallback != null && bundle.containsKey("window_mode") && bundle.containsKey("activity_type") && bundle.containsKey("display_id") && bundle.containsKey("pid") && bundle.containsKey("user_id") && bundle.containsKey("activity") && bundle.containsKey("info")) {
                        this.mFullActivityFirstCreateCallback.onFullActivityFirstCreate((ComponentName) bundle.getParcelable("activity", ComponentName.class), (ActivityInfo) bundle.getParcelable("info", ActivityInfo.class), bundle.getInt("stack_id", -1), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("display_id"), bundle.getInt("activity_type"), bundle.getInt("window_mode"), bundle);
                        break;
                    }
                    break;
                case 3:
                    if (this.mFullActivityResumedCallback != null && bundle.containsKey("window_mode") && bundle.containsKey("activity_type") && bundle.containsKey("display_id") && bundle.containsKey("pid") && bundle.containsKey("user_id") && bundle.containsKey("activity") && bundle.containsKey("info")) {
                        this.mFullActivityResumedCallback.onFullActivityResumed((ComponentName) bundle.getParcelable("activity", ComponentName.class), (ActivityInfo) bundle.getParcelable("info", ActivityInfo.class), bundle.getInt("stack_id", -1), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("display_id"), bundle.getInt("activity_type"), bundle.getInt("window_mode"), bundle);
                        break;
                    }
                    break;
            }
        }

        public void registerAppCallback() {
            try {
                GAControllerProxy.c().e("register_app_callback", null, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void unregisterAppCallback() {
            try {
                GAControllerProxy.c().e("unregister_app_callback", null, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public interface SystemWindowCallback {
        void a(boolean z, String str);

        default void onSystemWindowChanged(boolean z, String str, String str2, int i2, Bundle bundle) {
            a(z, str);
        }
    }

    public static void a(FullActivityResumedCallback fullActivityResumedCallback) {
        Map map = f16678a;
        if (map.containsKey(fullActivityResumedCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, null, null, fullActivityResumedCallback);
        map.put(fullActivityResumedCallback, systemCallback);
        systemCallback.registerAppCallback();
    }
}
