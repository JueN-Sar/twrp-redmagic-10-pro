package cn.nubia.plugin.screenextraction.controller;

import android.content.Context;
import android.graphics.Point;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public abstract class BaseWindowController<T extends View> implements IWindowController {

    /* renamed from: c, reason: collision with root package name */
    protected final ScreenExtractionManager f8589c;

    /* renamed from: h, reason: collision with root package name */
    private Runnable f8590h = new Runnable() { // from class: cn.nubia.plugin.screenextraction.controller.a
        @Override // java.lang.Runnable
        public final void run() {
            BaseWindowController.this.A();
        }
    };

    /* renamed from: i, reason: collision with root package name */
    private Runnable f8591i = new Runnable() { // from class: cn.nubia.plugin.screenextraction.controller.b
        @Override // java.lang.Runnable
        public final void run() {
            BaseWindowController.this.u();
        }
    };

    /* renamed from: j, reason: collision with root package name */
    protected final Handler f8592j;

    /* renamed from: k, reason: collision with root package name */
    protected final Context f8593k;

    /* renamed from: l, reason: collision with root package name */
    protected View f8594l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f8595m;

    /* renamed from: n, reason: collision with root package name */
    private WindowManager f8596n;

    /* renamed from: o, reason: collision with root package name */
    private WindowManager.LayoutParams f8597o;

    public BaseWindowController(ScreenExtractionManager screenExtractionManager) {
        this.f8589c = screenExtractionManager;
        this.f8592j = screenExtractionManager.v();
        Context u = screenExtractionManager.u();
        this.f8593k = u;
        this.f8596n = (WindowManager) u.getSystemService(WindowManager.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        synchronized (this) {
            try {
                if (!this.f8595m) {
                    this.f8595m = true;
                    View j2 = j();
                    this.f8594l = j2;
                    x(j2);
                    this.f8594l.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cn.nubia.plugin.screenextraction.controller.BaseWindowController.1
                        @Override // android.view.ViewTreeObserver.OnPreDrawListener
                        public boolean onPreDraw() {
                            BaseWindowController baseWindowController = BaseWindowController.this;
                            baseWindowController.w(baseWindowController.f8594l);
                            BaseWindowController.this.f8594l.getViewTreeObserver().removeOnPreDrawListener(this);
                            return true;
                        }
                    });
                    this.f8596n.addView(this.f8594l, o());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        synchronized (this) {
            try {
                if (this.f8595m) {
                    this.f8595m = false;
                    y(this.f8594l);
                    this.f8596n.removeView(this.f8594l);
                    this.f8594l = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void B(String str) {
        GaLog.a("ScreenExtraction.Window", "showWindow reason=" + str);
        z();
        this.f8592j.post(this.f8590h);
    }

    protected void C() {
        if (v()) {
            this.f8596n.updateViewLayout(this.f8594l, o());
        }
    }

    @Override // cn.nubia.plugin.screenextraction.controller.IWindowController
    public void b(String str) {
        GaLog.a("ScreenExtraction.Window", "hideWindow reason=" + str);
        z();
        this.f8592j.post(this.f8591i);
    }

    @Override // cn.nubia.plugin.screenextraction.controller.IWindowController
    public void e(boolean z) {
        if (v()) {
            b("GameScene=" + z);
        }
    }

    abstract View j();

    protected int k() {
        return 0;
    }

    protected int l() {
        return 92604168;
    }

    protected int m() {
        return -3;
    }

    protected int n() {
        return 51;
    }

    protected WindowManager.LayoutParams o() {
        if (this.f8597o == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = t();
            layoutParams.windowAnimations = k();
            layoutParams.token = s();
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            layoutParams.setTitle(r());
            layoutParams.softInputMode = 3;
            layoutParams.layoutInDisplayCutoutMode = 3;
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
            this.f8597o = layoutParams;
        }
        Point q2 = q();
        WindowManager.LayoutParams layoutParams2 = this.f8597o;
        layoutParams2.width = q2.x;
        layoutParams2.height = q2.y;
        Point p2 = p();
        WindowManager.LayoutParams layoutParams3 = this.f8597o;
        layoutParams3.x = p2.x;
        layoutParams3.y = p2.y;
        layoutParams3.flags = l();
        this.f8597o.gravity = n();
        this.f8597o.format = m();
        return this.f8597o;
    }

    protected Point p() {
        return new Point(0, 0);
    }

    protected Point q() {
        return new Point(-1, -1);
    }

    protected String r() {
        return "ScreenExtraction.Window";
    }

    protected IBinder s() {
        return new Binder();
    }

    protected int t() {
        return 2008;
    }

    public boolean v() {
        return this.f8595m;
    }

    protected void w(View view) {
    }

    protected void x(View view) {
    }

    protected void y(View view) {
    }

    public void z() {
        this.f8592j.removeCallbacks(this.f8591i);
        this.f8592j.removeCallbacks(this.f8590h);
    }
}
