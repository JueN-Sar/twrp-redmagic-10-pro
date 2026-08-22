package cn.nubia.multisubscreen.utils;

import android.os.PowerManager;
import android.os.SystemClock;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class LockScreenHelper {

    /* renamed from: c, reason: collision with root package name */
    private static volatile LockScreenHelper f8153c;

    /* renamed from: a, reason: collision with root package name */
    private PowerManager f8154a = (PowerManager) GameAssistApplication.j().getSystemService("power");

    /* renamed from: b, reason: collision with root package name */
    private PowerManager.WakeLock f8155b;

    private LockScreenHelper() {
    }

    public static LockScreenHelper a() {
        if (f8153c == null) {
            synchronized (LockScreenHelper.class) {
                try {
                    if (f8153c == null) {
                        f8153c = new LockScreenHelper();
                    }
                } finally {
                }
            }
        }
        return f8153c;
    }

    public void b() {
        if (this.f8155b == null) {
            PowerManager.WakeLock newWakeLock = this.f8154a.newWakeLock(268435482, "cn.nubia.gameassist");
            this.f8155b = newWakeLock;
            newWakeLock.acquire();
        }
    }

    public void c() {
        PowerManager.WakeLock wakeLock = this.f8155b;
        if (wakeLock != null) {
            wakeLock.release();
            this.f8155b = null;
        }
    }

    public void d() {
        if (this.f8154a.isInteractive()) {
            try {
                this.f8154a.goToSleep(SystemClock.uptimeMillis());
            } catch (Exception e2) {
                GaLog.b("MultiSubScreen_LockScreenHelper", "screenOff e = " + e2.toString());
            }
        }
    }

    public void e() {
        GaLog.b("MultiSubScreen_LockScreenHelper", "screenOn mPowerManager.isInteractive() = " + this.f8154a.isInteractive());
        if (this.f8154a.isInteractive()) {
            return;
        }
        this.f8154a.newWakeLock(268435482, "cn.nubia.gameassist").acquire(1000L);
    }
}
