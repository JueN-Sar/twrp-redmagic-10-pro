package com.google.android.material.color;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import androidx.core.os.BuildCompat;
import com.google.android.material.R;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.color.utilities.SchemeContent;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class DynamicColors {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f14274a = {R.attr.dynamicColorThemeOverlay};

    /* renamed from: b, reason: collision with root package name */
    private static final DeviceSupportCondition f14275b;

    /* renamed from: c, reason: collision with root package name */
    private static final DeviceSupportCondition f14276c;

    /* renamed from: d, reason: collision with root package name */
    private static final Map f14277d;

    /* renamed from: e, reason: collision with root package name */
    private static final Map f14278e;

    /* renamed from: f, reason: collision with root package name */
    private static final String f14279f;

    private interface DeviceSupportCondition {
        boolean a();
    }

    private static class DynamicColorsActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        /* renamed from: c, reason: collision with root package name */
        private final DynamicColorsOptions f14281c;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity activity, Bundle bundle) {
            DynamicColors.a(activity, this.f14281c);
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

    public interface OnAppliedCallback {
        void a(Activity activity);
    }

    public interface Precondition {
        boolean a(Activity activity, int i2);
    }

    static {
        DeviceSupportCondition deviceSupportCondition = new DeviceSupportCondition() { // from class: com.google.android.material.color.DynamicColors.1
            @Override // com.google.android.material.color.DynamicColors.DeviceSupportCondition
            public boolean a() {
                return true;
            }
        };
        f14275b = deviceSupportCondition;
        DeviceSupportCondition deviceSupportCondition2 = new DeviceSupportCondition() { // from class: com.google.android.material.color.DynamicColors.2

            /* renamed from: a, reason: collision with root package name */
            private Long f14280a;

            @Override // com.google.android.material.color.DynamicColors.DeviceSupportCondition
            public boolean a() {
                if (this.f14280a == null) {
                    try {
                        Method declaredMethod = Build.class.getDeclaredMethod("getLong", String.class);
                        declaredMethod.setAccessible(true);
                        Long l2 = (Long) declaredMethod.invoke(null, "ro.build.version.oneui");
                        l2.longValue();
                        this.f14280a = l2;
                    } catch (Exception unused) {
                        this.f14280a = -1L;
                    }
                }
                return this.f14280a.longValue() >= 40100;
            }
        };
        f14276c = deviceSupportCondition2;
        HashMap hashMap = new HashMap();
        hashMap.put("fcnt", deviceSupportCondition);
        hashMap.put("google", deviceSupportCondition);
        hashMap.put("hmd global", deviceSupportCondition);
        hashMap.put("infinix", deviceSupportCondition);
        hashMap.put("infinix mobility limited", deviceSupportCondition);
        hashMap.put("itel", deviceSupportCondition);
        hashMap.put("kyocera", deviceSupportCondition);
        hashMap.put("lenovo", deviceSupportCondition);
        hashMap.put("lge", deviceSupportCondition);
        hashMap.put("meizu", deviceSupportCondition);
        hashMap.put("motorola", deviceSupportCondition);
        hashMap.put("nothing", deviceSupportCondition);
        hashMap.put("oneplus", deviceSupportCondition);
        hashMap.put("oppo", deviceSupportCondition);
        hashMap.put("realme", deviceSupportCondition);
        hashMap.put("robolectric", deviceSupportCondition);
        hashMap.put("samsung", deviceSupportCondition2);
        hashMap.put("sharp", deviceSupportCondition);
        hashMap.put("shift", deviceSupportCondition);
        hashMap.put("sony", deviceSupportCondition);
        hashMap.put("tcl", deviceSupportCondition);
        hashMap.put("tecno", deviceSupportCondition);
        hashMap.put("tecno mobile limited", deviceSupportCondition);
        hashMap.put("vivo", deviceSupportCondition);
        hashMap.put("wingtech", deviceSupportCondition);
        hashMap.put("xiaomi", deviceSupportCondition);
        f14277d = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("asus", deviceSupportCondition);
        hashMap2.put("jio", deviceSupportCondition);
        f14278e = Collections.unmodifiableMap(hashMap2);
        f14279f = DynamicColors.class.getSimpleName();
    }

    public static void a(Activity activity, DynamicColorsOptions dynamicColorsOptions) {
        if (d()) {
            int b2 = dynamicColorsOptions.a() == null ? dynamicColorsOptions.d() == 0 ? b(activity, f14274a) : dynamicColorsOptions.d() : 0;
            if (dynamicColorsOptions.c().a(activity, b2)) {
                if (dynamicColorsOptions.a() != null) {
                    SchemeContent schemeContent = new SchemeContent(Hct.b(dynamicColorsOptions.a().intValue()), !MaterialColors.j(activity), c(activity));
                    ColorResourcesOverride a2 = ColorResourcesOverride.a();
                    if (a2 == null || !a2.b(activity, MaterialColorUtilitiesHelper.a(schemeContent))) {
                        return;
                    }
                } else {
                    ThemeUtils.a(activity, b2);
                }
                dynamicColorsOptions.b().a(activity);
            }
        }
    }

    private static int b(Context context, int[] iArr) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private static float c(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager == null || Build.VERSION.SDK_INT < 34) {
            return 0.0f;
        }
        return uiModeManager.getContrast();
    }

    public static boolean d() {
        if (BuildCompat.a()) {
            return true;
        }
        Map map = f14277d;
        String str = Build.MANUFACTURER;
        Locale locale = Locale.ROOT;
        DeviceSupportCondition deviceSupportCondition = (DeviceSupportCondition) map.get(str.toLowerCase(locale));
        if (deviceSupportCondition == null) {
            deviceSupportCondition = (DeviceSupportCondition) f14278e.get(Build.BRAND.toLowerCase(locale));
        }
        return deviceSupportCondition != null && deviceSupportCondition.a();
    }
}
