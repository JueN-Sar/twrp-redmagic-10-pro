package cn.nubia.gameassist;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.gameassist.common.GameDurationManager;
import cn.nubia.gameassist.common.GlobalExceptionHandler;
import cn.nubia.gameassist.dessert.TilesManager;
import cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController;
import cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController;
import cn.nubia.gameassist.fold.FoldBigMgr;
import cn.nubia.gameassist.install.InstallListener;
import cn.nubia.gameassist.install.PackageInstallReceiver;
import cn.nubia.gameassist.meditationmode.MeditationController;
import cn.nubia.gameassist.onemorething.OneMoreThingManager;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.plugin.config.UpdatePluginConfigService;
import cn.nubia.gameassist.plugin.policy.AiSpeakerController;
import cn.nubia.gameassist.plugin.policy.GameVoiceController;
import cn.nubia.gameassist.search.SearchWindowManager;
import cn.nubia.gameassist.tips.TipsUtils;
import cn.nubia.gameassist.tips.learn.UserGuideController;
import cn.nubia.gameassist.utils.ThreadPoolUtils;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.hostassist.HostAssistMgr;
import cn.nubia.magicwindow.MagicWindowMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import cn.nubia.plugin.gameshader.ShaderMgr;
import cn.nubia.plugin.timer.TimerMgr;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.streamgame.StreamGameMgr;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.common.DensityHelper;
import com.zte.gameassist.common.DisplayMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.TraceWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameAssistApplication extends BaseApplication implements GameMonitor.Callback {

    /* renamed from: o, reason: collision with root package name */
    private static GameAssistApplication f6079o;

    /* renamed from: p, reason: collision with root package name */
    private static PackageInstallReceiver f6080p = new PackageInstallReceiver();

    /* renamed from: h, reason: collision with root package name */
    private GameAssistWindowManager f6081h;

    /* renamed from: i, reason: collision with root package name */
    private TilesManager f6082i;

    /* renamed from: k, reason: collision with root package name */
    private Locale f6084k;

    /* renamed from: l, reason: collision with root package name */
    private List f6085l;

    /* renamed from: n, reason: collision with root package name */
    private boolean f6087n;

    /* renamed from: j, reason: collision with root package name */
    private boolean f6083j = false;

    /* renamed from: m, reason: collision with root package name */
    public Handler f6086m = new Handler(this, Looper.getMainLooper()) { // from class: cn.nubia.gameassist.GameAssistApplication.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj = message.obj;
            if (obj instanceof Runnable) {
                ThreadPoolUtils.b((Runnable) obj);
            }
        }
    };

    private void A() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        BaseApplication.f16332c.registerReceiver(f6080p, intentFilter, 2);
    }

    private void B() {
        List list = this.f6085l;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.f6085l.forEach(new Consumer() { // from class: cn.nubia.gameassist.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Router.unregisterComponent((String) obj);
            }
        });
    }

    private void C() {
        new Handler(ThreadManager.c().a()).post(new Runnable() { // from class: cn.nubia.gameassist.f
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistApplication.q();
            }
        });
    }

    private void g() {
        if (SharedPreferencesUtil.k(BaseApplication.f16332c).D()) {
            Settings.System.putInt(BaseApplication.f16332c.getContentResolver(), "screen_brightness_mode", 1);
            GaLog.a("GameAssistApplication", "reset auto brightness");
            SharedPreferencesUtil.k(BaseApplication.f16332c).L(false);
        }
    }

    public static GameAssistWindowManager i() {
        return j().f6081h;
    }

    public static GameAssistApplication j() {
        return f6079o;
    }

    private void m() {
        A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void n() {
        VirtualHandleAssistController.E().F(BaseApplication.f16332c);
        if (ZteFeature.isSupportAISpeaker()) {
            AiSpeakerController.f().g(BaseApplication.f16332c);
        }
        if (ZteFeature.isSupportVoiceController()) {
            GameVoiceController.f().h(BaseApplication.f16332c);
        }
        Settings.Global.putInt(BaseApplication.f16332c.getContentResolver(), "nubia_game_assist_show", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o(String str) {
        try {
            Router.registerComponent(str, BaseApplication.f16332c);
        } catch (Throwable th) {
            GaLog.b("GameAssistApplication", "loadComponent name=" + str + " e=" + th);
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p() {
        try {
            DistributeBusMgr.getInstance().init(this);
            DistributeBusMgr.getInstance().publishService(true);
            DistributeBusMgr.getInstance().subscribeService();
            MultiSubScreenUtils.A(this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q() {
        WechatHelper.a().g();
    }

    private void r() {
        ArrayList a2 = DefaultUtil.a(BaseApplication.f16332c.getString(cn.nubia.componentcenter.R.string.component_application_list));
        this.f6085l = a2;
        y(a2);
        w(this.f6085l);
        x(this.f6085l);
        this.f6085l.forEach(new Consumer() { // from class: cn.nubia.gameassist.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GameAssistApplication.o((String) obj);
            }
        });
    }

    private void s() {
        if (ZteFeature.isSupportMultiSubScreen()) {
            new Handler(ThreadManager.c().f()).post(new Runnable() { // from class: cn.nubia.gameassist.e
                @Override // java.lang.Runnable
                public final void run() {
                    GameAssistApplication.this.p();
                }
            });
        }
    }

    public static void t(Runnable runnable, long j2) {
        f6079o.f6086m.postDelayed(runnable, j2);
    }

    public static void u(Runnable runnable) {
        f6079o.f6086m.removeCallbacks(runnable);
    }

    private void w(List list) {
        if (ZteFeature.isSupportLowSugar()) {
            return;
        }
        list.remove("com.zte.gameassist.lowsugar.LowSugarComApplication");
    }

    private void x(List list) {
        if (ZteFeature.isSupportGameBenefit()) {
            return;
        }
        list.remove("cn.nubia.neogamelib.NeoGameLibComApplication");
    }

    private void y(List list) {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 >= 36;
        GaLog.a("GameAssistApplication", "removeWlanIndicator,version=" + i2);
        if ((ZteFeature.isRedMagicProduct() || z) && !ZteFeature.IS_INTER_VERSION) {
            return;
        }
        list.remove("com.zte.performanceindicator.PerfIndicatorComApplication");
    }

    private void z() {
        Settings.Global.putInt(BaseApplication.f16332c.getContentResolver(), "active_mode_on", 0);
    }

    public void f(InstallListener installListener) {
        f6080p.a(installListener);
    }

    public Locale h() {
        return this.f6084k;
    }

    public synchronized void k() {
        if (this.f6083j) {
            return;
        }
        try {
            try {
                TraceWrapper.traceBegin(8L, "gameassist_init");
                this.f6083j = true;
                m();
                HostAssistMgr.n();
                GameDurationManager.n();
                UserGuideController.e(BaseApplication.f16332c);
                UpdatePluginConfigService.c(this);
                this.f6086m.post(new Runnable() { // from class: cn.nubia.gameassist.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        GameAssistApplication.n();
                    }
                });
                MeditationController.s();
                GaLog.e("GameAssistApplication", "init");
                if (ZteFeature.isSupportSuperResolutionOld()) {
                    PluginUtils.f(BaseApplication.f16332c).m();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
        }
    }

    public synchronized void l() {
        if (this.f6081h != null) {
            return;
        }
        ContextWrapper.updateDisplay(BaseApplication.f16332c);
        TimerMgr.r();
        ShaderMgr.t();
        GameRatioMgr.q();
        PerformanceMonitorController.getInstance(BaseApplication.f16332c);
        GameAssistWindowManager O = GameAssistWindowManager.O(BaseApplication.f16332c);
        this.f6081h = O;
        O.x0();
        GaLog.e("GameAssistApplication", "initGameAssistWindowManager");
    }

    @Override // com.zte.gameassist.BaseApplication, android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        GameAssistWindowManager gameAssistWindowManager;
        super.onConfigurationChanged(configuration);
        GaLog.a("GameAssistApplication", "onConfigurationChanged " + this.f6087n);
        BaseApplication.f16332c = getApplicationContext();
        Locale locale = configuration.getLocales().get(0);
        if (!locale.equals(this.f6084k)) {
            TilesManager.j().p();
            this.f6084k = locale;
            OneMoreThingManager.g().m(true);
        }
        if (this.f6087n) {
            Router.onConfigurationChanged(configuration);
            DensityHelper.d(BaseApplication.f16332c);
            InflaterHelper.m(this);
            if (this.f6083j && (gameAssistWindowManager = this.f6081h) != null) {
                gameAssistWindowManager.y0(configuration);
            }
            PerformanceMonitorController.getInstance(BaseApplication.f16332c).onConfigurationChanged(configuration, false);
            SearchWindowManager.i(BaseApplication.f16332c).t(configuration);
            RotationMgr.e(BaseApplication.f16332c).m(configuration, false);
            MeditationController.s().E(configuration);
            if (ZteFeature.isSupportSuperResolutionOld()) {
                try {
                    PluginUtils.f(BaseApplication.f16332c).m();
                } catch (Exception e2) {
                    GaLog.c("GameAssistApplication", "parseResolutionConfig e：", e2);
                }
            }
        }
    }

    @Override // com.zte.gameassist.BaseApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        f6079o = this;
        Context applicationContext = getApplicationContext();
        BaseApplication.f16332c = applicationContext;
        applicationContext.setTheme(com.zte.gameassist.common.R.style.GameAssist_Theme_ZTE_Light);
        this.f6084k = BaseApplication.f16332c.getResources().getConfiguration().getLocales().get(0);
        String myProcessName = Process.myProcessName();
        if (myProcessName != null) {
            if (myProcessName.startsWith(getPackageName() + ":")) {
                GaLog.e("GameAssistApplication", "onCreate processName:" + myProcessName + " Pid:" + Process.myPid());
                return;
            }
        }
        if (Utils.f7699d) {
            GaLog.b("GameAssistApplication", "sUnitTesting");
            return;
        }
        GaLog.e("GameAssistApplication", "load component start");
        r();
        GaLog.e("GameAssistApplication", "load component end");
        this.f6087n = true;
        C();
        new GlobalExceptionHandler().a();
        SystemMgr y = SystemMgr.y(BaseApplication.f16332c);
        DensityHelper.c(BaseApplication.f16332c);
        NubiaTrackManager.p().r(BaseApplication.f16332c);
        GameAssistWindowManager.O(BaseApplication.f16332c);
        RotationMgr.e(BaseApplication.f16332c).i();
        DisplayMgr.d().e();
        FoldMgr.c().d();
        if (ZteFeature.isSupportFoldBig()) {
            FoldBigMgr.c().d();
        }
        if (ZteFeature.isSupportStreamGame()) {
            StreamGameMgr.e();
        }
        y.Z(new SystemMgr.Pioneer() { // from class: cn.nubia.gameassist.a
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistApplication.this.k();
            }
        });
        y.h(this);
        TipsUtils.f7518m.j(this);
        z();
        g();
        s();
        if (ZteFeature.isSupportMagicWindow()) {
            MagicWindowMgr.l();
        }
        TpEdgeMisOperation.a(BaseApplication.f16332c);
        Utils.T(BaseApplication.f16332c);
        GaLog.e("GameAssistApplication", "onCreate processName:" + myProcessName + " isGameAssistServiceProcess=" + this.f6087n);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        l();
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        GaLog.e("GameAssistApplication", "onTerminate()");
        if (Utils.f7699d) {
            GaLog.b("GameAssistApplication", "sUnitTesting");
            return;
        }
        B();
        f6080p.b();
        this.f6082i = null;
        this.f6081h = null;
    }

    public void v(InstallListener installListener) {
        f6080p.d(installListener);
    }
}
