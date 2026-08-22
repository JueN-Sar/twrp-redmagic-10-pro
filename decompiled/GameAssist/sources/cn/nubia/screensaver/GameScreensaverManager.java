package cn.nubia.screensaver;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.GSWindowController;
import cn.nubia.screensaver.common.ActionEvent;
import cn.nubia.screensaver.common.CallStateCallback;
import cn.nubia.screensaver.common.IController;
import cn.nubia.screensaver.common.ScreensaverToken;
import cn.nubia.screensaver.common.SettingsObserver;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.sensor.GSSensorController;
import cn.nubia.screensaver.system.GSSystemController;
import cn.nubia.screensaver.system.ISnapshotKeyguard;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.screensaver.view.ScreensaverRootView;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.ext.system.PowerStateMonitorProxy;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class GameScreensaverManager implements GameMonitor.Callback, GSPowerController.PowerCallback, GSWindowController.Callback, GSSensorController.Callback, DisplayManager.DisplayListener {
    public static boolean I = ZteFeatureWrapper.getBoolean("ZTE_FEATURE_MAGIC_GAME_SCREEN_SAVER", false);
    private static volatile GameScreensaverManager J;
    private boolean A;
    private boolean B;
    private String C;
    private final List D;
    private ActivityManager E;
    private final Runnable F;
    private Locale G;
    private boolean H;

    /* renamed from: c, reason: collision with root package name */
    private final Map f8969c;

    /* renamed from: h, reason: collision with root package name */
    private final Map f8970h;

    /* renamed from: i, reason: collision with root package name */
    private final Handler f8971i;

    /* renamed from: j, reason: collision with root package name */
    private final List f8972j;

    /* renamed from: k, reason: collision with root package name */
    private int f8973k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f8974l;

    /* renamed from: m, reason: collision with root package name */
    private ScreensaverToken f8975m;

    /* renamed from: n, reason: collision with root package name */
    private ActionEvent f8976n;

    /* renamed from: o, reason: collision with root package name */
    private String f8977o;

    /* renamed from: p, reason: collision with root package name */
    private Handler f8978p;

    /* renamed from: q, reason: collision with root package name */
    private Context f8979q;

    /* renamed from: r, reason: collision with root package name */
    private final GSPowerController f8980r;

    /* renamed from: s, reason: collision with root package name */
    private final GSSensorController f8981s;
    private final GSWindowController t;
    private final GSSystemController u;
    private final SettingsObserver v;
    private final CallStateCallback w;
    private final DisplayManager x;
    private ISnapshotKeyguard y;
    private boolean z;

    private GameScreensaverManager() {
        ArrayMap arrayMap = new ArrayMap();
        this.f8969c = arrayMap;
        this.f8970h = new ArrayMap();
        this.f8971i = new Handler(ThreadManager.c().b());
        this.f8972j = new ArrayList();
        this.f8973k = 0;
        this.C = "";
        ArrayList arrayList = new ArrayList();
        this.D = arrayList;
        this.F = new Runnable() { // from class: cn.nubia.screensaver.v
            @Override // java.lang.Runnable
            public final void run() {
                GameScreensaverManager.this.p0();
            }
        };
        Configuration configuration = ContextWrapper.getContext().getResources().getConfiguration();
        this.G = configuration.getLocales().get(0);
        Context createConfigurationContext = ContextWrapper.getContext().createConfigurationContext(configuration);
        this.f8979q = createConfigurationContext;
        arrayList.addAll(DefaultUtil.a(createConfigurationContext.getString(R.string.lock_card_list)));
        this.E = (ActivityManager) this.f8979q.getSystemService(ActivityManager.class);
        HandlerThread handlerThread = new HandlerThread(ScreensaverRootView.TAG);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f8978p = handler;
        this.f8976n = new ActionEvent(handler);
        ScreensaverToken screensaverToken = new ScreensaverToken(this.f8978p, "onConnected", new ScreensaverToken.SystemCallback() { // from class: cn.nubia.screensaver.w
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GameScreensaverManager.this.i0(bundle);
            }
        });
        this.f8975m = screensaverToken;
        screensaverToken.e("keyguardState", new ScreensaverToken.SystemCallback() { // from class: cn.nubia.screensaver.x
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GameScreensaverManager.this.Z(bundle);
            }
        });
        this.f8975m.e("onSingleTap", new ScreensaverToken.SystemCallback() { // from class: cn.nubia.screensaver.y
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GameScreensaverManager.this.j0(bundle);
            }
        });
        GSSensorController gSSensorController = new GSSensorController(this);
        this.f8981s = gSSensorController;
        gSSensorController.L(this);
        arrayMap.put(GSSensorController.class, gSSensorController);
        GSWindowController gSWindowController = new GSWindowController(this, this);
        this.t = gSWindowController;
        arrayMap.put(GSWindowController.class, gSWindowController);
        GSSystemController gSSystemController = new GSSystemController(this);
        this.u = gSSystemController;
        arrayMap.put(GSSystemController.class, gSSystemController);
        GSPowerController gSPowerController = new GSPowerController(this);
        this.f8980r = gSPowerController;
        arrayMap.put(GSPowerController.class, gSPowerController);
        gSPowerController.t(this);
        this.v = new SettingsObserver(this);
        this.w = new CallStateCallback(this);
        DisplayManager displayManager = (DisplayManager) this.f8979q.getSystemService(DisplayManager.class);
        this.x = displayManager;
        displayManager.registerDisplayListener(this, this.f8978p);
        this.y = gSSystemController.g();
    }

    public static GameScreensaverManager L() {
        if (J == null) {
            synchronized (GameScreensaverManager.class) {
                try {
                    if (J == null) {
                        J = new GameScreensaverManager();
                    }
                } finally {
                }
            }
        }
        return J;
    }

    private boolean Q() {
        if (!ZteFeature.isSuppprtRedMagicGameKey()) {
            return this.H;
        }
        SettingsObserver settingsObserver = this.v;
        return settingsObserver != null && settingsObserver.g();
    }

    private boolean S(int i2) {
        return this.t.I(this.x.getDisplay(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X() {
        if (this.A) {
            return;
        }
        Process.setThreadPriority(-16);
        Thread.currentThread().setPriority(10);
        this.f8969c.forEach(new BiConsumer() { // from class: cn.nubia.screensaver.A
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IController) obj2).f();
            }
        });
        SystemMgr.y(this.f8979q).h(this);
        n0();
        this.A = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String a0(int i2) {
        return (String) this.D.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0() {
        String str;
        try {
            long currentTimeMillis = System.currentTimeMillis() / 86400000;
            if (currentTimeMillis == Settings.Global.getLong(this.f8979q.getContentResolver(), "sendGameScreensaverEventDay", 0L) || !this.t.J() || (str = (String) Arrays.stream(CardContainerController.o()).mapToObj(new IntFunction() { // from class: cn.nubia.screensaver.r
                @Override // java.util.function.IntFunction
                public final Object apply(int i2) {
                    String a0;
                    a0 = GameScreensaverManager.this.a0(i2);
                    return a0;
                }
            }).collect(Collectors.joining(","))) == null || str.isEmpty()) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("card_type", str);
            NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_Info_quick_view_used", bundle);
            GaLog.e(ScreensaverRootView.TAG, "sentUsedEvent cardType=" + str + " sendEventDay=" + currentTimeMillis);
            Settings.Global.putLong(this.f8979q.getContentResolver(), "sendGameScreensaverEventDay", currentTimeMillis);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0(Class cls, IController iController) {
        iController.j(this.f8973k);
    }

    private void h0(final int i2, final boolean z) {
        if (this.z != z && i2 == 0) {
            this.z = z;
            GaLog.e(ScreensaverRootView.TAG, "notifyKeyguardState mIsKeyguardShowing=" + this.z);
            o0("keyguard");
        }
        if (((Boolean) this.f8970h.getOrDefault(Integer.valueOf(i2), Boolean.FALSE)).booleanValue() != z) {
            this.f8970h.put(Integer.valueOf(i2), Boolean.valueOf(z));
        }
        this.f8969c.forEach(new BiConsumer() { // from class: cn.nubia.screensaver.o
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IController) obj2).o(i2, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i0(Bundle bundle) {
        final Bundle bundle2 = bundle.getBundle("keyguardState");
        if (bundle2 != null) {
            this.f8978p.postDelayed(new Runnable() { // from class: cn.nubia.screensaver.q
                @Override // java.lang.Runnable
                public final void run() {
                    GameScreensaverManager.this.Z(bundle2);
                }
            }, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j0(Bundle bundle) {
        GaLog.e(ScreensaverRootView.TAG, "onSingleTap bundle=" + bundle);
        g0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0, reason: merged with bridge method [inline-methods] */
    public void Z(Bundle bundle) {
        if (bundle.containsKey("displayId") && bundle.containsKey("keyguardShowing")) {
            boolean z = bundle.getBoolean("keyguardShowing");
            int i2 = bundle.getInt("displayId");
            if (this.f8970h.containsKey(Integer.valueOf(i2)) && ((Boolean) this.f8970h.get(Integer.valueOf(i2))).booleanValue() == z) {
                return;
            }
            h0(i2, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l0() {
        this.f8976n.m("sentUsedEvent");
        this.f8971i.postDelayed(new Runnable() { // from class: cn.nubia.screensaver.p
            @Override // java.lang.Runnable
            public final void run() {
                GameScreensaverManager.this.b0();
            }
        }, 500L);
    }

    private void n0() {
        int i2;
        int i3;
        boolean z = false;
        int state = this.x.getDisplay(0).getState();
        if (state != 1) {
            i3 = 2;
            if (state != 2) {
                if (state == 3 || state == 4) {
                    i2 = 256;
                    i3 = 3;
                } else if (state != 6) {
                    i2 = 0;
                    i3 = 0;
                }
            }
            i2 = 128;
        } else {
            i2 = 64;
            i3 = 1;
        }
        if (i3 != this.f8973k) {
            this.f8973k = i3;
            if (i3 == 3 && U()) {
                z = true;
            }
            this.f8974l = z;
            this.f8969c.forEach(new BiConsumer() { // from class: cn.nubia.screensaver.u
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GameScreensaverManager.this.c0((Class) obj, (IController) obj2);
                }
            });
            if (i2 != 0) {
                this.f8976n.g(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0() {
        if (!this.A) {
            GaLog.e(ScreensaverRootView.TAG, "---updateScreensaverInner--- mIsInit=" + this.A);
            return;
        }
        GSWindowController gSWindowController = this.t;
        boolean z = gSWindowController != null && gSWindowController.J();
        StringBuilder sb = new StringBuilder();
        boolean d0 = d0(sb, false);
        String sb2 = sb.toString();
        if (!sb2.equals(this.f8977o) || GaLog.f17034b) {
            this.f8977o = sb2;
            GaLog.e(ScreensaverRootView.TAG, "---updateScreensaver--- needShow=" + d0 + " isShow=" + z + " reason=" + this.f8977o + ":" + this.C);
        }
        this.f8980r.Q();
        if (d0) {
            this.t.T(sb2);
        } else {
            this.t.E(sb2);
        }
    }

    private void q0() {
        if (this.f8980r.y()) {
            return;
        }
        this.f8980r.R();
    }

    public void B() {
        Locale h2 = GameAssistApplication.j().h();
        if (h2.equals(this.G)) {
            return;
        }
        GaLog.a(ScreensaverRootView.TAG, "locale has change");
        Configuration configuration = this.f8979q.getResources().getConfiguration();
        configuration.setLocale(h2);
        this.f8979q = ContextWrapper.getContext().createConfigurationContext(configuration);
        this.G = h2;
    }

    public Handler C() {
        return new Handler(this.f8978p.getLooper());
    }

    public View D() {
        return LayoutInflater.from(this.f8979q).inflate(R.layout.card_container, (ViewGroup) null);
    }

    public void E(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String str) {
        printWriter.println(str + ScreensaverRootView.TAG);
        printWriter.println(str + "mToken=" + this.f8975m);
        printWriter.println(str + "mScreensaverDisplayIds=" + this.f8972j);
        StringBuilder sb = new StringBuilder();
        printWriter.println(str + "needShow=" + d0(sb, false) + " : " + ((Object) sb));
        this.f8969c.forEach(new BiConsumer() { // from class: cn.nubia.screensaver.t
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IController) obj2).a(fileDescriptor, printWriter, str);
            }
        });
        this.v.e(fileDescriptor, printWriter, str);
    }

    public void F(boolean z) {
        this.t.U(z);
        o0("gameKey");
    }

    public ActionEvent G() {
        return this.f8976n;
    }

    public Context H() {
        return this.f8979q;
    }

    public IController I(Class cls) {
        IController iController = (IController) this.f8969c.get(cls);
        if (iController != null) {
            return iController;
        }
        throw new RuntimeException("not find " + cls);
    }

    public int J() {
        return this.f8973k;
    }

    public Handler K() {
        return this.f8978p;
    }

    public ScreensaverToken M() {
        return this.f8975m;
    }

    public boolean N() {
        return this.f8972j.size() > 0;
    }

    public void O() {
        this.f8978p.post(new Runnable() { // from class: cn.nubia.screensaver.s
            @Override // java.lang.Runnable
            public final void run() {
                GameScreensaverManager.this.X();
            }
        });
    }

    public boolean P() {
        return J() == 3;
    }

    public boolean R() {
        return this.z;
    }

    public boolean T() {
        return this.f8980r.y();
    }

    public boolean U() {
        return this.B;
    }

    @Override // cn.nubia.screensaver.sensor.GSSensorController.Callback
    public void a(boolean z) {
        GaLog.e(ScreensaverRootView.TAG, "onProximityChanged far=" + z + " isHorizontal=" + this.f8981s.P());
        boolean P = P();
        if (this.B && z && P && this.f8981s.P()) {
            q0();
        }
        this.f8980r.O(false);
        this.B = false;
    }

    @Override // cn.nubia.screensaver.power.GSPowerController.PowerCallback
    public void b(boolean z) {
        o0(PowerStateMonitorProxy.POWER_STATE_DOZE);
    }

    public boolean d0(StringBuilder sb, boolean z) {
        return e0(sb, z, false);
    }

    @Override // cn.nubia.screensaver.power.GSPowerController.PowerCallback
    public void e(int i2, String str) {
        try {
            if (this.z) {
                return;
            }
            this.H = DefaultUtil.b();
            if (this.u.m(0)) {
                return;
            }
            this.z = true;
            o0("resetKeyguard");
        } catch (Exception e2) {
            GaLog.e(ScreensaverRootView.TAG, "onGotoSleep e:" + e2.getMessage());
        }
    }

    public boolean e0(StringBuilder sb, boolean z, boolean z2) {
        if (this.v == null || this.f8981s == null || this.t == null) {
            return false;
        }
        StringBuilder sb2 = new StringBuilder();
        boolean z3 = I;
        sb2.append(z3 ? "E" : "_");
        boolean l2 = this.u.l();
        sb2.append(l2 ? "C" : "_");
        boolean z4 = this.f8980r.x() || this.f8980r.y() || z2;
        sb2.append(z4 ? "A" : "_");
        boolean z5 = this.z || this.v.h() || z2;
        sb2.append(z5 ? "K" : "_");
        boolean Q = Q();
        sb2.append(Q ? "G" : "_");
        boolean i2 = this.v.i();
        sb2.append(i2 ? "Q" : "_");
        CallStateCallback callStateCallback = this.w;
        boolean z6 = callStateCallback == null || callStateCallback.c();
        sb2.append(z6 ? "I" : "_");
        boolean z7 = !this.t.C();
        sb2.append(z7 ? "P" : "_");
        boolean z8 = !this.t.B();
        sb2.append(z8 ? "S" : "_");
        boolean z9 = this.f8981s.P() || z2;
        sb2.append(z9 ? "H" : "_");
        boolean z10 = z3 && l2 && z5 && z6 && i2 && Q && z7 && z8;
        if (this.f8980r.u() && (this.f8980r.w() || this.f8980r.x())) {
            sb2.append("A");
            z4 = true;
            z9 = true;
        }
        sb2.append(z2 ? "s" : "_");
        if (!z) {
            z10 = z10 && z4 && z9;
        }
        if (sb != null) {
            sb.append((CharSequence) sb2);
        }
        return z10;
    }

    @Override // cn.nubia.screensaver.sensor.GSSensorController.Callback
    public void f() {
        o0("rotation");
    }

    public void f0() {
        g0(false);
    }

    public void g0(boolean z) {
        if (this.f8981s.a0() || !z) {
            boolean w = this.f8980r.w();
            boolean P = P();
            StringBuilder sb = new StringBuilder();
            boolean d0 = d0(sb, true);
            String sb2 = sb.toString();
            GaLog.e(ScreensaverRootView.TAG, "needShowFromDoze isDoze=" + w + " isDozeScreen=" + P + " reason=" + sb2 + " needShow=" + d0);
            if (w && d0) {
                if (P && !this.B && !this.f8981s.Q()) {
                    q0();
                } else {
                    if (P) {
                        return;
                    }
                    this.B = true;
                    this.t.V(sb2);
                    this.f8980r.O(true);
                }
            }
        }
    }

    @Override // cn.nubia.screensaver.power.GSPowerController.PowerCallback
    public void h(int i2, String str) {
        this.B = false;
    }

    @Override // cn.nubia.screensaver.GSWindowController.Callback
    public void m() {
        if (this.f8980r.y()) {
            l0();
        } else {
            this.f8976n.d(128, "sentUsedEvent", new Runnable() { // from class: cn.nubia.screensaver.z
                @Override // java.lang.Runnable
                public final void run() {
                    GameScreensaverManager.this.l0();
                }
            });
        }
    }

    public boolean m0() {
        SettingsObserver settingsObserver = this.v;
        boolean z = false;
        if (settingsObserver == null) {
            return false;
        }
        boolean z2 = (this.z && settingsObserver.h()) || this.f8980r.w();
        boolean Q = Q();
        boolean i2 = this.v.i();
        if (z2 && Q && i2) {
            z = true;
        }
        if (!z) {
            GaLog.a(ScreensaverRootView.TAG, "cannot show projection GS window " + z2 + " " + Q + " " + i2);
        }
        return z;
    }

    public void o0(String str) {
        this.C = str;
        this.f8978p.removeCallbacks(this.F);
        this.f8978p.postDelayed(this.F, 5L);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i2) {
        this.t.O(i2);
        Integer valueOf = Integer.valueOf(i2);
        if (this.f8972j.contains(valueOf) || !S(i2)) {
            return;
        }
        this.f8972j.add(valueOf);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i2) {
        if (i2 == 0) {
            n0();
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i2) {
        if (this.f8970h.containsKey(Integer.valueOf(i2))) {
            this.f8970h.remove(Integer.valueOf(i2));
            h0(i2, false);
        }
        this.t.P(i2);
        Integer valueOf = Integer.valueOf(i2);
        if (this.f8972j.contains(valueOf)) {
            this.f8972j.remove(valueOf);
        }
    }
}
