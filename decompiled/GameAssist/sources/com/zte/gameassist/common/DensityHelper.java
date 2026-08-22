package com.zte.gameassist.common;

import android.content.Context;
import android.net.Uri;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;

/* loaded from: classes2.dex */
public class DensityHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final String f16474a = SystemProperties.get("ro.sf.lcd_density", "");

    /* renamed from: b, reason: collision with root package name */
    private static float f16475b = 3.0f;

    public static String a() {
        return b(ContextWrapper.getContext().getResources().getDisplayMetrics());
    }

    public static String b(DisplayMetrics displayMetrics) {
        int c2 = InflaterHelper.c(displayMetrics);
        float f2 = c2 / 160.0f;
        String str = c2 <= 120 ? "ldpi" : (120 >= c2 || c2 > 160) ? (160 >= c2 || c2 > 240) ? (240 >= c2 || c2 > 320) ? (320 >= c2 || c2 > 480) ? (480 >= c2 || c2 > 640) ? "nodpi" : "xxxhdpi" : "xxhdpi" : "xhdpi" : "hdpi" : "mdpi";
        StringBuilder sb = new StringBuilder();
        sb.append("sw");
        int i2 = displayMetrics.heightPixels;
        int i3 = displayMetrics.widthPixels;
        if (i2 >= i3) {
            i2 = i3;
        }
        sb.append((int) (i2 / f2));
        sb.append("dp");
        return sb.toString() + "-" + str + "-" + (((int) (displayMetrics.widthPixels / f2)) + "dpx" + ((int) (displayMetrics.heightPixels / f2)) + "dp") + "-" + (displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
    }

    public static void c(final Context context) {
        try {
            f16475b = Integer.parseInt(f16474a) / 160.0f;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ObserverManager.c().b(context, Settings.Secure.getUriFor("display_density_forced"), new ObserverManager.SettingCallback() { // from class: com.zte.gameassist.common.DensityHelper.1
            @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
            public void w(boolean z, Uri uri) {
                GaLog.e("DensityHelper", "onChange() -> display_density_forced");
                DensityHelper.d(context);
            }
        });
        d(context);
    }

    public static void d(Context context) {
        if (context == null || TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "display_density_forced"))) {
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f2 = displayMetrics.density;
        float f3 = displayMetrics.scaledDensity;
        GaLog.a("DensityHelper", "updateDensity(current) density : " + f2 + ", scale : " + f3 + ", dpi : " + displayMetrics.densityDpi);
        float f4 = f16475b;
        float f5 = (f3 / f2) * f4;
        int i2 = ((int) f4) * 160;
        GaLog.a("DensityHelper", "updateDensity(target) density : " + f4 + ", scale : " + f5 + ", dpi : " + i2);
        displayMetrics.density = f4;
        displayMetrics.scaledDensity = f5;
        displayMetrics.densityDpi = i2;
    }
}
