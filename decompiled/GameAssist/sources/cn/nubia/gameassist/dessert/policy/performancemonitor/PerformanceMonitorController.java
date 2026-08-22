package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import androidx.annotation.VisibleForTesting;
import cn.nubia.hostassist.HostMonitor;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.PrintWriter;
import java.util.Locale;

/* loaded from: classes.dex */
public class PerformanceMonitorController implements GameMonitor.Callback, HostMonitor.Callback, IGameAssistCommander {

    /* renamed from: s, reason: collision with root package name */
    private static volatile PerformanceMonitorController f6396s;

    /* renamed from: c, reason: collision with root package name */
    private Context f6397c;

    /* renamed from: h, reason: collision with root package name */
    private Context f6398h;

    /* renamed from: i, reason: collision with root package name */
    private Handler f6399i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f6400j;

    /* renamed from: l, reason: collision with root package name */
    private int f6402l;

    /* renamed from: m, reason: collision with root package name */
    private int f6403m;
    public long mTrackBeginTime;

    /* renamed from: n, reason: collision with root package name */
    private boolean f6404n;

    /* renamed from: o, reason: collision with root package name */
    private PerformanceMonitorWindowController f6405o;

    /* renamed from: p, reason: collision with root package name */
    private PerformanceMonitorWindowController f6406p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f6407q;

    /* renamed from: k, reason: collision with root package name */
    private String f6401k = "";

