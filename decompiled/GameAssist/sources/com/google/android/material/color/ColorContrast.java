package com.google.android.material.color;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ColorContrast {

    @RequiresApi
    private static class ColorContrastActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        /* renamed from: c, reason: collision with root package name */
        private final Set f14219c;

        /* renamed from: h, reason: collision with root package name */
        private final ColorContrastOptions f14220h;

        /* renamed from: i, reason: collision with root package name */
        private UiModeManager.ContrastChangeListener f14221i;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.f14219c.remove(activity);
            UiModeManager uiModeManager = (UiModeManager) activity.getSystemService("uimode");
            if (uiModeManager == null || this.f14221i == null || !this.f14219c.isEmpty()) {
                return;
            }
            uiModeManager.removeContrastChangeListener(this.f14221i);
            this.f14221i = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity activity, Bundle bundle) {
            UiModeManager uiModeManager = (UiModeManager) activity.getSystemService("uimode");
            if (uiModeManager != null && this.f14219c.isEmpty() && this.f14221i == null) {
                this.f14221i = new UiModeManager.ContrastChangeListener() { // from class: com.google.android.material.color.ColorContrast.ColorContrastActivityLifecycleCallbacks.1
                    @Override // android.app.UiModeManager.ContrastChangeListener
                    public void onContrastChanged(float f2) {
                        Iterator it = ColorContrastActivityLifecycleCallbacks.this.f14219c.iterator();
                        while (it.hasNext()) {
                            ((Activity) it.next()).recreate();
                        }
                    }
                };
                uiModeManager.addContrastChangeListener(ContextCompat.h(activity.getApplicationContext()), this.f14221i);
            }
            this.f14219c.add(activity);
            if (uiModeManager != null) {
                ColorContrast.a(activity, this.f14220h);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public static void a(Activity activity, ColorContrastOptions colorContrastOptions) {
        int b2;
        if (c() && (b2 = b(activity, colorContrastOptions)) != 0) {
            ThemeUtils.a(activity, b2);
        }
    }

    private static int b(Context context, ColorContrastOptions colorContrastOptions) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (c() && uiModeManager != null) {
            float contrast = uiModeManager.getContrast();
            int b2 = colorContrastOptions.b();
            int a2 = colorContrastOptions.a();
            if (contrast >= 0.6666667f) {
                return a2 == 0 ? b2 : a2;
            }
            if (contrast >= 0.33333334f) {
                return b2 == 0 ? a2 : b2;
            }
        }
        return 0;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 34;
    }
}
