package cn.nubia.hostassist;

import android.content.Context;
import android.provider.Settings;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class HostAssistUtils {
    public static int a(int i2) {
        return Math.max(1, (i2 + 2) / 3) * 120;
    }

    public static int b() {
        return d() + 336;
    }

    public static int c() {
        return 183;
    }

    public static int d() {
        return ZteFeature.isSupportHostFreeform() ? 48 : 0;
    }

    public static int e() {
        return d() + 353;
    }

    public static int f() {
        return d() + 256;
    }

    public static int g() {
        return ZteFeature.isSupportHostFreeform() ? 96 : 0;
    }

    public static int h() {
        return g() + 691;
    }

    public static boolean i(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "hasWindowReply", 0) > 0;
    }

    public static boolean j() {
        return Settings.Global.getInt(GameAssistApplication.j().getContentResolver(), "wrap_displayid", 0) > 0;
    }
}
