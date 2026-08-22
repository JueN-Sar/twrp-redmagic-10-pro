package cn.nubia.gameassist.meditationmode.danmu;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class BarrageFactory {

    /* renamed from: a, reason: collision with root package name */
    private static Context f6570a;

    /* renamed from: b, reason: collision with root package name */
    private static final Handler f6571b = new Handler(Looper.getMainLooper());

    public static Context a() {
        return f6570a;
    }

    public static Handler b() {
        return f6571b;
    }

    public static boolean c() {
        return f6570a.getResources().getConfiguration().orientation == 2;
    }

    public static void d(Context context) {
        f6570a = context;
    }
}
