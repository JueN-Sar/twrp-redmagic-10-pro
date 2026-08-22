package cn.nubia.hostassist;

import android.view.Display;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class HostRotationHelper {

    /* renamed from: a, reason: collision with root package name */
    private static int f7833a;

    public static synchronized void a(Display display) {
        synchronized (HostRotationHelper.class) {
            int rotation = display.getRotation();
            if (rotation != f7833a) {
                f7833a = rotation;
                GaLog.e("HostRotationHelper", "updateOrientation new rotation = " + f7833a);
            }
        }
    }
}
