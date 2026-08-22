package cn.nubia.screensaver.util;

import android.content.ComponentName;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import cn.nubia.screensaver.sensor.GSSensorController;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DefaultUtil {
    public static ArrayList a(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            String trim = str2.trim();
            if (!trim.isEmpty() && !"null".equals(trim) && !"".equals(trim)) {
                arrayList.add(trim);
            }
        }
        return arrayList;
    }

    public static boolean b() {
        ComponentName componentName;
        AbsGameAssistToken.ActivityEntity activityEntity = SystemMgr.f16556q;
        boolean z = false;
        if (activityEntity == null || (componentName = activityEntity.mActivity) == null) {
            return false;
        }
        String packageName = componentName.getPackageName();
        int i2 = activityEntity.mUserId;
        if ((i2 == 0 || i2 == 999) && GameCheck.h(packageName)) {
            z = true;
        }
        return !z ? "cn.nubia.gamelauncher".equals(packageName) : z;
    }

    public static boolean c(float[] fArr) {
        if (fArr == null || fArr.length < 3) {
            GaLog.e(GSSensorController.J, " values null =" + fArr);
            return false;
        }
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        if (f2 == 0.0f && f3 == 0.0f && f4 == 0.0f) {
            return false;
        }
        int round = (int) Math.round(Math.asin(f2 / ((float) Math.sqrt(((f2 * f2) + (f3 * f3)) + (f4 * f4)))) * 57.295780181884766d);
        boolean z = Math.abs(round) > 45;
        int round2 = (int) Math.round((-Math.atan2(-f2, f3)) * 57.295780181884766d);
        if (round2 < 0) {
            round2 += 360;
        }
        if (GaLog.f17035c) {
            GaLog.e(GSSensorController.J, " orientationAngle=" + round2 + " tiltAngle=" + round);
        }
        int i2 = (round2 + 45) / 90;
        if (i2 == 4) {
            i2 = 0;
        }
        return (i2 == 1 || i2 == 3) && z;
    }

    public static void d(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Display display = context.getDisplay();
        boolean z = false;
        if (display != null && display.getDisplayId() != 0) {
            z = true;
        }
        float f2 = displayMetrics.density;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (!z) {
            i2 = Math.max(i2, i3);
            i3 = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        float min = Math.min(i2 / 800.0f, i3 / 360.0f);
        float f3 = (displayMetrics.scaledDensity / f2) * min;
        displayMetrics.density = min;
        displayMetrics.scaledDensity = f3;
        displayMetrics.densityDpi = ((int) min) * 160;
    }
}
