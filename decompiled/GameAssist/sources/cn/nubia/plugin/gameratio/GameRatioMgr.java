package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.hostassist.HostAssistMgr;
import cn.nubia.plugin.gameratio.GameRatioGuideWindow;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameRatioMgr implements GameMonitor.Callback, DumpController.Dump {

    /* renamed from: q, reason: collision with root package name */
    private static volatile GameRatioMgr f8395q;

    /* renamed from: r, reason: collision with root package name */
    static final boolean f8396r;

    /* renamed from: s, reason: collision with root package name */
    static final boolean f8397s;

    /* renamed from: c, reason: collision with root package name */
    private GameRatioGuideWindow f8398c;

    /* renamed from: h, reason: collision with root package name */
    private GameRatioSettingsWindow f8399h;

    /* renamed from: i, reason: collision with root package name */
    private GameRatioDataMgr f8400i;

    /* renamed from: j, reason: collision with root package name */
    private GameRatioIndicatorWindow f8401j;

    /* renamed from: k, reason: collision with root package name */
    private Context f8402k;

    /* renamed from: l, reason: collision with root package name */
    private Handler f8403l;

    /* renamed from: m, reason: collision with root package name */
    private String f8404m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f8405n;

    /* renamed from: o, reason: collision with root package name */
    private String f8406o;

    /* renamed from: p, reason: collision with root package name */
    private String f8407p;

    static {
        boolean f2 = FoldMgr.f();
        f8396r = f2;
        f8397s = !f2;
    }

    private GameRatioMgr() {
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(String str) {
        String j2 = Utils.j();
        if (str.equals(j2)) {
            L(str);
            this.f8398c = null;
            return;
        }
        GaLog.b("GameRatio", "app " + j2 + " is different from previous app " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(GameRatioData gameRatioData) {
        this.f8400i.c(gameRatioData.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public void B() {
        String j2 = Utils.j();
        if (TextUtils.isEmpty(j2)) {
            GaLog.b("GameRatio", "get no current pkg");
        } else {
            L(j2);
        }
    }

    private void I() {
        CharSequence charSequence;
        if (TextUtils.isEmpty(this.f8404m)) {
            return;
        }
        Bundle bundle = new Bundle();
        try {
            charSequence = this.f8402k.getPackageManager().getApplicationLabel(this.f8402k.getPackageManager().getApplicationInfo(SystemMgr.A(this.f8404m), 128));
        } catch (PackageManager.NameNotFoundException unused) {
            GaLog.a("GameRatio", this.f8404m + " not found");
            charSequence = null;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            bundle.putCharSequence("app_name", charSequence);
        }
        bundle.putString("package_name", this.f8404m);
        bundle.putString("screen_orientation", GameRatioDataMgr.v(this.f8406o));
        bundle.putString("screen_ratio", GameRatioDataMgr.B(this.f8407p));
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "customize_display_used", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public void C(boolean z) {
        String j2 = Utils.j();
        if (TextUtils.isEmpty(j2)) {
            GaLog.b("GameRatio", "get no app when change state");
            return;
        }
        GaLog.e("GameRatio", "enable " + z + " for " + j2);
        if (!z) {
            this.f8399h.z(new GameRatioData(j2), new Consumer() { // from class: cn.nubia.plugin.gameratio.u
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameRatioMgr.this.E((GameRatioData) obj);
                }
            }, null);
            return;
        }
        if (f8396r && v()) {
            ToastUtil.a(this.f8402k.getString(R.string.gameratio_fold_mutex_virtual_game_handle));
            return;
        }
        if (!this.f8400i.s()) {
            L(j2);
            return;
        }
        if (this.f8398c == null) {
            this.f8398c = new GameRatioGuideWindow(this.f8402k, this.f8400i);
        }
        this.f8398c.p(j2);
        this.f8398c.o(new GameRatioGuideWindow.OnCloseListener() { // from class: cn.nubia.plugin.gameratio.t
            @Override // cn.nubia.plugin.gameratio.GameRatioGuideWindow.OnCloseListener
            public final void a(String str) {
                GameRatioMgr.this.D(str);
            }
        });
    }

    private void L(String str) {
        GameRatioDataMgr gameRatioDataMgr = this.f8400i;
        int i2 = gameRatioDataMgr.i(gameRatioDataMgr.k(str));
        GameRatioDataMgr gameRatioDataMgr2 = this.f8400i;
        this.f8399h.C(new GameRatioData(str, i2, gameRatioDataMgr2.j(gameRatioDataMgr2.o(str))));
    }

    private void M() {
        SharedPreferencesUtil k2 = SharedPreferencesUtil.k(this.f8402k);
        int h2 = k2.h();
        int i2 = 4;
        if (4 <= h2) {
            return;
        }
        if (h2 == 1) {
            if ("3.56".equals(this.f8400i.o("com.tencent.tmgp.sgame"))) {
                this.f8400i.E("com.tencent.tmgp.sgame", "");
                if (TextUtils.isEmpty(this.f8400i.k("com.tencent.tmgp.sgame"))) {
                    this.f8400i.c("com.tencent.tmgp.sgame");
                }
                GaLog.e("GameRatio", "update sgame game ratio size " + this.f8400i.o("com.tencent.tmgp.sgame") + ", enable " + this.f8400i.r("com.tencent.tmgp.sgame"));
            }
            GaLog.e("GameRatio", "update to the second version from " + h2);
            h2 = 2;
        }
        if (h2 == 2 || h2 == 3) {
            this.f8400i.c("com.tencent.mm");
            GaLog.e("GameRatio", "update to the forth version from " + h2);
        } else {
            i2 = h2;
        }
        k2.R(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public void w() {
        GameRatioGuideWindow gameRatioGuideWindow = this.f8398c;
        if (gameRatioGuideWindow != null) {
            gameRatioGuideWindow.h();
        }
        GameRatioSettingsWindow gameRatioSettingsWindow = this.f8399h;
        if (gameRatioSettingsWindow != null) {
            gameRatioSettingsWindow.p();
        }
    }

    private void o() {
        I();
        this.f8401j.t();
        this.f8400i.A(0);
        if ("com.tencent.tmgp.sgame".equals(this.f8404m)) {
            HostAssistMgr.n().E(false);
        }
    }

    private void p(String str) {
        this.f8406o = this.f8400i.k(str);
        String o2 = this.f8400i.o(str);
        this.f8407p = o2;
        if (TextUtils.isEmpty(o2)) {
            this.f8401j.t();
        } else {
            Utils.c0(str);
            this.f8401j.T(str, this.f8407p);
        }
        this.f8400i.A(1);
        if (!"com.tencent.tmgp.sgame".equals(str) || this.f8401j.f8387m <= 2.5f) {
            HostAssistMgr.n().E(false);
        } else {
            HostAssistMgr.n().E(true);
        }
    }

    public static GameRatioMgr q() {
        if (f8395q == null) {
            synchronized (GameRatioMgr.class) {
                try {
                    if (f8395q == null) {
                        f8395q = new GameRatioMgr();
                    }
                } finally {
                }
            }
        }
        return f8395q;
    }

    private void s() {
        this.f8402k = GameAssistApplication.j();
        this.f8403l = new Handler(Looper.getMainLooper());
        GameRatioDataMgr gameRatioDataMgr = new GameRatioDataMgr(this.f8402k);
        this.f8400i = gameRatioDataMgr;
        this.f8399h = new GameRatioSettingsWindow(this.f8402k, gameRatioDataMgr, this);
        this.f8401j = new GameRatioIndicatorWindow(this.f8402k);
        SystemMgr.y(this.f8402k).h(this);
        DumpController.c().a(this);
        M();
        if (f8396r) {
            FoldMgr.c().a(new FoldMgr.Callback() { // from class: cn.nubia.plugin.gameratio.q
                @Override // com.zte.gameassist.common.FoldMgr.Callback
                public final void onDisplayInUseStateChanged(int i2) {
                    GameRatioMgr.this.x(i2);
                }
            });
        }
    }

    private boolean v() {
        return Settings.Global.getInt(this.f8402k.getContentResolver(), "nubia_virtual_handle_enable", 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(int i2) {
        if (this.f8399h.u()) {
            this.f8399h.p();
        }
        GameRatioGuideWindow gameRatioGuideWindow = this.f8398c;
        if (gameRatioGuideWindow == null || !gameRatioGuideWindow.k()) {
            return;
        }
        this.f8398c.h();
    }

    public void F() {
        this.f8403l.post(new Runnable() { // from class: cn.nubia.plugin.gameratio.r
            @Override // java.lang.Runnable
            public final void run() {
                GameRatioMgr.this.B();
            }
        });
    }

    public void H(GameRatioCallback gameRatioCallback) {
        this.f8401j.L(gameRatioCallback);
    }

    public void J(final boolean z) {
        this.f8403l.post(new Runnable() { // from class: cn.nubia.plugin.gameratio.s
            @Override // java.lang.Runnable
            public final void run() {
                GameRatioMgr.this.C(z);
            }
        });
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.f8405n) {
            printWriter.println("GameRatio: enable");
            printWriter.print("  ori: ");
            printWriter.println(GameRatioDataMgr.v(this.f8406o));
            printWriter.print("  size: ");
            printWriter.println(GameRatioDataMgr.B(this.f8407p));
            printWriter.print("  port: ");
            printWriter.println(u());
            this.f8399h.r(printWriter);
            GameRatioGuideWindow gameRatioGuideWindow = this.f8398c;
            if (gameRatioGuideWindow != null) {
                gameRatioGuideWindow.i(printWriter);
            }
            this.f8401j.u(printWriter);
            printWriter.print("  version: ");
            printWriter.println(SharedPreferencesUtil.k(this.f8402k).h());
        }
    }

    public void k(GameRatioCallback gameRatioCallback) {
        this.f8401j.m(gameRatioCallback);
    }

    public void l(String str) {
        boolean z = this.f8405n;
        boolean r2 = this.f8400i.r(str);
        this.f8405n = r2;
        if (r2) {
            if (z) {
                I();
            }
            p(this.f8404m);
        } else if (z) {
            o();
        }
    }

    public void m() {
        this.f8403l.post(new Runnable() { // from class: cn.nubia.plugin.gameratio.v
            @Override // java.lang.Runnable
            public final void run() {
                GameRatioMgr.this.w();
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart, reason: merged with bridge method [inline-methods] */
    public void y() {
        if (!this.f8403l.getLooper().isCurrentThread()) {
            this.f8403l.post(new Runnable() { // from class: cn.nubia.plugin.gameratio.o
                @Override // java.lang.Runnable
                public final void run() {
                    GameRatioMgr.this.y();
                }
            });
            return;
        }
        String j2 = Utils.j();
        if (j2 == this.f8404m) {
            return;
        }
        this.f8404m = j2;
        boolean r2 = this.f8400i.r(j2);
        this.f8405n = r2;
        if (r2) {
            p(this.f8404m);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop, reason: merged with bridge method [inline-methods] */
    public void z() {
        if (!this.f8403l.getLooper().isCurrentThread()) {
            this.f8403l.post(new Runnable() { // from class: cn.nubia.plugin.gameratio.p
                @Override // java.lang.Runnable
                public final void run() {
                    GameRatioMgr.this.z();
                }
            });
            return;
        }
        this.f8399h.p();
        GameRatioGuideWindow gameRatioGuideWindow = this.f8398c;
        if (gameRatioGuideWindow != null) {
            gameRatioGuideWindow.h();
        }
        if (this.f8405n) {
            o();
            this.f8405n = false;
        }
        this.f8404m = null;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate, reason: merged with bridge method [inline-methods] */
    public void A() {
        if (!this.f8403l.getLooper().isCurrentThread()) {
            this.f8403l.post(new Runnable() { // from class: cn.nubia.plugin.gameratio.n
                @Override // java.lang.Runnable
                public final void run() {
                    GameRatioMgr.this.A();
                }
            });
            return;
        }
        String str = this.f8404m;
        z();
        y();
        String str2 = this.f8404m;
        if (str2 == null || str2.equals(str)) {
            return;
        }
        GaLog.a("GameRatio", "game update from " + str + " to " + this.f8404m);
    }

    public int r() {
        if (!this.f8405n || TextUtils.isEmpty(this.f8407p)) {
            return 0;
        }
        return this.f8401j.z();
    }

    public boolean t() {
        String j2 = Utils.j();
        if (!TextUtils.isEmpty(j2)) {
            return this.f8400i.r(j2);
        }
        GaLog.b("GameRatio", "get no app when just enable");
        return false;
    }

    public boolean u() {
        if (this.f8405n) {
            return "2".equals(this.f8406o);
        }
        return false;
    }
}
