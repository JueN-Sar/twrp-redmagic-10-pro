package cn.nubia.hostassist;

import android.content.Context;
import android.util.DisplayMetrics;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class HostDensityHelper {

    /* renamed from: a, reason: collision with root package name */
    private static int f7807a = 3;

    private static void a(DisplayMetrics displayMetrics, float f2) {
        displayMetrics.density = f2;
        displayMetrics.scaledDensity = (displayMetrics.scaledDensity / f2) * f2;
        displayMetrics.densityDpi = (int) (f2 * 160.0f);
    }

    private static float b(DisplayMetrics displayMetrics) {
        float f2 = displayMetrics.density;
        float f3 = displayMetrics.scaledDensity;
        float f4 = f7807a;
        c("HostDensityHelper", f4, (f3 / f2) * f4, (int) (160.0f * f4));
        return f4;
    }

    private static void c(String str, float f2, float f3, int i2) {
        GaLog.a(str, "updateDensity(target) density : " + f2 + ", scale : " + f3 + ", dpi : " + i2);
    }

    public static void d(Context context) {
        if (context == null) {
            GaLog.a("HostDensityHelper", "updateDensity null");
        } else if (HostAssistMgr.n().f7770s > 1.0f && HostAssistMgr.y()) {
            GaLog.a("HostDensityHelper", "mScaleRatio > 1f");
        } else {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            a(displayMetrics, b(displayMetrics));
        }
    }

    public static void e(Context context) {
        if (context == null) {
            GaLog.a("HostDensityHelper", "updateDensity null");
        } else {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            a(displayMetrics, b(displayMetrics));
        }
    }
}
