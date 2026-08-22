package cn.nubia.screensaver.power;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import cn.nubia.screensaver.GameScreensaverManager;
import cn.nubia.screensaver.common.ActionEvent;
import cn.nubia.screensaver.common.IController;
import cn.nubia.screensaver.common.ScreensaverToken;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.system.GSSystemController;
import cn.nubia.screensaver.system.ISystemPower;
import cn.nubia.screensaver.util.DefaultUtil;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.ext.system.PowerStateMonitorProxy;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GSPowerController implements IController {
    private PowerManager B;
    private boolean C;
    private boolean D;
    private boolean E;
    private ISystemPower F;

    /* renamed from: h, reason: collision with root package name */
    private final GameScreensaverManager f9073h;

    /* renamed from: i, reason: collision with root package name */
    private final Context f9074i;

    /* renamed from: j, reason: collision with root package name */
    private final ActionEvent f9075j;

    /* renamed from: k, reason: collision with root package name */
    private DozeWakefulnessData f9076k;

    /* renamed from: l, reason: collision with root package name */
    private GlobalWakefulnessData f9077l;

    /* renamed from: m, reason: collision with root package name */
    private Handler f9078m;

    /* renamed from: n, reason: collision with root package name */
    private int f9079n;

    /* renamed from: o, reason: collision with root package name */
    private int f9080o;

    /* renamed from: p, reason: collision with root package name */
    private String f9081p;

    /* renamed from: q, reason: collision with root package name */
    private long f9082q;

    /* renamed from: r, reason: collision with root package name */
    private String f9083r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f9084s;
    private boolean t;
    private final PowerManager.WakeLock v;
    private final PowerManager.WakeLock w;
    private boolean x;
    private boolean y;

    /* renamed from: c, reason: collision with root package name */
    private final List f9072c = new ArrayList();
    private final Object u = new Object();
    private Runnable z = new Runnable() { // from class: cn.nubia.screensaver.power.g
        @Override // java.lang.Runnable
        public final void run() {
            GSPowerController.this.M();
        }
    };
    private Runnable A = new Runnable() { // from class: cn.nubia.screensaver.power.h
        @Override // java.lang.Runnable
        public final void run() {
            GSPowerController.this.L();
        }
    };

    protected static class DozeWakefulnessData {

        /* renamed from: a, reason: collision with root package name */
        final boolean f9085a;

        /* renamed from: b, reason: collision with root package name */
        final int f9086b;

        /* renamed from: c, reason: collision with root package name */
        final int f9087c;

        /* renamed from: d, reason: collision with root package name */
        final long f9088d;

        public DozeWakefulnessData(boolean z, int i2, int i3, long j2) {
            this.f9085a = z;
            this.f9086b = i2;
            this.f9087c = i3;
            this.f9088d = j2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DozeWakefulnessData dozeWakefulnessData = (DozeWakefulnessData) obj;
            return this.f9085a == dozeWakefulnessData.f9085a && this.f9086b == dozeWakefulnessData.f9086b && this.f9087c == dozeWakefulnessData.f9087c;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(this.f9085a), Integer.valueOf(this.f9086b), Integer.valueOf(this.f9087c));
        }

        public String toString() {
            return "mISdoze=" + this.f9085a + "， mGroundId=" + this.f9086b + ", mWakefulness=" + this.f9087c;
        }
    }

    private static class GlobalWakefulnessData {

        /* renamed from: a, reason: collision with root package name */
        final int f9089a;

        /* renamed from: b, reason: collision with root package name */
        final long f9090b;

        /* renamed from: c, reason: collision with root package name */
        final int f9091c;

        /* renamed from: d, reason: collision with root package name */
        final int f9092d;

        /* renamed from: e, reason: collision with root package name */
        final String f9093e;

        /* renamed from: f, reason: collision with root package name */
        final String f9094f;

        /* renamed from: g, reason: collision with root package name */
        final long f9095g;

        public GlobalWakefulnessData(int i2, long j2, int i3, int i4, String str, String str2, long j3) {
            this.f9089a = i2;
            this.f9090b = j2;
            this.f9091c = i3;
            this.f9092d = i4;
            this.f9093e = str;
            this.f9094f = str2;
            this.f9095g = j3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GlobalWakefulnessData globalWakefulnessData = (GlobalWakefulnessData) obj;
            return this.f9089a == globalWakefulnessData.f9089a && this.f9090b == globalWakefulnessData.f9090b && this.f9091c == globalWakefulnessData.f9091c && this.f9092d == globalWakefulnessData.f9092d && Objects.equals(this.f9093e, globalWakefulnessData.f9093e) && Objects.equals(this.f9094f, globalWakefulnessData.f9094f);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.f9089a), Long.valueOf(this.f9090b), Integer.valueOf(this.f9091c), Integer.valueOf(this.f9092d), this.f9093e, this.f9094f);
        }

        public String toString() {
            return "mWakefulness=" + this.f9089a + "， mEventTime=" + this.f9090b + ", mUid=" + this.f9091c + ", mPackageName=" + this.f9093e + ", mDetails=" + this.f9094f;
        }
    }

    public interface PowerCallback {
        default void b(boolean z) {
        }

        default void e(int i2, String str) {
        }

        default void g(int i2) {
        }

        default void h(int i2, String str) {
        }

        default void l(int i2) {
        }
    }

    public GSPowerController(GameScreensaverManager gameScreensaverManager) {
        this.f9073h = gameScreensaverManager;
        Context H = gameScreensaverManager.H();
        this.f9074i = H;
        this.f9078m = gameScreensaverManager.C();
        this.f9075j = gameScreensaverManager.G();
        PowerManager powerManager = (PowerManager) H.getSystemService(PowerManager.class);
        this.B = powerManager;
        this.v = powerManager.newWakeLock(1, "GameScreensaver.PowerPartial");
        this.w = this.B.newWakeLock(128, "GameScreensaver.PowerDraw");
        gameScreensaverManager.M().e("updateDoze", new ScreensaverToken.SystemCallback() { // from class: cn.nubia.screensaver.power.i
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GSPowerController.this.I(bundle);
            }
        });
        gameScreensaverManager.M().e("updateWakefulness", new ScreensaverToken.SystemCallback() { // from class: cn.nubia.screensaver.power.j
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GSPowerController.this.K(bundle);
            }
        });
        gameScreensaverManager.M().e("onConnected", new ScreensaverToken.SystemCallback() { // from class: cn.nubia.screensaver.power.k
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GSPowerController.this.H(bundle);
            }
        });
        this.t = this.B.isInteractive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(PowerCallback powerCallback) {
        powerCallback.b(this.f9084s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void B(StringBuilder sb, String str) {
        sb.append(" " + str + " ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(PowerCallback powerCallback) {
        if (this.f9072c.contains(powerCallback)) {
            this.f9072c.remove(powerCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("updateWakefulness");
        if (bundle2 != null) {
            K(bundle2);
        }
        Bundle bundle3 = bundle.getBundle("dozeState");
        if (bundle3 != null) {
            J(bundle3, true);
        }
        GaLog.e("GameScreensaver.Power", "onServiceInit wakefulness=" + bundle2 + " dozeState=" + bundle3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(Bundle bundle) {
        J(bundle, false);
    }

    private void J(Bundle bundle, boolean z) {
        boolean z2 = bundle.getBoolean(PowerStateMonitorProxy.POWER_STATE_DOZE, false);
        int i2 = bundle.getInt("groupId", 0);
        int i3 = bundle.getInt("wakefulness", 0);
        DozeWakefulnessData dozeWakefulnessData = new DozeWakefulnessData(z2, i2, i3, bundle.getLong("time", SystemClock.elapsedRealtime()));
        if (dozeWakefulnessData.equals(this.f9076k)) {
            return;
        }
        this.f9076k = dozeWakefulnessData;
        if (!z && i3 == 0 && this.t) {
            P(false, 0, this.f9074i.getPackageName(), "check");
        }
        if (!z && i3 == 1 && !this.t) {
            P(true, 2, this.f9074i.getPackageName(), "check");
        }
        if (this.f9084s != z2) {
            this.f9084s = z2;
            this.f9072c.forEach(new Consumer() { // from class: cn.nubia.screensaver.power.o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GSPowerController.this.A((GSPowerController.PowerCallback) obj);
                }
            });
            final StringBuilder sb = new StringBuilder();
            if (this.f9084s) {
                this.f9075j.g(32);
                if (this.D) {
                    bundle.keySet().forEach(new Consumer() { // from class: cn.nubia.screensaver.power.b
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            GSPowerController.B(sb, (String) obj);
                        }
                    });
                    if (ZteFeature.isTabletProduct() && this.E) {
                        if (this.f9073h.e0(sb, true, true)) {
                            r();
                            this.f9078m.postDelayed(new Runnable() { // from class: cn.nubia.screensaver.power.c
                                @Override // java.lang.Runnable
                                public final void run() {
                                    GSPowerController.this.R();
                                }
                            }, 500L);
                        }
                    } else if (!ZteFeature.isTabletProduct()) {
                        Handler handler = this.f9078m;
                        final GameScreensaverManager gameScreensaverManager = this.f9073h;
                        Objects.requireNonNull(gameScreensaverManager);
                        handler.postDelayed(new Runnable() { // from class: cn.nubia.screensaver.power.d
                            @Override // java.lang.Runnable
                            public final void run() {
                                GameScreensaverManager.this.f0();
                            }
                        }, 100L);
                    }
                }
            }
            GaLog.e("GameScreensaver.Power", "updateDoze doze=" + z2 + " wakefulness=" + i3 + " dozeVerification=" + this.D + " other=" + ((Object) sb));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(Bundle bundle) {
        int i2 = bundle.getInt("wakefulness");
        long j2 = bundle.getLong("eventTime");
        final int i3 = bundle.getInt("uid");
        final int i4 = bundle.getInt("reason");
        String string = bundle.getString("opPackageName");
        String string2 = bundle.getString("details");
        long j3 = bundle.getLong("time", SystemClock.elapsedRealtime());
        GlobalWakefulnessData globalWakefulnessData = new GlobalWakefulnessData(i2, j2, i3, i4, string, string2, j3);
        if (globalWakefulnessData.equals(this.f9077l)) {
            return;
        }
        this.f9077l = globalWakefulnessData;
        GaLog.e("GameScreensaver.Power", "updateWakefulness wakefulness=" + PowerReason.c(i2) + " eventTime=" + j2 + " uid=" + i3 + " details=" + string2 + " reason=" + i4 + " packageName=" + string);
        if (i2 == 0) {
            GaLog.e("GameScreensaver.Power", "---reallyGoToSleep--- reason=" + i4);
            this.f9072c.forEach(new Consumer() { // from class: cn.nubia.screensaver.power.e
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((GSPowerController.PowerCallback) obj).g(i4);
                }
            });
            return;
        }
        if (i2 == 1) {
            this.f9079n = i4;
            this.f9083r = string2;
            P(true, i4, string, string2);
            M();
            O(false);
            return;
        }
        if (i2 == 2) {
            GaLog.e("GameScreensaver.Power", "---nap time up--- uid=" + i3);
            this.f9072c.forEach(new Consumer() { // from class: cn.nubia.screensaver.power.f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((GSPowerController.PowerCallback) obj).l(i3);
                }
            });
            return;
        }
        if (i2 != 3) {
            GaLog.e("GameScreensaver.Power", "---unknown--- wakefulness");
            return;
        }
        this.f9081p = string2;
        this.f9082q = j3;
        this.f9080o = i4;
        this.E = DefaultUtil.c(bundle.getFloatArray("accelerometer_data"));
        this.D = (this.f9073h.N() || this.f9080o != 4 || this.f9073h.R()) ? false : true;
        P(false, i4, string, string2);
    }

    private void P(boolean z, final int i2, final String str, final String str2) {
        if (this.t != z) {
            this.t = z;
            if (!z) {
                GaLog.e("GameScreensaver.Power", "---goToSleep--- reason=" + PowerReason.a(i2) + " details=" + str2);
                this.f9072c.forEach(new Consumer() { // from class: cn.nubia.screensaver.power.n
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GSPowerController.PowerCallback) obj).e(i2, str2);
                    }
                });
                this.f9075j.g(4);
                return;
            }
            GaLog.e("GameScreensaver.Power", "---waking up--- reason=" + PowerReason.b(i2) + " packageName=" + str);
            this.f9072c.forEach(new Consumer() { // from class: cn.nubia.screensaver.power.m
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((GSPowerController.PowerCallback) obj).h(i2, str);
                }
            });
            this.f9073h.o0(PowerStateMonitorProxy.POWER_STATE_WAKEUP);
            this.f9075j.g(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(PowerCallback powerCallback) {
        if (this.f9072c.contains(powerCallback)) {
            return;
        }
        this.f9072c.add(powerCallback);
    }

    public void L() {
        synchronized (this.u) {
            try {
                this.f9078m.removeCallbacks(this.A);
                if (this.y) {
                    this.y = false;
                    this.w.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void M() {
        synchronized (this.u) {
            try {
                this.f9078m.removeCallbacks(this.z);
                if (this.x) {
                    this.x = false;
                    this.v.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void N(final PowerCallback powerCallback) {
        this.f9078m.post(new Runnable() { // from class: cn.nubia.screensaver.power.a
            @Override // java.lang.Runnable
            public final void run() {
                GSPowerController.this.E(powerCallback);
            }
        });
    }

    public void O(boolean z) {
        if (this.C == z || this.F == null) {
            return;
        }
        this.C = z;
        Settings.Global.putInt(this.f9074i.getContentResolver(), "set_screen_aod_from_gamescreensaver", z ? 1 : 0);
        this.F.a(z);
        GaLog.e("GameScreensaver.Power", "setAodEnable aod=" + z);
    }

    public void Q() {
        ISystemPower iSystemPower;
        if (!y() || (iSystemPower = this.F) == null) {
            return;
        }
        iSystemPower.userActivity();
    }

    public void R() {
        if (y() || this.F == null) {
            return;
        }
        P(true, 2, this.f9074i.getPackageName(), "");
        this.F.b();
    }

    @Override // cn.nubia.screensaver.common.IController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "GameScreensaver.Power");
        String str2 = str + "  ";
        printWriter.println(str2 + "mWakeupReason=" + PowerReason.b(this.f9079n));
        printWriter.println(str2 + "mWakeupDetails=" + this.f9083r);
        printWriter.println(str2 + "mGotoSleepReason=" + PowerReason.a(this.f9080o));
        printWriter.println(str2 + "mGotoSleepDetails=" + this.f9081p);
        printWriter.println(str2 + "mIsDoze=" + this.f9084s);
        printWriter.println(str2 + "mIsWakeup=" + this.t);
        printWriter.println(str2 + "mLastDozeWakefulnessData=" + this.f9076k);
        printWriter.println(str2 + "mLastGlobalWakefulnessData=" + this.f9077l);
    }

    @Override // cn.nubia.screensaver.common.IController
    public void f() {
        this.F = ((GSSystemController) this.f9073h.I(GSSystemController.class)).h();
    }

    public void p() {
        q(500L);
    }

    public void q(long j2) {
        synchronized (this.u) {
            try {
                this.f9078m.removeCallbacks(this.A);
                if (!this.y) {
                    this.y = true;
                    this.w.acquire();
                }
                this.f9078m.postDelayed(this.A, j2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void r() {
        s(500L);
    }

    public void s(long j2) {
        synchronized (this.u) {
            try {
                this.f9078m.removeCallbacks(this.z);
                if (!this.x) {
                    this.x = true;
                    this.v.acquire();
                }
                this.f9078m.postDelayed(this.z, j2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void t(final PowerCallback powerCallback) {
        this.f9078m.post(new Runnable() { // from class: cn.nubia.screensaver.power.l
            @Override // java.lang.Runnable
            public final void run() {
                GSPowerController.this.z(powerCallback);
            }
        });
    }

    public boolean u() {
        return this.f9084s && this.D;
    }

    public long v() {
        if (this.f9084s) {
            return SystemClock.elapsedRealtime() - this.f9076k.f9088d;
        }
        return -1L;
    }

    public boolean w() {
        return this.f9084s && !y();
    }

    public boolean x() {
        return this.C;
    }

    public boolean y() {
        return this.t;
    }
}