    /* renamed from: r, reason: collision with root package name */
    private Runnable f6408r = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController.3
        @Override // java.lang.Runnable
        public void run() {
            if (PerformanceMonitorController.this.f6405o != null) {
                GaLog.a("PerformanceMonitorController", "onGameStart: mPerformanceMonitorWindow.isWindowAdd() = " + PerformanceMonitorController.this.f6405o.getWindow().isWindowAdd());
            }
            if (PerformanceMonitorController.this.isPerformanceMonitorSwitchOpened()) {
                PerformanceMonitorController.this.register(false);
            }
        }
    };

    private PerformanceMonitorController(Context context) {
        this.f6397c = context;
        if (Settings.Global.getInt(context.getContentResolver(), "framerate_display", 0) == 1) {
            GaLog.a("PerformanceMonitorController", "new PerformanceMonitor replaced FrameRateDisplay.");
            Settings.Global.putInt(this.f6397c.getContentResolver(), "framerate_display", 0);
        }
        int i2 = Settings.Global.getInt(this.f6397c.getContentResolver(), "performance_monitor", 0);
        if (i2 == 1) {
            SharedPreferencesUtil.k(context).U("spf_performance_monitor", i2);
        }
        this.f6399i = new Handler(ThreadManager.c().e());
        SystemMgr.y(this.f6397c).h(this);
        SystemMgr.y(this.f6397c).o(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        GaLog.e("PerformanceMonitorController", "addFloatView mIsGameStopped = " + this.f6400j);
        PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
        if (performanceMonitorWindowController == null || this.f6400j) {
            return;
        }
        performanceMonitorWindowController.addFloatView(SystemMgr.t());
    }

    public static PerformanceMonitorController getInstance(Context context) {
        if (f6396s == null) {
            synchronized (PerformanceMonitorController.class) {
                try {
                    if (f6396s == null) {
                        f6396s = new PerformanceMonitorController(context);
                    }
                } finally {
                }
            }
        }
        return f6396s;
    }

    private void h(Runnable runnable) {
        if (Looper.myLooper() == this.f6399i.getLooper()) {
            runnable.run();
        } else {
            this.f6399i.post(runnable);
        }
    }

    private String i() {
        PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
        if (performanceMonitorWindowController == null) {
            return "";
        }
        int clickIndex = performanceMonitorWindowController.getWindow().getClickIndex();
        return clickIndex != 0 ? clickIndex != 1 ? clickIndex != 2 ? clickIndex != 3 ? clickIndex != 4 ? "" : "play_time" : "electricity" : "speed" : "framerate" : "current_time";
    }

    private String j() {
        long uptimeMillis = SystemClock.uptimeMillis() - this.mTrackBeginTime;
        return uptimeMillis == 0 ? "0" : String.format("%.1f", Float.valueOf((uptimeMillis * 1.0f) / 1000.0f));
    }

    private void k() {
        if (this.f6406p == null) {
            PerformanceMonitorWindowController performanceMonitorWindowController = new PerformanceMonitorWindowController(this.f6397c, this.f6407q);
            this.f6406p = performanceMonitorWindowController;
            Context context = this.f6398h;
            if (context != null) {
                performanceMonitorWindowController.addHostWindow(context, this.f6407q);
            }
        }
    }

    private void l() {
        k();
        this.f6406p.removeFloatView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Bundle bundle = new Bundle();
        bundle.putString("app_name", SystemMgr.t());
        PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
        if (performanceMonitorWindowController == null || !performanceMonitorWindowController.getWindow().getIsViewGroupShorten()) {
            bundle.putString("size", "all");
        } else {
            bundle.putString("size", "single");
            bundle.putString(Constants.EXTRA_ITEM, i());
        }
        bundle.putString("duration", j());
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "performance_board_used", bundle);
    }

    private void n() {
        GaLog.a("PerformanceMonitorController", "updateFullScreen  mIsFullScreen = " + this.f6407q);
        PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
        if (performanceMonitorWindowController != null) {
            performanceMonitorWindowController.updateFullScreen(this.f6407q);
        }
        PerformanceMonitorWindowController performanceMonitorWindowController2 = this.f6406p;
        if (performanceMonitorWindowController2 != null) {
            performanceMonitorWindowController2.updateFullScreen(this.f6407q);
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("    PerformanceMonitorController:");
        printWriter.println("        isPerformanceMonitorSwitchOpened=" + isPerformanceMonitorSwitchOpened());
        printWriter.println("        isHostPerformanceMonitorSwitchOpened=" + isHostPerformanceMonitorSwitchOpened());
        printWriter.println("        mIsFullScreen=" + this.f6407q);
        PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
        if (performanceMonitorWindowController != null) {
            performanceMonitorWindowController.getWindow().dump(printWriter);
        }
        PerformanceMonitorWindowController performanceMonitorWindowController2 = this.f6406p;
        if (performanceMonitorWindowController2 != null) {
            performanceMonitorWindowController2.getWindow().dump(printWriter);
        }
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        GaLog.a("PerformanceMonitorController", "executive name = " + str + ", mIsFullScreen = " + this.f6407q);
        if ("statusbar_show".equals(str)) {
            this.f6407q = false;
            n();
        } else if ("statusbar_hide".equals(str)) {
            this.f6407q = true;
            n();
        }
    }

    public boolean isHostPerformanceMonitorSwitchOpened() {
        return Settings.Global.getInt(this.f6397c.getContentResolver(), "gamebox_mirror_displayid", 0) > 0 && Settings.Global.getInt(this.f6397c.getContentResolver(), "host_performance_monitor", 0) > 0;
    }

    public boolean isPerformanceMonitorSwitchOpened() {
        return SharedPreferencesUtil.k(this.f6397c).l("spf_performance_monitor", 0) > 0;
    }

    @VisibleForTesting
    public boolean isWindowAdd() {
        PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
        if (performanceMonitorWindowController == null) {
            return false;
        }
        return performanceMonitorWindowController.getWindow().isWindowAdd();
    }

    public void onConfigurationChanged(Configuration configuration, boolean z) {
        PerformanceMonitorWindowController performanceMonitorWindowController;
        if (isPerformanceMonitorSwitchOpened()) {
            Locale locale = configuration.locale;
            String language = Locale.getDefault().getLanguage();
            if (!this.f6401k.equals(language) && (performanceMonitorWindowController = this.f6405o) != null) {
                performanceMonitorWindowController.getWindow().onLanguageChanged(language);
                this.f6401k = language;
            }
            int i2 = configuration.orientation;
            if (this.f6403m != i2 && z) {
                GaLog.a("PerformanceMonitorController", "onHostOrientationChanged:");
                k();
                this.f6406p.getWindow().onOrientationChanged(i2);
                this.f6403m = i2;
                return;
            }
            if (this.f6402l != i2) {
                GaLog.a("PerformanceMonitorController", "onOrientationChanged:");
                PerformanceMonitorWindowController performanceMonitorWindowController2 = this.f6405o;
                if (performanceMonitorWindowController2 != null) {
                    performanceMonitorWindowController2.getWindow().onOrientationChanged(i2);
                }
                this.f6402l = i2;
            }
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onFocuesWindowChanged(AbsGameAssistToken.FocuesWindow focuesWindow) {
        super.onFocuesWindowChanged(focuesWindow);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onFullscreenActivityChange */
    public /* bridge */ /* synthetic */ void p(ComponentName componentName) {
        super.p(componentName);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public /* bridge */ /* synthetic */ void m0(boolean z) {
        super.m0(z);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        if (isPerformanceMonitorSwitchOpened()) {
            if (this.f6405o == null) {
                this.f6405o = new PerformanceMonitorWindowController(this.f6397c, this.f6407q);
            }
            this.f6400j = false;
            this.f6399i.removeCallbacks(this.f6408r);
            GaLog.a("PerformanceMonitorController", "onGameStart: mIsFullActivityFirstCreate = " + this.f6404n);
            this.f6399i.postDelayed(this.f6408r, this.f6404n ? 1100L : 800L);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        if (!isPerformanceMonitorSwitchOpened()) {
            this.f6405o = null;
            return;
        }
        this.f6400j = true;
        this.f6404n = false;
        this.f6399i.removeCallbacks(this.f6408r);
        unregister(false);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        if (isPerformanceMonitorSwitchOpened()) {
            GaLog.a("PerformanceMonitorController", "onGameUpdate: mCurPkg = " + SystemMgr.t());
            PerformanceMonitorWindowController performanceMonitorWindowController = this.f6405o;
            if (performanceMonitorWindowController != null) {
                performanceMonitorWindowController.updateCurrPkg(SystemMgr.t());
            }
        }
    }

    public void onHostChange(boolean z, String str) {
        GaLog.a("PerformanceMonitorController", "onHostChange show=" + z);
        if (!z) {
            l();
        } else if (isHostPerformanceMonitorSwitchOpened()) {
            k();
            this.f6406p.addFloatView(str);
        }
    }

    @Override // cn.nubia.hostassist.HostMonitor.Callback
    public /* bridge */ /* synthetic */ void onHostStart(boolean z) {
        super.onHostStart(z);
    }

    @Override // cn.nubia.hostassist.HostMonitor.Callback
    public void onHostStop() {
        GaLog.a("PerformanceMonitorController", "onHostStop");
        l();
        if (isHostPerformanceMonitorSwitchOpened()) {
            return;
        }
        this.f6406p = null;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onLauncherFirstPackage(String str) {
        super.onLauncherFirstPackage(str);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onProjectionActivityResumed(ComponentName componentName, int i2) {
        super.onProjectionActivityResumed(componentName, i2);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onResumeFullscreenActivityPidChanged() {
        super.onResumeFullscreenActivityPidChanged();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onShowTipAnimation(GameCheck.GameAppInfo gameAppInfo) {
        super.onShowTipAnimation(gameAppInfo);
    }

    public void register(final boolean z) {
        h(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController.1
            @Override // java.lang.Runnable
            public void run() {
                if (PerformanceMonitorController.this.f6405o == null) {
                    PerformanceMonitorController.this.f6405o = new PerformanceMonitorWindowController(PerformanceMonitorController.this.f6397c, PerformanceMonitorController.this.f6407q);
                }
                if (PerformanceMonitorController.this.f6405o.getWindow().isWindowAdd()) {
                    GaLog.b("PerformanceMonitorController", "register illegal, because window has been added!");
                    return;
                }
                GaLog.a("PerformanceMonitorController", "register");
                PerformanceMonitorController.this.g();
                if (z) {
                    PerformanceMonitorController.this.mTrackBeginTime = SystemClock.uptimeMillis();
                }
            }
        });
    }

    public void unregister(final boolean z) {
        h(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController.2
            @Override // java.lang.Runnable
            public void run() {
                if (PerformanceMonitorController.this.f6405o != null) {
                    if (!PerformanceMonitorController.this.f6405o.getWindow().isWindowAdd()) {
                        GaLog.b("PerformanceMonitorController", "unregister illegal, because window does not exist!");
                        return;
                    } else {
                        GaLog.a("PerformanceMonitorController", "unregister");
                        PerformanceMonitorController.this.f6405o.removeFloatView();
                    }
                }
                if (z) {
                    PerformanceMonitorController.this.m();
                }
            }
        });
    }

    public void updateHostScreenSize(int i2, int i3) {
        k();
        this.f6406p.updateHostScreenSize(i2, i3);
    }

    public void onHostStart(Context context) {
        GaLog.a("PerformanceMonitorController", "onHostModeStart");
        if (this.f6406p == null) {
            this.f6406p = new PerformanceMonitorWindowController(this.f6397c, this.f6407q);
        }
        if (this.f6398h != context) {
            this.f6398h = context;
            this.f6406p.addHostWindow(context, this.f6407q);
        }
    }
}
