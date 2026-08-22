package cn.nubia.plugin.screenextraction;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.ArrayMap;
import android.widget.Toast;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.plugin.gameshader.ShaderUtils;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import cn.nubia.plugin.screenextraction.common.SystemSettingsObserver;
import cn.nubia.plugin.screenextraction.controller.HelperWindowController;
import cn.nubia.plugin.screenextraction.controller.IWindowController;
import cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController;
import cn.nubia.plugin.screenextraction.controller.SettingsWindowController;
import cn.nubia.plugin.screenextraction.utils.DefaultUtils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.ext.common.MutableData;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.ContextWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import l.a;

/* loaded from: classes.dex */
public class ScreenExtractionManager implements GameMonitor.Callback {

    /* renamed from: p, reason: collision with root package name */
    private static volatile ScreenExtractionManager f8564p;

    /* renamed from: c, reason: collision with root package name */
    private final Handler f8565c = new Handler(ThreadManager.c().b());

    /* renamed from: h, reason: collision with root package name */
    private final List f8566h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private final Context f8567i;

    /* renamed from: j, reason: collision with root package name */
    private SystemSettingsObserver f8568j;

    /* renamed from: k, reason: collision with root package name */
    private ScreenExtractionData f8569k;

    /* renamed from: l, reason: collision with root package name */
    private final Map f8570l;

    /* renamed from: m, reason: collision with root package name */
    private String f8571m;

    /* renamed from: n, reason: collision with root package name */
    private final Handler f8572n;

    /* renamed from: o, reason: collision with root package name */
    private final MutableData.Observer f8573o;

    public interface Callback {
        void l();
    }

