package com.zte.gameassist.lowsugar;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.nubia.componentcenter.service.LowSugarComService;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.lowsugar.ai.LowSugarAiMgr;
import com.zte.gameassist.lowsugar.common.DetectParam;
import com.zte.gameassist.lowsugar.common.LowSugarSettingsObserver;
import com.zte.gameassist.lowsugar.detect.ILowSugarDetect;
import com.zte.gameassist.lowsugar.detect.LowSugarDetect;
import com.zte.gameassist.lowsugar.detect.SceneConfig;
import com.zte.gameassist.lowsugar.ui.LowSugarWindowManager;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class LowSugarGameplayController implements GameMonitor.Callback, ILowSugarDetect.DetectCallback, IGameAssistCommander, DumpController.Dump {

    /* renamed from: s, reason: collision with root package name */
    private static volatile LowSugarGameplayController f16702s;

    /* renamed from: i, reason: collision with root package name */
    private Context f16705i;

    /* renamed from: j, reason: collision with root package name */
    private LowSugarSettingsObserver f16706j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f16707k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f16708l;

    /* renamed from: m, reason: collision with root package name */
    private String f16709m;

    /* renamed from: n, reason: collision with root package name */
    private ILowSugarDetect f16710n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f16711o;

    /* renamed from: p, reason: collision with root package name */
    private long f16712p;

    /* renamed from: q, reason: collision with root package name */
    private LowSugarComService.ICallback f16713q;

    /* renamed from: c, reason: collision with root package name */
    private final Handler f16703c = new Handler(Looper.getMainLooper());

    /* renamed from: r, reason: collision with root package name */
    private String f16714r = "";

    /* renamed from: h, reason: collision with root package name */
    private final Handler f16704h = new Handler(ThreadManager.c().d());

    private LowSugarGameplayController() {
    }

    private ILowSugarDetect j(String str) {
        if (LowSugarUtils.o(str)) {
            return new LowSugarDetect(this.f16705i, str, this.f16704h, new Supplier() { // from class: com.zte.gameassist.lowsugar.f
                @Override // java.util.function.Supplier
                public final Object get() {
                    Long r2;
                    r2 = LowSugarGameplayController.this.r();
                    return r2;
                }
            });
        }
        return null;
    }

    private void k() {
        ILowSugarDetect iLowSugarDetect = this.f16710n;
        if (iLowSugarDetect == null || !iLowSugarDetect.d().contains(this.f16709m)) {
            ILowSugarDetect iLowSugarDetect2 = this.f16710n;
            if (iLowSugarDetect2 != null) {
                iLowSugarDetect2.a();
                this.f16710n = null;
            }
            if (this.f16711o) {
                if (this.f16707k && this.f16708l) {
                    this.f16710n = j(this.f16709m);
                }
                ILowSugarDetect iLowSugarDetect3 = this.f16710n;
                if (iLowSugarDetect3 != null) {
                    iLowSugarDetect3.g(this);
                }
            }
        }
    }

    public static LowSugarGameplayController l() {
        if (f16702s == null) {
            synchronized (LowSugarGameplayController.class) {
                try {
                    if (f16702s == null) {
                        f16702s = new LowSugarGameplayController();
                    }
                } finally {
                }
            }
        }
        return f16702s;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long r() {
        return Long.valueOf(this.f16712p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s(String str) {
        boolean g2 = LowSugarWindowManager.d().g();
        GaLog.a("LowSugarGameplay", "executive isViewShow = " + g2);
        if ("showLowSugarSettingsPanel".equals(str) && !g2) {
            LowSugarWindowManager.d().k();
        } else if ("hideLowSugarSettingsPanel".equals(str) && g2) {
            LowSugarWindowManager.d().i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t() {
        w("gamestart");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u() {
        w("gamestop");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v() {
        w("gameupdate");
    }

    public boolean A() {
        return this.f16707k;
    }

    /* renamed from: B, reason: merged with bridge method [inline-methods] */
    public void w(final String str) {
        ILowSugarDetect iLowSugarDetect;
        ILowSugarDetect iLowSugarDetect2;
        if (!this.f16704h.getLooper().isCurrentThread()) {
            this.f16704h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.d
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarGameplayController.this.w(str);
                }
            });
            return;
        }
        String t = SystemMgr.t();
        if (this.f16706j == null) {
            this.f16706j = new LowSugarSettingsObserver(this.f16704h, this.f16705i);
        }
        boolean d2 = this.f16706j.d();
        boolean c2 = this.f16706j.c();
        if (TextUtils.equals(t, this.f16709m) && d2 == this.f16707k && c2 == this.f16708l) {
            return;
        }
        this.f16707k = d2;
        this.f16708l = c2 & d2;
        this.f16709m = t;
        k();
        boolean z = this.f16708l;
        if (z && (iLowSugarDetect2 = this.f16710n) != null) {
            iLowSugarDetect2.c();
        } else if (!z && (iLowSugarDetect = this.f16710n) != null) {
            iLowSugarDetect.a();
        }
        LowSugarComService.ICallback iCallback = this.f16713q;
        if (iCallback != null) {
            iCallback.u();
        }
        GaLog.a("LowSugarGameplay", "updateLowSugarGameplay pkg=" + t + " enable=" + d2 + " automatic=" + c2 + " reason=" + str + " mSceneDetect=" + this.f16710n);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect.DetectCallback
    public boolean a(int i2, int i3, Bitmap bitmap, DetectParam detectParam) {
        GaLog.e("LowSugarGameplay", "onDetectTarget mode =" + i2 + ",sceneId=" + i3 + ",target=" + bitmap + " gift=" + detectParam.o("gift_icon", 0) + " done=" + detectParam.o("task_done_icon", 0));
        return LowSugarAiMgr.F().T(i2, i3, bitmap, this.f16709m, detectParam);
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("LowSugarGameplay:");
        printWriter.println("   isEnableLowSugar=" + this.f16707k);
        printWriter.println("   isAutomaticRecognition=" + this.f16708l);
        printWriter.println("   mSceneDetect=" + this.f16710n);
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(final String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        GaLog.a("LowSugarGameplay", "executive name=" + str + " ,data=" + bundle);
        this.f16704h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.b
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarGameplayController.s(str);
            }
        });
    }

    public int m() {
        ILowSugarDetect iLowSugarDetect = this.f16710n;
        if (iLowSugarDetect != null) {
            return iLowSugarDetect.getSceneIndex();
        }
        return 0;
    }

    public boolean n() {
        LowSugarWindowManager.d().k();
        return true;
    }

    public void o(Context context) {
        this.f16705i = context;
        this.f16706j = new LowSugarSettingsObserver(this.f16704h, context);
        SystemMgr.y(context).o(this);
        SystemMgr.y(this.f16705i).h(this);
        LowSugarAiMgr.F().J();
        SceneConfig.c().d(this.f16705i);
        DumpController.c().a(this);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f16711o = true;
        this.f16712p = System.currentTimeMillis();
        this.f16704h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.c
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarGameplayController.this.t();
            }
        });
        LowSugarAiMgr.F().M();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f16711o = false;
        this.f16704h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.e
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarGameplayController.this.u();
            }
        });
        LowSugarWindowManager.d().i();
        LowSugarAiMgr.F().N();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        this.f16711o = true;
        this.f16712p = System.currentTimeMillis();
        this.f16704h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.a
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarGameplayController.this.v();
            }
        });
    }

    public boolean p(String str) {
        GaLog.e("LowSugarGameplay", "LowSugarGameplayController isAppEnable pkgName = " + str);
        return LowSugarUtils.o(str);
    }

    public boolean q() {
        ILowSugarDetect iLowSugarDetect = this.f16710n;
        if (iLowSugarDetect != null) {
            return iLowSugarDetect.isDetecting();
        }
        return false;
    }

    public void x(Configuration configuration) {
        Locale locale = configuration.locale;
        String language = Locale.getDefault().getLanguage();
        if (this.f16714r.equals(language)) {
            return;
        }
        LowSugarAiMgr.F().O(language);
        this.f16714r = language;
    }

    public void y(boolean z, LowSugarComService.ICallback iCallback) {
        if (!z) {
            iCallback = null;
        }
        this.f16713q = iCallback;
    }

    public void z() {
        GaLog.e("LowSugarGameplay", "startManualPurpose mSceneDetect=" + this.f16710n);
        String t = SystemMgr.t();
        if (this.f16710n == null || !TextUtils.equals(t, this.f16709m)) {
            ILowSugarDetect iLowSugarDetect = this.f16710n;
            if (iLowSugarDetect != null) {
                iLowSugarDetect.a();
                this.f16710n = null;
            }
            this.f16709m = t;
            ILowSugarDetect j2 = j(t);
            this.f16710n = j2;
            if (j2 != null) {
                j2.g(this);
            }
        }
        GaLog.e("LowSugarGameplay", "startManualPurpose mSceneDetect2=" + this.f16710n);
        if (this.f16710n != null) {
            this.f16704h.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.LowSugarGameplayController.1
                @Override // java.lang.Runnable
                public void run() {
                    LowSugarGameplayController.this.f16710n.b();
                }
            });
        }
    }
}
