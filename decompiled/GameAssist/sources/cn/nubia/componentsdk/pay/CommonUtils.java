package cn.nubia.componentsdk.pay;

import android.content.Context;
import cn.nubia.componentsdk.until.PayLog;

/* loaded from: classes.dex */
public class CommonUtils {

    /* renamed from: a, reason: collision with root package name */
    private static long f5928a;

    public static String a(Context context) {
        return "4";
    }

    public static boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - f5928a;
        PayLog.a("FastDoubleClick", "sLastClickTime = " + f5928a + "; time=" + f5928a);
        if (0 < j2 && j2 < 1000) {
            return true;
        }
        f5928a = currentTimeMillis;
        return false;
    }
}
