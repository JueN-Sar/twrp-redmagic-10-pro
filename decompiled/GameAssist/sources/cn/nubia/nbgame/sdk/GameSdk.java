package cn.nubia.nbgame.sdk;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class GameSdk {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("NEOGAME_FCM_ACTION");
        context.sendBroadcast(intent);
    }

    public static String b() {
        return GameInnerSdk.j().i();
    }

    public static void c(Context context) {
        GameInnerSdk.j().v(context);
    }

    public static void d(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("cp.current.status");
        intent.putExtra("CP_STATUS", "CP_IS_BACKGROUND_STATUS");
        context.sendBroadcast(intent);
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("cp.current.status");
        intent.putExtra("CP_STATUS", "CP_IS_FOREGROUND_STATUS");
        intent.putExtra("package", context.getPackageName());
        context.sendBroadcast(intent);
    }
}
