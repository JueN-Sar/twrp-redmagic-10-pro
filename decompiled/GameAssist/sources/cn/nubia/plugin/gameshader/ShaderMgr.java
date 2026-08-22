package cn.nubia.plugin.gameshader;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class ShaderMgr implements GameMonitor.Callback {

    /* renamed from: p, reason: collision with root package name */
    private static volatile ShaderMgr f8467p;

    /* renamed from: c, reason: collision with root package name */
    private Context f8468c;

    /* renamed from: l, reason: collision with root package name */
    private ShaderWindow f8473l;

    /* renamed from: m, reason: collision with root package name */
    private onDataChangeCallback f8474m;

    /* renamed from: h, reason: collision with root package name */
    private String f8469h = "";

    /* renamed from: i, reason: collision with root package name */
    private String f8470i = "";

    /* renamed from: j, reason: collision with root package name */
    private final Handler f8471j = new Handler(Looper.getMainLooper());

    /* renamed from: k, reason: collision with root package name */
    private ShaderDataMgr f8472k = new ShaderDataMgr();

    /* renamed from: n, reason: collision with root package name */
    private Runnable f8475n = new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.7
        @Override // java.lang.Runnable
        public void run() {
            if (ShaderMgr.this.f8469h.equals(Utils.j()) && ShaderMgr.this.f8472k.l() && ShaderMgr.this.f8473l != null && ShaderMgr.this.f8472k.e()) {
                ShaderMgr.this.f8473l.f();
                ShaderMgr.this.v();
            }
        }
    };

    /* renamed from: o, reason: collision with root package name */
    private Runnable f8476o = new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.8
        @Override // java.lang.Runnable
        public void run() {
            String j2 = Utils.j();
            if (!ShaderMgr.this.f8469h.equals(j2) && ShaderMgr.this.f8472k.l() && ShaderMgr.this.f8473l != null) {
                GaLog.e("GameShaderMgr", "resetLast last=" + ShaderMgr.this.f8469h);
                ShaderMgr.this.f8473l.r();
            }
            if (SystemMgr.H()) {
                ShaderMgr.this.f8470i = j2;
            }
            ShaderMgr.this.f8469h = j2;
            ShaderMgr.this.f8472k.j(ShaderMgr.this.f8469h);
            if (ShaderMgr.this.f8472k.l()) {
                GaLog.e("GameShaderMgr", "onGameStart :  " + ShaderMgr.this.f8469h + " " + ShaderMgr.this.f8472k.f8456c);
                ShaderMgr.this.v();
                ShaderMgr.this.F();
                ShaderMgr.this.f8471j.removeCallbacks(ShaderMgr.this.f8475n);
                ShaderMgr.this.f8471j.postDelayed(ShaderMgr.this.f8475n, 800L);
            }
        }
    };

    public interface onDataChangeCallback {
        void d();
    }

    private ShaderMgr() {
        u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(int i2) {
        if (SystemMgr.H()) {
            ShaderUtils.n(this.f8469h, i2);
        } else {
            if (TextUtils.isEmpty(this.f8470i)) {
                return;
            }
            ShaderUtils.n(this.f8470i, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (this.f8472k.m() && this.f8472k.l()) {
            C(this.f8472k.f());
        } else {
            C(0);
        }
    }

    public static ShaderMgr t() {
        if (f8467p == null) {
            synchronized (ShaderMgr.class) {
                try {
                    if (f8467p == null) {
                        f8467p = new ShaderMgr();
                    }
                } finally {
                }
            }
        }
        return f8467p;
    }

    private void u() {
        this.f8468c = GameAssistApplication.j();
        String j2 = Utils.j();
        this.f8469h = j2;
        this.f8472k.j(j2);
        if (this.f8472k.i()) {
            C(0);
        }
        SystemMgr.y(this.f8468c).h(this);
        FoldMgr.c().a(new FoldMgr.Callback() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.1
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public void onDisplayInUseStateChanged(int i2) {
                ShaderMgr.this.r();
                if (ShaderMgr.this.f8473l != null) {
                    ShaderMgr.this.f8473l.o();
                }
            }
        });
        RotationMgr.e(this.f8468c).c(new RotationMgr.Callback() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.2
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            /* renamed from: onRotationChanged */
            public void y(int i2) {
                if (SystemMgr.G && ShaderMgr.this.x()) {
                    ShaderMgr.this.r();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (!w()) {
            ShaderWindow shaderWindow = this.f8473l;
            if (shaderWindow != null) {
                shaderWindow.f();
                return;
            }
            return;
        }
        if (this.f8473l == null) {
            this.f8473l = new ShaderWindow(this.f8468c);
        }
        boolean z = this.f8472k.f8457d;
        this.f8473l.u(s(), z);
        if (z) {
            this.f8472k.q();
        }
    }

    public void A(boolean z) {
        if (!w()) {
            z(z);
        }
        this.f8471j.postDelayed(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.5
            @Override // java.lang.Runnable
            public void run() {
                if (ShaderMgr.this.y()) {
                    return;
                }
                ShaderMgr.this.p();
                ShaderMgr.this.f8473l.t();
            }
        }, 200L);
    }

    public void B(boolean z, onDataChangeCallback ondatachangecallback) {
        this.f8474m = ondatachangecallback;
        z(z);
    }

    public void D(final boolean z) {
        if (z && !w()) {
            z(true);
        }
        if (!z && w()) {
            z(false);
        }
        this.f8471j.postDelayed(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.6
            @Override // java.lang.Runnable
            public void run() {
                if (z && !ShaderMgr.this.y()) {
                    ShaderMgr.this.p();
                    ShaderMgr.this.f8473l.t();
                } else {
                    if (z || !ShaderMgr.this.y()) {
                        return;
                    }
                    ShaderMgr.this.p();
                    ShaderMgr.this.f8473l.t();
                }
            }
        }, 200L);
    }

    public void E() {
        this.f8471j.post(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.3
            @Override // java.lang.Runnable
            public void run() {
                if (ShaderMgr.this.f8473l != null) {
                    ShaderMgr.this.f8473l.v();
                }
            }
        });
    }

    public void n(boolean z) {
        this.f8472k.a(this.f8469h, z);
    }

    public void o(int i2, int i3) {
        this.f8472k.b(this.f8469h, i2, i3);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f8471j.removeCallbacks(this.f8476o);
        this.f8471j.postDelayed(this.f8476o, 5L);
        if ("com.tencent.mm".equals(SystemMgr.z())) {
            this.f8471j.postDelayed(this.f8476o, 500L);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f8471j.removeCallbacks(this.f8476o);
        if (this.f8472k.l()) {
            this.f8471j.post(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.9
                @Override // java.lang.Runnable
                public void run() {
                    if (ShaderMgr.this.f8473l != null) {
                        ShaderMgr.this.f8473l.f();
                        ShaderMgr.this.f8473l.q();
                        ShaderMgr.this.f8473l = null;
                    }
                    if (ShaderMgr.this.f8472k.f() <= 0 && ShaderMgr.this.f8472k.l() && ShaderMgr.this.f8472k.m()) {
                        return;
                    }
                    ShaderMgr.this.C(0);
                }
            });
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        if (this.f8472k.l() && this.f8473l != null) {
            GaLog.e("GameShaderMgr", "onGameUpdate: " + this.f8469h);
            C(0);
            this.f8473l.f();
        }
        this.f8471j.removeCallbacks(this.f8476o);
        this.f8471j.postDelayed(this.f8476o, 500L);
    }

    public void p() {
        this.f8472k.c(this.f8469h);
        F();
    }

    public void q(int i2) {
        this.f8472k.d(this.f8469h, i2);
        F();
        this.f8473l.u(s(), false);
    }

    public void r() {
        ShaderWindow shaderWindow = this.f8473l;
        if (shaderWindow != null) {
            shaderWindow.h();
        }
    }

    public ShaderItemData s() {
        return this.f8472k.f8456c;
    }

    public boolean w() {
        return this.f8472k.l();
    }

    public boolean x() {
        ShaderWindow shaderWindow = this.f8473l;
        return shaderWindow != null && shaderWindow.n();
    }

    public boolean y() {
        return this.f8472k.m();
    }

    public void z(final boolean z) {
        this.f8471j.post(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderMgr.4
            @Override // java.lang.Runnable
            public void run() {
                ShaderMgr.this.f8469h = Utils.j();
                ShaderMgr.this.n(z);
                ShaderMgr.this.v();
                ShaderMgr.this.F();
                if (ShaderMgr.this.f8473l != null) {
                    ShaderMgr.this.f8473l.r();
                }
                if (ShaderMgr.this.f8474m != null) {
                    ShaderMgr.this.f8474m.d();
                    ShaderMgr.this.f8474m = null;
                }
            }
        });
    }
}
