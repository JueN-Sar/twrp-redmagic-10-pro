package cn.nubia.gameassist.panel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.UserManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.bright.BrightSeekbarViewController;
import cn.nubia.gameassist.centerinfo.CentreInfoViewController;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.common.IHostPanel;
import cn.nubia.gameassist.common.SettingsListener;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.dessert.TilesManager;
import cn.nubia.gameassist.dessert.panel.DessertViewController;
import cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController;
import cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController;
import cn.nubia.gameassist.meditationmode.MeditationController;
import cn.nubia.gameassist.meditationmode.MeditationModeViewController;
import cn.nubia.gameassist.onemorething.OneMoreThingViewController;
import cn.nubia.gameassist.operation.SubViewController;
import cn.nubia.gameassist.panel.TouchHelper;
import cn.nubia.gameassist.performance.CpuEffectViewController;
import cn.nubia.gameassist.performance.GpuEffectViewController;
import cn.nubia.gameassist.performance.PerformanceViewController;
import cn.nubia.gameassist.pips.panel.PipViewController;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.plugin.panel.PluginSwitchController;
import cn.nubia.gameassist.plugin.panel.PluginViewController;
import cn.nubia.gameassist.search.SearchViewController;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.tips.learn.UserGuideController;
import cn.nubia.gameassist.utils.CtsUtils;
import cn.nubia.gameassist.utils.GameAssistMutex;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.gameassist.volume.VolumeSeekbarViewController;
import cn.nubia.plugin.timer.TimerMgr;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.DensityHelper;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverData;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.ext.common.MutableData;
import com.zte.gameassist.input.EventDispatcher;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.GameKeysHelperWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameAssistWindowManager implements ObserverManager.SettingCallback, DumpController.Dump, TouchHelper.OnTouchHelperCallback, IHostPanel, GameMonitor.Callback, IGameAssistCommander {
    public static int P;
    public static int Q;
    private static int S;
    private static int T;
    private static volatile GameAssistWindowManager U;
    public static boolean V;
    public static boolean X;
    private TouchHelper B;
    private boolean H;
    private boolean L;

    /* renamed from: p, reason: collision with root package name */
    private WindowManager f6781p;

    /* renamed from: q, reason: collision with root package name */
    private UserManager f6782q;

    /* renamed from: r, reason: collision with root package name */
    private GameAssistRootView f6783r;

    /* renamed from: s, reason: collision with root package name */
    private WindowManager.LayoutParams f6784s;
    private boolean t;
    private final Context u;
    private DisplayManager v;
    private TileHost w;
    private SettingsListener x;
    private int z;
    private static final Class[] M = {PluginSwitchController.class, SubViewController.class, PluginViewController.class, CpuEffectViewController.class, BrightSeekbarViewController.class, PerformanceViewController.class};
    private static final Class[] N = {CentreInfoViewController.class, OneMoreThingViewController.class, SearchViewController.class};
    private static final Class[] O = {PipViewController.class, GpuEffectViewController.class, DessertViewController.class, MeditationModeViewController.class, VolumeSeekbarViewController.class};
    public static final MutableData R = new MutableData(0);
    public static boolean W = false;

    /* renamed from: c, reason: collision with root package name */
    private final Map f6772c = new HashMap();

    /* renamed from: h, reason: collision with root package name */
    private final List f6773h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private final List f6774i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private final List f6775j = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private final List f6776k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    private final DisplayMetrics f6777l = new DisplayMetrics();

    /* renamed from: m, reason: collision with root package name */
    private final Point f6778m = new Point();

    /* renamed from: n, reason: collision with root package name */
    private Handler f6779n = new Handler(ThreadManager.c().b());

    /* renamed from: o, reason: collision with root package name */
    private Handler f6780o = new Handler(ThreadManager.c().e());
    private long y = 0;
    private final EventDispatcher A = new EventDispatcher();
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private BroadcastReceiver F = new BroadcastReceiver() { // from class: cn.nubia.gameassist.panel.GameAssistWindowManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.hardware.usb.action.USB_STATE".equals(action)) {
                GaLog.a("GameAssistWindowManager", action);
                GameAssistWindowManager.this.g0("usb");
            } else if ("android.intent.action.USER_UNLOCKED".equals(action)) {
                GaLog.a("GameAssistWindowManager", action);
                GameAssistWindowManager.V = false;
                GameAssistWindowManager.this.c0();
            }
        }
    };
    private final IHostPanel.PanelCallback G = new AnonymousClass2();
    private Runnable I = new Runnable() { // from class: cn.nubia.gameassist.panel.GameAssistWindowManager.4
        @Override // java.lang.Runnable
        public void run() {
            if (GameAssistWindowManager.this.H) {
                return;
            }
            GameAssistWindowManager.this.H = true;
            GameAssistWindowManager.this.A.j(GameAssistWindowManager.this.u, GameAssistWindowManager.this.L ? "danmu" : "cn.nubia.gameassist");
        }
    };
    private Runnable J = new Runnable() { // from class: cn.nubia.gameassist.panel.GameAssistWindowManager.5
        @Override // java.lang.Runnable
        public void run() {
            if (GameAssistWindowManager.this.H) {
                GameAssistWindowManager.this.H = false;
                GameAssistWindowManager.this.A.m();
            }
        }
    };
    final Runnable K = new Runnable() { // from class: cn.nubia.gameassist.panel.d
        @Override // java.lang.Runnable
        public final void run() {
            GameAssistWindowManager.this.k0();
        }
    };

    /* renamed from: cn.nubia.gameassist.panel.GameAssistWindowManager$2, reason: invalid class name */
    class AnonymousClass2 implements IHostPanel.PanelCallback {
        AnonymousClass2() {
        }

        @Override // cn.nubia.gameassist.common.IHostPanel.PanelCallback
        public void a() {
            GameAssistWindowManager.this.f6776k.forEach(new Consumer() { // from class: cn.nubia.gameassist.panel.A
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IHostPanel.PanelCallback) obj).a();
                }
            });
        }

        @Override // cn.nubia.gameassist.common.IHostPanel.PanelCallback
        public void b(final boolean z) {
            GameAssistWindowManager.this.z0(z);
            GameAssistWindowManager.this.f6776k.forEach(new Consumer() { // from class: cn.nubia.gameassist.panel.B
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IHostPanel.PanelCallback) obj).b(z);
                }
            });
        }

        @Override // cn.nubia.gameassist.common.IHostPanel.PanelCallback
        public void c() {
            GameAssistWindowManager.this.f6776k.forEach(new Consumer() { // from class: cn.nubia.gameassist.panel.z
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IHostPanel.PanelCallback) obj).c();
                }
            });
        }
    }

    /* renamed from: cn.nubia.gameassist.panel.GameAssistWindowManager$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ GameAssistWindowManager f6787c;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f6787c.E) {
                RecycleWatch.y(this.f6787c.f6781p, this.f6787c.f6783r);
            }
        }
    }

    private GameAssistWindowManager(Context context) {
        this.u = context;
        this.f6781p = (WindowManager) context.getSystemService(WindowManager.class);
        this.f6782q = (UserManager) context.getSystemService(UserManager.class);
        this.v = (DisplayManager) context.getSystemService(DisplayManager.class);
        SystemMgr.y(context).o(this);
        V();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(InflaterHelper.FixedScreenState fixedScreenState) {
        int i2 = !FoldMgr.c().e() ? 1 : 0;
        MutableData mutableData = R;
        if (i2 != ((Integer) mutableData.b()).intValue()) {
            mutableData.g(Integer.valueOf(i2));
        }
        if (SystemMgr.H() && this.z != 0) {
            GameAssistRootView gameAssistRootView = this.f6783r;
            if (gameAssistRootView != null) {
                gameAssistRootView.removeAllViews();
                this.f6783r.inflateContent();
            }
            g0("onFoldStateChanged big=" + FoldMgr.c().e() + "-" + DensityHelper.a());
            final boolean e2 = FoldMgr.c().e();
            M(new Consumer() { // from class: cn.nubia.gameassist.panel.p
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BaseViewController) obj).E(e2);
                }
            });
        } else if (!SystemMgr.H() && this.f6780o.hasCallbacks(this.K)) {
            this.f6780o.removeCallbacks(this.K);
            this.K.run();
        }
        this.z = fixedScreenState.f16526e;
    }

    private void C0() {
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.o
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GameAssistWindowManager.t0((BaseViewController) obj);
            }
        });
    }

    private void F0(Configuration configuration, boolean z) {
        boolean z2 = configuration.orientation == 2;
        if (z2 != this.t || z) {
            this.t = z2;
            g0("rotationwatch");
            Point point = new Point();
            Display display = this.v.getDisplay(0);
            display.getRealMetrics(this.f6777l);
            display.getRealSize(point);
            int i2 = point.x;
            int i3 = point.y;
            int i4 = i2 > i3 ? i2 : i3;
            S = i4;
            if (i2 >= i3) {
                i2 = i3;
            }
            T = i2;
            boolean z3 = this.t;
            if (z3) {
                P = i2;
                Q = i4;
            } else {
                P = i4;
                Q = i2;
            }
            if (z3) {
                this.f6778m.set(i4, i2);
            } else {
                this.f6778m.set(i2, i4);
            }
            GameAssistRootView gameAssistRootView = this.f6783r;
            if (gameAssistRootView != null) {
                gameAssistRootView.onDeviceScreenChanged();
            }
            M(new Consumer() { // from class: cn.nubia.gameassist.panel.i
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameAssistWindowManager.this.w0((BaseViewController) obj);
                }
            });
            GaLog.e("GameAssistWindowManager", "updateDisplayParams mIsHorizontal=" + this.t + " mFoldChanged=" + this.C);
            this.C = false;
        }
    }

    private void G0() {
        GaLog.e("GameAssistWindowManager", "updateInputEvent " + SystemMgr.H());
        this.f6780o.removeCallbacks(this.I);
        this.f6780o.removeCallbacks(this.J);
        if (SystemMgr.H()) {
            this.f6780o.postDelayed(this.I, 300L);
        } else {
            this.f6780o.postDelayed(this.J, 300L);
        }
    }

    private synchronized void K() {
        if (!this.E) {
            this.E = true;
            this.f6781p.addView(this.f6783r, this.f6784s);
            this.f6783r.inflateContent();
        }
    }

    private boolean L(Runnable runnable) {
        if (this.f6780o.getLooper().isCurrentThread()) {
            return false;
        }
        this.f6780o.post(runnable);
        return true;
    }

    private void M(Consumer consumer) {
        this.f6773h.forEach(consumer);
        this.f6774i.forEach(consumer);
        this.f6775j.forEach(consumer);
    }

    public static GameAssistWindowManager O(Context context) {
        if (U == null) {
            synchronized (GameAssistWindowManager.class) {
                try {
                    if (U == null) {
                        U = new GameAssistWindowManager(context);
                    }
                } finally {
                }
            }
        }
        return U;
    }

    public static int P() {
        return S;
    }

    public static int Q() {
        return T;
    }

    public static Point R() {
        return O(ContextWrapper.getContext()).f6778m;
    }

    private void V() {
        try {
            for (Class cls : M) {
                BaseViewController baseViewController = (BaseViewController) cls.getConstructor(getClass()).newInstance(this);
                if (baseViewController != null) {
                    this.f6773h.add(baseViewController);
                    this.f6772c.put(cls, baseViewController);
                }
            }
            for (Class cls2 : N) {
                BaseViewController baseViewController2 = (BaseViewController) cls2.getConstructor(getClass()).newInstance(this);
                if (baseViewController2 != null) {
                    this.f6774i.add(baseViewController2);
                    this.f6772c.put(cls2, baseViewController2);
                }
            }
            for (Class cls3 : O) {
                BaseViewController baseViewController3 = (BaseViewController) cls3.getConstructor(getClass()).newInstance(this);
                if (baseViewController3 != null) {
                    this.f6775j.add(baseViewController3);
                    this.f6772c.put(cls3, baseViewController3);
                }
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private void W() {
        V = !this.f6782q.isUserUnlocked();
        this.x = new SettingsListener(this.u, this);
        GameAssistRootView gameAssistRootView = new GameAssistRootView(this.u);
        this.f6783r = gameAssistRootView;
        gameAssistRootView.setCallback(this.G);
        F0(this.u.getResources().getConfiguration(), true);
        Z();
        Y();
        b0();
        c0();
        IndicateWindowController.s(this.u);
        SystemMgr.y(this.u).h(this);
        DumpController.c().a(this);
        GameAssistMutex.d().b(new GameAssistMutex.MutexCallback() { // from class: cn.nubia.gameassist.panel.j
            @Override // cn.nubia.gameassist.utils.GameAssistMutex.MutexCallback
            public final void a(List list) {
                GameAssistWindowManager.h0(list);
            }
        });
        if (FoldMgr.f()) {
            R.g(Integer.valueOf(!FoldMgr.c().e() ? 1 : 0));
            InflaterHelper.f16516e.e(true, new ObserverData.Observer() { // from class: cn.nubia.gameassist.panel.k
                @Override // com.zte.gameassist.common.ObserverData.Observer
                public final void a(Object obj) {
                    GameAssistWindowManager.this.A0((InflaterHelper.FixedScreenState) obj);
                }
            });
            FoldMgr.c().a(new FoldMgr.Callback() { // from class: cn.nubia.gameassist.panel.l
                @Override // com.zte.gameassist.common.FoldMgr.Callback
                public final void onDisplayInUseStateChanged(int i2) {
                    GameAssistWindowManager.this.i0(i2);
                }
            });
        }
    }

    private void X() {
        if ("userdebug".equals(Build.TYPE)) {
            CtsUtils.a(ContextWrapper.getContext());
        }
    }

    private void Y() {
        TouchHelper touchHelper = new TouchHelper(this.u);
        this.B = touchHelper;
        touchHelper.v(this);
        this.A.d(this.B);
        m0(SystemMgr.H());
    }

    private void Z() {
        if (this.f6784s == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2027, 75826952, -3);
            this.f6784s = layoutParams;
            layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            this.f6784s.setTitle("GameAssist");
            WindowManager.LayoutParams layoutParams2 = this.f6784s;
            layoutParams2.layoutInDisplayCutoutMode = 3;
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams2);
            this.f6784s.packageName = this.u.getPackageName();
            WindowManager.LayoutParams layoutParams3 = this.f6784s;
            layoutParams3.width = -1;
            layoutParams3.height = -1;
        }
    }

    private void a0() {
        this.f6783r.setOrientation(this.t);
        ViewGroup viewGroup = (ViewGroup) this.f6783r.getLeftPanel();
        for (BaseViewController baseViewController : this.f6773h) {
            if (baseViewController.w()) {
                baseViewController.O();
            }
            View findViewById = viewGroup.findViewById(baseViewController.C());
            if (findViewById == null) {
                findViewById = viewGroup;
            }
            baseViewController.m(findViewById);
            View findViewById2 = viewGroup.findViewById(baseViewController.s());
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
            }
        }
        ViewGroup viewGroup2 = (ViewGroup) this.f6783r.getMiddlePanel();
        for (BaseViewController baseViewController2 : this.f6774i) {
            if (baseViewController2.w()) {
                baseViewController2.O();
            }
            View findViewById3 = viewGroup2.findViewById(baseViewController2.C());
            if (findViewById3 == null) {
                findViewById3 = viewGroup2;
            }
            baseViewController2.m(findViewById3);
            View findViewById4 = viewGroup2.findViewById(baseViewController2.s());
            if (findViewById4 != null) {
                viewGroup2.removeView(findViewById4);
            }
        }
        ViewGroup viewGroup3 = (ViewGroup) this.f6783r.getRightPanel();
        for (BaseViewController baseViewController3 : this.f6775j) {
            if (baseViewController3.w()) {
                baseViewController3.O();
            }
            View findViewById5 = viewGroup3.findViewById(baseViewController3.C());
            if (findViewById5 == null) {
                findViewById5 = viewGroup3;
            }
            baseViewController3.m(findViewById5);
            View findViewById6 = viewGroup3.findViewById(baseViewController3.s());
            if (findViewById6 != null) {
                viewGroup3.removeView(findViewById6);
            }
        }
    }

    private void b0() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        this.u.registerReceiver(this.F, intentFilter, null, this.f6779n, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0() {
        if (V || this.w != null) {
            return;
        }
        this.w = new TileHost(this.u, this);
        ObserverManager.c().b(this.u, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
        ObserverManager.c().b(this.u, Settings.System.getUriFor("ss_multi_window_enabled"), this);
        this.D = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h0(List list) {
        GaLog.a("GameAssistWindowManager", "onMutexChanged tags=" + list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0(int i2) {
        GameAssistRootView gameAssistRootView;
        this.C = true;
        if (!SystemMgr.H() || (gameAssistRootView = this.f6783r) == null) {
            return;
        }
        gameAssistRootView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0() {
        W();
        TilesManager.j().p();
        Utils.Z(this.u, this.f6779n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k0() {
        if (!d0() || SystemMgr.H()) {
            return;
        }
        C0();
        if (this.E) {
            this.E = false;
            RecycleWatch.y(this.f6781p, this.f6783r);
        }
        this.f6783r.removeAllViews();
        RecycleWatch.p().k(this.f6783r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o0() {
        this.w.x(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r0() {
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.q
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).M();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s0() {
        u0("onTouchSlideIn");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t0(BaseViewController baseViewController) {
        if (baseViewController.w()) {
            baseViewController.O();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v0() {
        this.w.x(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w0(BaseViewController baseViewController) {
        baseViewController.K(this.t, this.f6778m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0(boolean z) {
        if (z || !W || this.f6783r == null) {
            if (z) {
                GaLog.a("GameAssistWindowManager", "showWindowAfterAni");
                GameAssistMutex.d().c();
                return;
            }
            return;
        }
        GaLog.a("GameAssistWindowManager", "hideWindowAfterAni");
        W = false;
        GameAssistMutex.d().g();
        this.f6783r.setVisibility(4);
        this.B.w(false);
        this.f6779n.post(new Runnable() { // from class: cn.nubia.gameassist.panel.m
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.o0();
            }
        });
        this.f6783r.closePanel("hidewindow");
        Settings.Global.putInt(this.u.getContentResolver(), "nubia_game_assist_show", 0);
        EventListenerMgr.e(3);
        C0();
    }

    public void B0() {
        this.f6780o.post(new Runnable() { // from class: cn.nubia.gameassist.panel.g
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.r0();
            }
        });
    }

    public void D0(IHostPanel.PanelCallback panelCallback) {
        if (this.f6776k.contains(panelCallback)) {
            return;
        }
        this.f6776k.add(panelCallback);
    }

    /* renamed from: E0, reason: merged with bridge method [inline-methods] */
    public void u0(final String str) {
        GaLog.k("GameAssistWindowManager", "showWindow() reason =" + str + " visible=" + W + " add=" + this.E);
        if (L(new Runnable() { // from class: cn.nubia.gameassist.panel.s
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.u0(str);
            }
        }) || W || str == null) {
            return;
        }
        if (!this.D || !SystemMgr.H()) {
            GaLog.k("GameAssistWindowManager", "showWindow(), not init or not in gamescene");
            return;
        }
        GaLog.a("GameAssistWindowManager", "showWindow() add=" + this.E + " visible=" + W);
        this.f6783r.setOrientation(this.t);
        K();
        if (W) {
            GaLog.k("GameAssistWindowManager", "showWindow() mVisible=" + W);
            return;
        }
        W = true;
        a0();
        this.f6783r.setVisibility(0);
        this.f6783r.openPanel("addwindow");
        Settings.Global.putInt(this.u.getContentResolver(), "nubia_game_assist_show", 1);
        EventListenerMgr.e(3);
        this.f6779n.post(new Runnable() { // from class: cn.nubia.gameassist.panel.t
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.v0();
            }
        });
        this.B.w(true);
    }

    public Context N() {
        return this.u;
    }

    public TileHost S() {
        return this.w;
    }

    public BaseViewController T(Class cls) {
        return (BaseViewController) this.f6772c.getOrDefault(cls, null);
    }

    public int[] U() {
        return new int[]{Q, P};
    }

    @Override // cn.nubia.gameassist.panel.TouchHelper.OnTouchHelperCallback
    public void a() {
        GameAssistRootView gameAssistRootView = this.f6783r;
        if (gameAssistRootView == null || gameAssistRootView.getWindowVisibility() == 0) {
            return;
        }
        GaLog.a("GameAssistWindowManager", "checkWindow() restore when gameassist window not visible realy");
        g0("resetfromtouchhelper");
    }

    @Override // cn.nubia.gameassist.common.IHostPanel
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void g0(final String str) {
        if (L(new Runnable() { // from class: cn.nubia.gameassist.panel.e
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.g0(str);
            }
        }) || !W) {
            return;
        }
        GaLog.a("GameAssistWindowManager", "hideWindow() reason=" + str + " visible=" + W);
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).N();
            }
        });
        if (this.f6783r.isOpened()) {
            this.f6783r.closePanel("hidewindow");
        } else if (W) {
            z0(false);
        }
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, final PrintWriter printWriter, String[] strArr) {
        printWriter.println("GameAssistManager:");
        printWriter.print("  ResAdapter: ");
        printWriter.println(DensityHelper.a());
        printWriter.print("  mInited: ");
        printWriter.println(this.D);
        printWriter.print("  mAdded: ");
        printWriter.println(this.E);
        printWriter.print("  mVisible: ");
        printWriter.println(W);
        printWriter.print("  mIsHorizontal: ");
        printWriter.println(this.t);
        printWriter.print("  screensize: ");
        printWriter.println(this.f6778m);
        printWriter.print("  DISPLAY_MAX_WIDTH: ");
        printWriter.println(S);
        printWriter.print("  DISPLAY_MIN_WIDTH: ");
        printWriter.println(T);
        printWriter.print("  mSmallestScreenWidthDp: ");
        printWriter.println(this.z);
        ViewRootImpl viewRootImpl = (ViewRootImpl) this.f6783r.getRootSurfaceControl();
        if (viewRootImpl != null) {
            viewRootImpl.dump(" ", printWriter);
        } else {
            printWriter.print(Utils.d0(this.f6783r, null, ""));
        }
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.r
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).h(printWriter, "    ");
            }
        });
        printWriter.println("");
        TouchHelper touchHelper = this.B;
        if (touchHelper != null) {
            touchHelper.d(printWriter);
        }
        printWriter.println("");
        SettingsListener.a(printWriter);
        printWriter.println("");
        VirtualHandleAssistController.E().A(printWriter);
        IndicateWindowController.s(this.u).r(fileDescriptor, printWriter, strArr);
        MeditationController.s().p(fileDescriptor, printWriter, strArr);
        TimerMgr.r().q(printWriter);
        ThemeController.m().l(printWriter, "    ");
        PerformanceMonitorController.getInstance(this.u).dump(printWriter);
    }

    @Override // cn.nubia.gameassist.panel.TouchHelper.OnTouchHelperCallback
    public void d() {
        this.f6780o.post(new Runnable() { // from class: cn.nubia.gameassist.panel.h
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.s0();
            }
        });
    }

    public boolean d0() {
        if (this.f6783r == null) {
            return false;
        }
        return !r0.isOpened();
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if ("onHandleOperate".equals(str)) {
            if (SystemMgr.y(this.u).f16568k || "cn.nubia.gameassist".equals(SystemMgr.t())) {
                GaLog.k("GameAssistWindowManager", "black window exist, do not pull panel");
                ToastUtil.a(this.u.getString(R.string.game_assist_tips_pls_quit_setting));
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.y < 200) {
                GaLog.k("GameAssistWindowManager", "the time interval is too short");
                return;
            }
            this.y = elapsedRealtime;
            if (W) {
                g0("onHandleSleep");
            } else {
                u0("onHandleAwake");
            }
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged, reason: merged with bridge method [inline-methods] */
    public void m0(final boolean z) {
        if (!this.f6780o.getLooper().isCurrentThread()) {
            this.f6780o.post(new Runnable() { // from class: cn.nubia.gameassist.panel.c
                @Override // java.lang.Runnable
                public final void run() {
                    GameAssistWindowManager.this.m0(z);
                }
            });
            return;
        }
        G0();
        if (z) {
            this.f6780o.removeCallbacks(this.K);
            GameAssistRootView gameAssistRootView = this.f6783r;
            if (gameAssistRootView != null) {
                gameAssistRootView.inflateContent();
            }
        } else {
            this.f6780o.postDelayed(this.K, 10000L);
        }
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.n
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).G(z);
            }
        });
        TileHost tileHost = this.w;
        if (tileHost != null) {
            tileHost.t(z);
        }
        if (z || !W) {
            return;
        }
        g0("GameSceneStateChanged");
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.v
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).H();
            }
        });
        PluginUtils.f(this.u).j();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f6783r.resetMiddleLayoutChangeList();
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.x
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).I();
            }
        });
        UserGuideController.e(this.u).z();
        PluginUtils.f(this.u).k();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.y
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).J();
            }
        });
        PluginUtils.f(this.u).l();
    }

    @Override // cn.nubia.gameassist.panel.TouchHelper.OnTouchHelperCallback
    public void pilferPointers() {
        EventDispatcher eventDispatcher = this.A;
        if (eventDispatcher != null) {
            eventDispatcher.i();
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.j("GameAssistWindowManager", "onChange() uri = " + uri + ",selfChange = " + z);
        if (Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS).equals(uri)) {
            int c2 = GameKeysWrapper.b().c(this.u);
            boolean z2 = (c2 & 1) != 0;
            GaLog.j("GameAssistWindowManager", "onChange dbValue:" + c2 + " " + Integer.toBinaryString(c2) + " legacyGame=" + z2);
            if (z2 != X) {
                X = z2;
                g0("gamekey");
            }
        }
    }

    public void x0() {
        X();
        this.f6779n.post(new Runnable() { // from class: cn.nubia.gameassist.panel.w
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistWindowManager.this.j0();
            }
        });
    }

    public void y0(final Configuration configuration) {
        GaLog.a("GameAssistWindowManager", "onConfigurationChanged add=" + this.E + " SystemMgr.sIsGameScene = " + SystemMgr.G + " orientation=" + configuration.orientation);
        F0(configuration, this.C);
        M(new Consumer() { // from class: cn.nubia.gameassist.panel.u
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BaseViewController) obj).F(configuration);
            }
        });
    }
}