    private ScreenExtractionManager() {
        ArrayMap arrayMap = new ArrayMap();
        this.f8570l = arrayMap;
        this.f8571m = "";
        this.f8573o = new MutableData.Observer() { // from class: l.l
            @Override // com.zte.gameassist.ext.common.MutableData.Observer
            public final void a(Object obj) {
                ScreenExtractionManager.this.P(((Integer) obj).intValue());
            }
        };
        Context context = ContextWrapper.getContext();
        this.f8567i = context;
        Handler handler = new Handler(Looper.getMainLooper());
        this.f8572n = handler;
        this.f8568j = new SystemSettingsObserver(this);
        arrayMap.put(SettingsWindowController.class, new SettingsWindowController(this));
        arrayMap.put(InterceptTouchWindowController.class, new InterceptTouchWindowController(this));
        arrayMap.put(HelperWindowController.class, new HelperWindowController(this));
        SystemMgr.y(context).h(this);
        handler.post(new Runnable() { // from class: l.m
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.E();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(Callback callback) {
        if (this.f8566h.contains(callback)) {
            return;
        }
        this.f8566h.add(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        X(false, "forceHideWindow");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G() {
        this.f8566h.forEach(new Consumer() { // from class: l.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ScreenExtractionManager.Callback) obj).l();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H(int i2) {
        X(y(this.f8571m), "onFoldStateChanged_" + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(final boolean z) {
        this.f8570l.forEach(new BiConsumer() { // from class: l.e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IWindowController) obj2).e(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K() {
        X(false, "gamestop");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(boolean z) {
        X(z, "gameupdate");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(Callback callback) {
        if (this.f8566h.contains(callback)) {
            this.f8566h.remove(callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(String str) {
        if (DefaultUtils.c(this.f8567i, str) != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("package_name", str);
                bundle.putString("app_name", NubiaTrackManager.o(this.f8567i, str));
                NubiaTrackManager.p().x("cn.nubia.gamelauncher", "screen_extraction_used", bundle);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(final int i2) {
        if (SystemMgr.H()) {
            if (((SettingsWindowController) x(SettingsWindowController.class)).v()) {
                ((SettingsWindowController) x(SettingsWindowController.class)).b("onFoldStateChanged");
            }
            if (y(this.f8571m)) {
                ShaderUtils.k(this.f8571m, false);
                GaLog.a("ScreenExtraction", "onFoldStateChanged: " + this.f8571m + " " + DefaultUtils.a());
            }
            this.f8572n.postDelayed(new Runnable() { // from class: l.d
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenExtractionManager.this.H(i2);
                }
            }, 100L);
        }
    }

    public static ScreenExtractionManager w() {
        if (f8564p == null) {
            synchronized (ScreenExtractionManager.class) {
                try {
                    if (f8564p == null) {
                        f8564p = new ScreenExtractionManager();
                    }
                } finally {
                }
            }
        }
        return f8564p;
    }

    public void O() {
        this.f8572n.post(new Runnable() { // from class: l.h
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.G();
            }
        });
    }

    public void Q(ScreenExtractionData screenExtractionData) {
        ScreenExtractionData screenExtractionData2 = this.f8569k;
        boolean z = (screenExtractionData2 == null || screenExtractionData == null || screenExtractionData2.e() == null || this.f8569k.e().equals(screenExtractionData.e())) ? false : true;
        boolean v = ((SettingsWindowController) x(SettingsWindowController.class)).v();
        if (this.f8569k == null || z || GaLog.i()) {
            GaLog.e("ScreenExtraction", "previewScreenExtraction data=" + screenExtractionData + " isGameScene=" + SystemMgr.H() + " mPreviewData=" + this.f8569k + " ShowSettingWindow=" + v);
        }
        if (screenExtractionData == null || !SystemMgr.H() || (this.f8568j.f() && !v)) {
            if (this.f8569k != null) {
                this.f8569k = null;
                ShaderUtils.m("");
                ShaderUtils.k(this.f8571m, false);
                Settings.Global.putString(this.f8567i.getContentResolver(), "gameassist_fixed_look_data", "");
                ((InterceptTouchWindowController) x(InterceptTouchWindowController.class)).N(null);
                return;
            }
            return;
        }
        ScreenExtractionData screenExtractionData3 = this.f8569k;
        boolean z2 = screenExtractionData3 == null || !screenExtractionData3.equals(screenExtractionData);
        this.f8569k = screenExtractionData;
        if (z2) {
            ShaderUtils.m(screenExtractionData.f() + (v ? "-1" : "-0"));
            ShaderUtils.k(this.f8571m, true);
            Settings.Global.putString(this.f8567i.getContentResolver(), "gameassist_fixed_look_data", screenExtractionData.d() == 0 ? screenExtractionData.i() : "");
        }
        InterceptTouchWindowController interceptTouchWindowController = (InterceptTouchWindowController) x(InterceptTouchWindowController.class);
        if (v) {
            screenExtractionData = null;
        }
        interceptTouchWindowController.N(screenExtractionData);
    }

    public void R(final Callback callback) {
        this.f8572n.post(new Runnable() { // from class: l.j
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.M(callback);
            }
        });
    }

    public void S(final String str) {
        this.f8565c.post(new Runnable() { // from class: l.p
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.N(str);
            }
        });
    }

    public void T(String str) {
        U(str, null);
    }

    public void U(String str, Runnable runnable) {
        V(str, runnable, false);
    }

    public void V(String str, Runnable runnable, boolean z) {
        if (!y(str)) {
            Toast.makeText(this.f8567i, R.string.ic_qs_function_enable, 0).show();
            return;
        }
        if (DefaultUtils.b(this.f8567i)) {
            ((SettingsWindowController) x(SettingsWindowController.class)).I(runnable).J(str, z);
        } else {
            ((HelperWindowController) x(HelperWindowController.class)).B("updateScreenExtraction");
        }
        GameAssistWindowManager.O(this.f8567i).g0("showScreenExtractionSettings");
    }

    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public void E() {
        X(y(this.f8571m) && SystemMgr.H(), "update");
    }

    public void X(boolean z, String str) {
        ScreenExtractionData c2 = DefaultUtils.c(this.f8567i, this.f8571m);
        if (c2 != null || GaLog.i()) {
            GaLog.e("ScreenExtraction", "updateScreenExtraction packageName=" + this.f8571m + " show=" + z + " reason=" + str + " data=" + c2);
        }
        if (z && c2 == null) {
            V(this.f8571m, new a(this), true);
        } else if (!z || c2 == null) {
            Q(null);
        } else {
            Q(c2);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(final boolean z) {
        if (this.f8570l != null) {
            this.f8572n.post(new Runnable() { // from class: l.o
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenExtractionManager.this.J(z);
                }
            });
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        GameAssistWindowManager.R.e(true, this.f8573o);
        A();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f8572n.post(new Runnable() { // from class: l.k
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.K();
            }
        });
        GameAssistWindowManager.R.e(false, this.f8573o);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        this.f8571m = SystemMgr.t();
        if (DefaultUtils.b(this.f8567i)) {
            final boolean y = y(this.f8571m);
            if (y) {
                S(this.f8571m);
            }
            this.f8572n.post(new Runnable() { // from class: l.n
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenExtractionManager.this.L(y);
                }
            });
            if (y(this.f8571m)) {
                ShaderUtils.k(this.f8571m, false);
                GaLog.e("ScreenExtraction", "onGameUpdate: " + this.f8571m);
            }
        }
    }

    public void p(final Callback callback) {
        this.f8572n.post(new Runnable() { // from class: l.i
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.A(callback);
            }
        });
    }

    public void q(String str) {
        this.f8568j.b(str);
        E();
    }

    public void r(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String str) {
        printWriter.println(str + "ScreenExtraction");
        printWriter.println(str + "mGameApp=" + this.f8571m);
        printWriter.println(str + "mPreviewData=" + this.f8569k);
        printWriter.println(str + "data=" + DefaultUtils.c(this.f8567i, SystemMgr.t()));
        this.f8570l.forEach(new BiConsumer() { // from class: l.c
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IWindowController) obj2).a(fileDescriptor, printWriter, str);
            }
        });
    }

    public void s(String str) {
        this.f8568j.c(str);
        if (!DefaultUtils.b(this.f8567i) || DefaultUtils.c(this.f8567i, str) == null) {
            V(str, new a(this), true);
        } else {
            E();
        }
    }

    public void t() {
        Map map = this.f8570l;
        if (map != null) {
            map.forEach(new BiConsumer() { // from class: l.f
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((IWindowController) obj2).b("forceHideWindow");
                }
            });
        }
        this.f8572n.post(new Runnable() { // from class: l.g
            @Override // java.lang.Runnable
            public final void run() {
                ScreenExtractionManager.this.C();
            }
        });
    }

    public Context u() {
        return this.f8567i;
    }

    public Handler v() {
        return this.f8572n;
    }

    public IWindowController x(Class cls) {
        return (IWindowController) this.f8570l.get(cls);
    }

    public boolean y(String str) {
        return this.f8568j.d().contains(str);
    }

    public boolean z() {
        return DefaultUtils.b(this.f8567i);
    }
}
