package cn.nubia.hostassist;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.Display;
import android.widget.Toast;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController;
import cn.nubia.plugin.gameshader.ShaderUtils;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ActivityManagerWrapper;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.DisplayWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class HostAssistMgr implements DumpController.Dump, HostMonitor {
    private static volatile HostAssistMgr A = null;
    public static int B = 2400;
    public static int C = 1080;
    public static boolean D = true;
    private static boolean E = false;
    public static boolean F = false;
    private static int y = 2027;

    /* renamed from: o, reason: collision with root package name */
    private HostAssistWindow f7766o;

    /* renamed from: p, reason: collision with root package name */
    private HostFreeformWindow f7767p;
    private static Object z = new Object();
    private static List G = Arrays.asList("cn.nubia.gameassist", "com.tencent.tmgp.sgame", "com.tencent.jkchess", "com.miHoYo.Yuanshen", "com.tencent.tmgp.cod", "cn.nubia.gamelauncher", "com.tencent.tmgp.supercell.clashofclans", "com.netease.mrzh", "com.netease.party", "com.netease.moba", "com.tencent.lolm");

    /* renamed from: c, reason: collision with root package name */
    public Context f7758c = null;

    /* renamed from: h, reason: collision with root package name */
    public Context f7759h = null;

    /* renamed from: i, reason: collision with root package name */
    private Handler f7760i = new Handler(Looper.getMainLooper());

    /* renamed from: j, reason: collision with root package name */
    private Display f7761j = null;

    /* renamed from: k, reason: collision with root package name */
    private DisplayManager f7762k = null;

    /* renamed from: l, reason: collision with root package name */
    private ContentObserver f7763l = null;

    /* renamed from: m, reason: collision with root package name */
    private ComponentCallbacks f7764m = null;

    /* renamed from: n, reason: collision with root package name */
    private Rect f7765n = new Rect();

    /* renamed from: q, reason: collision with root package name */
    private boolean f7768q = false;

    /* renamed from: r, reason: collision with root package name */
    private boolean f7769r = false;

    /* renamed from: s, reason: collision with root package name */
    public float f7770s = 1.0f;
    private boolean t = false;
    private boolean u = false;
    private Runnable v = new Runnable() { // from class: cn.nubia.hostassist.HostAssistMgr.3
        @Override // java.lang.Runnable
        public void run() {
            if (HostAssistMgr.this.f7766o != null) {
                GaLog.e("HostAssistMgr", "mCloseRunnable");
                HostAssistMgr.this.f7766o.m();
                HostAssistMgr.this.f7766o = null;
            }
        }
    };
    private ContentObserver w = new ContentObserver(this.f7760i) { // from class: cn.nubia.hostassist.HostAssistMgr.6
        @Override // android.database.ContentObserver
        public void onChange(boolean z2, Uri uri) {
            super.onChange(z2);
            if (Settings.Global.getUriFor("app_mirror_list").equals(uri)) {
                HostAssistMgr.this.v();
            } else if (Settings.Global.getUriFor("app_mirror_displayid").equals(uri)) {
                HostAssistMgr.this.w();
            }
        }
    };
    private final Runnable x = new Runnable() { // from class: cn.nubia.hostassist.HostAssistMgr.8
        @Override // java.lang.Runnable
        public void run() {
            HostAssistMgr.this.u = false;
        }
    };

    private HostAssistMgr(Context context) {
        x(context);
    }

    private boolean A() {
        for (Display display : this.f7762k.getDisplays()) {
            int type = DisplayWrapper.getType(display);
            String uniqueId = DisplayWrapper.getUniqueId(display);
            if (type == 5 && uniqueId != null && uniqueId.contains("com.zte.multscr")) {
                return true;
            }
        }
        return false;
    }

    private void J(float f2, String str) {
        if (f2 > 2.5f) {
            if (G.contains(str)) {
                GaLog.e("HostAssistMgr", "handleMirrorAppChange wide whitle app=" + str);
                return;
            }
            GaLog.e("HostAssistMgr", "handleMirrorAppChange not wide whitle app=" + str + " mWideToast=" + this.u);
            this.f7760i.postDelayed(this.x, 3000L);
            if (this.u) {
                return;
            }
            this.u = true;
            Toast.makeText(this.f7759h, this.f7758c.getString(R.string.host_mode_not_adapt_wide_screen_toast), 1).show();
        }
    }

    private void K(int i2) {
        if (this.f7768q) {
            O();
        }
        this.f7768q = true;
        if (i2 <= 0) {
            GaLog.e("HostAssistMgr", "not gamebox mirror mode");
            return;
        }
        GaLog.e("HostAssistMgr", "start displayId=" + i2);
        Q(i2);
        L();
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.m();
            this.f7766o = null;
        }
        this.f7766o = new HostAssistWindow(this.f7759h, i2);
        this.f7760i.removeCallbacks(this.v);
        this.f7766o.A(this.f7765n, D);
        this.f7760i.postDelayed(new Runnable() { // from class: cn.nubia.hostassist.HostAssistMgr.1
            @Override // java.lang.Runnable
            public void run() {
                HostAssistMgr.this.H();
            }
        }, 100L);
        boolean y2 = y();
        if (y2) {
            this.f7760i.postDelayed(new Runnable() { // from class: cn.nubia.hostassist.HostAssistMgr.2
                @Override // java.lang.Runnable
                public void run() {
                    HostAssistMgr.this.k();
                }
            }, 600L);
        }
        PerformanceMonitorController.getInstance(this.f7758c).onHostStart(this.f7759h);
        onHostStart(y2);
    }

    private void L() {
        ComponentCallbacks componentCallbacks = new ComponentCallbacks() { // from class: cn.nubia.hostassist.HostAssistMgr.4
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
                GaLog.e("HostAssistMgr", "newConfig=" + configuration);
                HostAssistMgr.this.C(configuration);
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }
        };
        this.f7764m = componentCallbacks;
        this.f7759h.registerComponentCallbacks(componentCallbacks);
    }

    private void O() {
        if (!this.f7768q) {
            GaLog.k("HostAssistMgr", "not start");
            return;
        }
        P();
        this.f7760i.postDelayed(this.v, 200L);
        PerformanceMonitorController.getInstance(this.f7758c).onHostStop();
        this.f7768q = false;
        E(false);
        onHostStop();
    }

    private void P() {
        ComponentCallbacks componentCallbacks = this.f7764m;
        if (componentCallbacks != null) {
            this.f7759h.unregisterComponentCallbacks(componentCallbacks);
            this.f7764m = null;
        }
    }

    private void Q(int i2) {
        Display display = this.f7762k.getDisplay(i2);
        this.f7761j = display;
        this.f7759h = ContextWrapper.createWindowContext(this.f7758c.createDisplayContext(display), y);
        if (ZteFeature.isTabletProduct() && y()) {
            this.f7770s = 1.66f;
            Configuration configuration = this.f7759h.getResources().getConfiguration();
            configuration.densityDpi *= 2;
            this.f7759h = this.f7759h.createConfigurationContext(configuration);
        } else {
            this.f7770s = 1.0f;
        }
        HostDensityHelper.d(this.f7759h);
        R();
    }

    private void R() {
        Point point = new Point();
        this.f7761j.getRealSize(point);
        int i2 = point.x;
        B = i2;
        int i3 = point.y;
        C = i3;
        if (i2 > i3) {
            D = true;
            this.f7765n.set(0, i3 - ((int) (this.f7770s * 55.0f)), (int) (HostAssistUtils.e() * this.f7770s), C);
        } else {
            D = false;
            this.f7765n.set(0, i3 - ((int) (this.f7770s * 95.0f)), (int) (HostAssistUtils.h() * this.f7770s), C);
        }
        PerformanceMonitorController.getInstance(this.f7758c).updateHostScreenSize(B, C);
        GaLog.e("HostAssistMgr", "w= " + B + " h= " + C + " " + this.f7765n);
    }

    public static HostAssistMgr n() {
        if (A == null) {
            synchronized (z) {
                try {
                    if (A == null) {
                        A = new HostAssistMgr(GameAssistApplication.j());
                    }
                } finally {
                }
            }
        }
        return A;
    }

    private int r() {
        return Settings.Global.getInt(this.f7758c.getContentResolver(), "nubia_systemui_wifidisplay_status", 0);
    }

    private int s() {
        return Settings.Global.getInt(this.f7758c.getContentResolver(), "tp_type_for_games", 0);
    }

    private void u(boolean z2) {
        if (z2 != E) {
            E = z2;
            GaLog.e("HostAssistMgr", "handleGameSpaceChange isGameSpace= " + z2);
            if (z2) {
                this.f7760i.postDelayed(new Runnable() { // from class: cn.nubia.hostassist.HostAssistMgr.7
                    @Override // java.lang.Runnable
                    public void run() {
                        GaLog.e("HostAssistMgr", "isGameSpace -> showGameSpacePanel DisPlayId = " + HostAssistMgr.this.q());
                        HostAssistMgr.this.G();
                    }
                }, 300L);
            } else {
                GaLog.e("HostAssistMgr", "isGameSpace -> closePanel");
                k();
            }
        }
        PerformanceMonitorController.getInstance(this.f7758c).updateHostScreenSize(B, C);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        j("appchange");
        float f2 = B / C;
        String o2 = o();
        GaLog.e("HostAssistMgr", "handleMirrorAppChange pkgName=" + o2 + " screenRadio=" + f2);
        boolean equals = "cn.nubia.gamelauncher".equals(o2);
        PerformanceMonitorController.getInstance(this.f7758c).onHostChange(equals ^ true, o2);
        if (!"com.tencent.tmgp.sgame".equals(o2) || f2 <= 2.5f) {
            E(false);
        } else {
            E(true);
        }
        J(f2, o2);
        u(equals);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
    
        if (r2 != 5) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void w() {
        /*
            r7 = this;
            int r0 = r7.q()
            int r1 = r7.r()
            int r2 = r7.s()
            boolean r3 = r7.A()
            if (r1 == 0) goto L1f
            r4 = 1
            if (r2 == r4) goto L20
            r5 = 2
            if (r2 == r5) goto L20
            r5 = 3
            if (r2 == r5) goto L20
            r5 = 5
            if (r2 != r5) goto L1f
            goto L20
        L1f:
            r4 = 0
        L20:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "handleMirrorDisplayIdChange displayId="
            r5.append(r6)
            r5.append(r0)
            java.lang.String r6 = " tp_status="
            r5.append(r6)
            r5.append(r1)
            java.lang.String r1 = " tp_type="
            r5.append(r1)
            r5.append(r2)
            java.lang.String r1 = " mIsGameSpace="
            r5.append(r1)
            boolean r1 = cn.nubia.hostassist.HostAssistMgr.E
            r5.append(r1)
            java.lang.String r1 = " isPCtp="
            r5.append(r1)
            r5.append(r4)
            java.lang.String r1 = " isMullscr="
            r5.append(r1)
            r5.append(r3)
            java.lang.String r1 = r5.toString()
            java.lang.String r2 = "HostAssistMgr"
            com.zte.gameassist.utils.GaLog.e(r2, r1)
            if (r0 <= 0) goto L6e
            if (r4 == 0) goto L68
            boolean r1 = cn.nubia.hostassist.HostAssistMgr.E
            if (r1 == 0) goto L6e
        L68:
            if (r3 != 0) goto L6e
            r7.K(r0)
            goto L71
        L6e:
            r7.O()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.hostassist.HostAssistMgr.w():void");
    }

    private void x(Context context) {
        this.f7758c = context;
        this.f7762k = (DisplayManager) context.getSystemService("display");
        this.f7758c.getContentResolver().registerContentObserver(Settings.Global.getUriFor("app_mirror_list"), false, this.w);
        this.f7758c.getContentResolver().registerContentObserver(Settings.Global.getUriFor("app_mirror_displayid"), false, this.w);
    }

    public static boolean y() {
        return Settings.Global.getInt(GameAssistApplication.j().getContentResolver(), "gamebox_mirror_displayid", 0) <= 0;
    }

    public static boolean z() {
        return E;
    }

    public boolean B() {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            return hostAssistWindow.w();
        }
        return false;
    }

    public void C(final Configuration configuration) {
        HostDensityHelper.d(this.f7759h);
        HostRotationHelper.a(this.f7761j);
        R();
        this.f7760i.post(new Runnable() { // from class: cn.nubia.hostassist.HostAssistMgr.5
            @Override // java.lang.Runnable
            public void run() {
                if (HostAssistMgr.this.f7766o != null) {
                    HostAssistMgr.this.f7760i.removeCallbacks(HostAssistMgr.this.v);
                    HostAssistMgr.this.f7766o.A(HostAssistMgr.this.f7765n, HostAssistMgr.D);
                }
                PerformanceMonitorController.getInstance(HostAssistMgr.this.f7758c).onConfigurationChanged(configuration, true);
            }
        });
    }

    public void D(int i2) {
        GaLog.a("HostAssistMgr", "sendKeyBack");
        try {
            Class cls = Integer.TYPE;
            DisplayManager.class.getMethod("setCmdToDisplay", cls, cls, cls, Bundle.class).invoke(this.f7762k, 15, 0, 0, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void E(boolean z2) {
        boolean z3 = this.t;
        if (!z3 && z2) {
            ShaderUtils.i("com.tencent.tmgp.sgame", true);
            this.t = true;
        } else {
            if (!z3 || z2) {
                return;
            }
            ShaderUtils.i("com.tencent.tmgp.sgame", false);
            this.t = false;
        }
    }

    public void F() {
        if (this.f7767p == null) {
            this.f7767p = new HostFreeformWindow(this.f7759h);
        }
        HostFreeformWindow hostFreeformWindow = this.f7767p;
        if (hostFreeformWindow != null) {
            hostFreeformWindow.h(D, this.f7765n);
        }
    }

    public void G() {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.B();
        }
    }

    public void H() {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.D();
        }
    }

    public void I(boolean z2) {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.E(z2);
        }
    }

    public void M(Intent intent) {
        if (this.f7759h == null) {
            GaLog.b("HostAssistMgr", "startWindowFreeForm mMirrorContext null");
        } else {
            GaLog.a("HostAssistMgr", "startWindowFreeForm ");
            ActivityManagerWrapper.startWindowFreeForm(intent, this.f7759h, SystemMgr.w(), 0);
        }
    }

    public void N(String str) {
        M(this.f7758c.getPackageManager().getLaunchIntentForPackage(str));
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("HostAssistMgr:");
        printWriter.println("  DisplayId=" + q());
        printWriter.println("  mIsGameSpace=" + E);
        printWriter.println("  mIsHorizontal=" + D);
        printWriter.println("  mHoverArea=" + this.f7765n);
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.q(fileDescriptor, printWriter, strArr);
        }
    }

    public void j(String str) {
        HostFreeformWindow hostFreeformWindow = this.f7767p;
        if (hostFreeformWindow != null) {
            hostFreeformWindow.b(str);
            this.f7767p = null;
        }
    }

    public void k() {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.n();
        }
    }

    public void l(boolean z2) {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.o(z2);
        }
    }

    public void m() {
        HostAssistWindow hostAssistWindow = this.f7766o;
        if (hostAssistWindow != null) {
            hostAssistWindow.p();
        }
    }

    public String o() {
        String[] split = Settings.Global.getString(this.f7758c.getContentResolver(), "app_mirror_list").split("/");
        return split.length > 0 ? split[0] : "";
    }

    public Context p() {
        return this.f7759h;
    }

    public int q() {
        return Settings.Global.getInt(this.f7758c.getContentResolver(), "app_mirror_displayid", 0);
    }

    public void t() {
        try {
            GaLog.a("HostAssistMgr", "goHome");
            Class cls = Integer.TYPE;
            DisplayManager.class.getMethod("setCmdToDisplay", cls, cls, cls, Bundle.class).invoke(this.f7762k, 6, 0, 0, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
