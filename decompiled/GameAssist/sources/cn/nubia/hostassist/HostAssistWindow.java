package cn.nubia.hostassist;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.performance.GamePerformanceViewController;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.gameassist.utils.WindowManagerUtil;
import cn.nubia.hostassist.controller.HostViewController;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class HostAssistWindow implements View.OnHoverListener {
    public static boolean G = true;
    public static boolean H = false;
    public static boolean I = false;
    private HostViewController A;
    private GamePerformanceViewController B;
    private int E;
    public AnimatorSet F;

    /* renamed from: c, reason: collision with root package name */
    private WindowManager.LayoutParams f7783c;

    /* renamed from: h, reason: collision with root package name */
    private WindowManager.LayoutParams f7784h;

    /* renamed from: i, reason: collision with root package name */
    private View f7785i;

    /* renamed from: j, reason: collision with root package name */
    private HostAssistPanel f7786j;

    /* renamed from: k, reason: collision with root package name */
    private CircleImageView f7787k;

    /* renamed from: l, reason: collision with root package name */
    private View f7788l;

    /* renamed from: m, reason: collision with root package name */
    private FrameLayout f7789m;

    /* renamed from: n, reason: collision with root package name */
    private ImageView f7790n;

    /* renamed from: o, reason: collision with root package name */
    private ImageView f7791o;

    /* renamed from: p, reason: collision with root package name */
    private ImageView f7792p;

    /* renamed from: q, reason: collision with root package name */
    private ImageView f7793q;

    /* renamed from: r, reason: collision with root package name */
    private ImageView f7794r;

    /* renamed from: s, reason: collision with root package name */
    private CircleImageView f7795s;
    private Context t;
    private WindowManagerUtil u;
    private boolean v = false;
    private boolean w = false;
    private boolean x = true;
    private final Handler y = new Handler(Looper.getMainLooper());
    private Rect z = new Rect();
    private int C = 290;
    private int D = 183;

    public HostAssistWindow(Context context, int i2) {
        this.t = context;
        this.E = i2;
        r();
    }

    private void F(boolean z) {
        if (this.f7784h == null || this.z == null) {
            return;
        }
        GaLog.e("HostAssistWindow", "updatePerformanceLayoutParams: isHorizontal= " + z + " mIsHorizontal= " + G + " " + this.z);
        this.f7784h.width = z ? HostAssistUtils.b() : HostAssistUtils.b() * 2;
        int c2 = z ? HostAssistUtils.c() : HostAssistUtils.c() * 2;
        WindowManager.LayoutParams layoutParams = this.f7784h;
        layoutParams.height = c2;
        Rect rect = this.z;
        layoutParams.x = rect.left;
        layoutParams.y = (rect.top - c2) + 14;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(int i2, boolean z) {
        if (this.f7783c != null && this.z != null) {
            GaLog.e("HostAssistWindow", "updateWindowLayoutParams: tipPanelStatus= " + i2 + " isHorizontal= " + z + " mIsHorizontalOld= " + H + " " + this.z);
            this.f7783c.height = this.z.height();
            WindowManager.LayoutParams layoutParams = this.f7783c;
            Rect rect = this.z;
            layoutParams.x = rect.left;
            layoutParams.y = rect.top;
            if (i2 == 0) {
                layoutParams.width = (int) (HostAssistMgr.n().f7770s * 30.0f);
            } else if (i2 == 1) {
                layoutParams.width = (int) (HostAssistUtils.f() * HostAssistMgr.n().f7770s);
            } else if (i2 != 2) {
                layoutParams.width = rect.width();
            } else {
                layoutParams.width = (int) (HostAssistUtils.e() * HostAssistMgr.n().f7770s);
            }
            if (!z) {
                this.f7783c.width *= 2;
            }
        }
        if (z != H) {
            H = z;
            I = true;
            this.v = false;
            this.u.b(this.f7785i);
            this.f7785i = null;
            v(z);
        }
    }

    private void s(boolean z) {
        if (this.f7784h == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2027, 75826952, -3);
            this.f7784h = layoutParams;
            layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            WindowManager.LayoutParams layoutParams2 = this.f7784h;
            layoutParams2.gravity = 51;
            layoutParams2.setTitle("HostAssistPerformance");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f7784h);
        }
        GaLog.e("HostAssistWindow", "initPerformanceLayoutParams: isHorizontal= " + z + " mIsHorizontal= " + G);
        this.f7784h.width = z ? HostAssistUtils.b() : HostAssistUtils.b() * 2;
        int c2 = z ? HostAssistUtils.c() : HostAssistUtils.c() * 2;
        WindowManager.LayoutParams layoutParams3 = this.f7784h;
        layoutParams3.height = c2;
        Rect rect = this.z;
        layoutParams3.x = rect.left;
        layoutParams3.y = (rect.top - c2) + 14;
    }

    private void t(boolean z) {
        int i2;
        if (this.f7788l == null) {
            if (z) {
                i2 = R.layout.host_performance_panel;
                this.C = HostAssistUtils.b();
                this.D = HostAssistUtils.c();
            } else {
                i2 = R.layout.host_performance_panel_port;
                this.C = HostAssistUtils.b() * 2;
                this.D = HostAssistUtils.c() * 2;
            }
            View inflate = LayoutInflater.from(this.t).inflate(i2, (ViewGroup) null);
            this.f7788l = inflate;
            this.f7789m = (FrameLayout) inflate.findViewById(R.id.host_performance_panel);
            this.A.m(this.B);
            this.B.r(this.A);
            this.A.h(this.f7789m);
            GaLog.e("HostAssistWindow", "addPerformanceView: isPerformanceAdd= " + this.w);
            if (this.w) {
                return;
            }
            this.w = true;
            s(z);
            this.u.a(this.f7788l, this.f7784h);
        }
    }

    private void u(boolean z) {
        if (this.f7783c == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2027, 75826952, -3);
            this.f7783c = layoutParams;
            layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            WindowManager.LayoutParams layoutParams2 = this.f7783c;
            layoutParams2.gravity = 51;
            layoutParams2.setTitle("HostAssistPanel");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f7783c);
        }
        GaLog.e("HostAssistWindow", "initTipLayoutParams: isHorizontal= " + z + " mIsHorizontal= " + G + " " + this.z);
        this.f7783c.width = z ? 30 : 60;
        if (this.f7787k.getVisibility() != 0) {
            this.f7783c.width = this.z.width();
        }
        this.f7783c.height = this.z.height();
        WindowManager.LayoutParams layoutParams3 = this.f7783c;
        Rect rect = this.z;
        layoutParams3.x = rect.left;
        layoutParams3.y = rect.top;
    }

    private void v(boolean z) {
        GaLog.e("HostAssistWindow", "initWindowView: " + z + " " + G);
        if (z != G || this.f7785i == null) {
            HostDensityHelper.d(this.t);
            View inflate = LayoutInflater.from(this.t).inflate(z ? R.layout.host_tip : R.layout.host_tip_port, (ViewGroup) null);
            this.f7785i = inflate;
            inflate.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.hostassist.HostAssistWindow.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 4) {
                        float rawX = motionEvent.getRawX();
                        float rawY = motionEvent.getRawY();
                        boolean z2 = rawX >= ((float) HostAssistWindow.this.f7783c.x) && rawX <= ((float) (HostAssistWindow.this.f7783c.x + HostAssistWindow.this.C)) && rawY >= ((float) (HostAssistWindow.this.f7783c.y - HostAssistWindow.this.D)) && rawY <= ((float) HostAssistWindow.this.f7783c.y);
                        boolean z3 = HostAssistMgr.z();
                        StringBuilder sb = new StringBuilder();
                        sb.append("onTouch: !isClickInPerformance= ");
                        sb.append(!z2);
                        sb.append(" !isGameSpace= ");
                        sb.append(!z3);
                        sb.append(" !isTipShow= ");
                        sb.append(true ^ HostAssistWindow.this.x);
                        GaLog.e("HostAssistWindow", sb.toString());
                        if (!z2 && !z3 && !HostAssistWindow.this.x) {
                            HostAssistWindow.this.n();
                        }
                    } else if (motionEvent.getAction() == 0) {
                        GaLog.a("HostAssistWindow", "show panel");
                    }
                    return false;
                }
            });
            CircleImageView circleImageView = (CircleImageView) this.f7785i.findViewById(R.id.host_tip_floatbar);
            this.f7787k = circleImageView;
            circleImageView.setOnHoverListener(this);
            this.f7786j = (HostAssistPanel) this.f7785i.findViewById(R.id.host_tip_panel);
            this.f7790n = (ImageView) this.f7785i.findViewById(R.id.host_tip_home);
            this.f7791o = (ImageView) this.f7785i.findViewById(R.id.host_tip_freeform);
            this.f7792p = (ImageView) this.f7785i.findViewById(R.id.host_assist_panel_divider_2);
            this.f7793q = (ImageView) this.f7785i.findViewById(R.id.host_tip_keybord);
            this.f7794r = (ImageView) this.f7785i.findViewById(R.id.host_tip_performmonitor);
            this.f7795s = (CircleImageView) this.f7785i.findViewById(R.id.host_tip_performance);
            GaLog.e("HostAssistWindow", "addWindowView: isWindowAdd= " + this.v);
            if (this.v) {
                return;
            }
            this.v = true;
            u(z);
            this.u.a(this.f7785i, this.f7783c);
        }
    }

    private void z() {
        NubiaTrackManager.p().z("com.android.settings", "host_mode_console_used", "dashboard_status", w() ? "expand" : "fold");
    }

    public void A(Rect rect, boolean z) {
        this.z.set(rect);
        G = z;
        if (this.f7785i == null) {
            GaLog.e("HostAssistWindow", "show window");
            v(z);
        } else {
            GaLog.e("HostAssistWindow", "update window: mIsHorizontal= " + G);
            G(!this.x ? 1 : 0, G);
            this.u.c(this.f7785i, this.f7783c);
        }
        if (this.f7788l == null) {
            GaLog.e("HostAssistWindow", "show mHostPerformance");
            t(z);
        } else {
            GaLog.e("HostAssistWindow", "update mHostPerformance");
            F(z);
            this.u.c(this.f7788l, this.f7784h);
        }
    }

    public void B() {
        StringBuilder sb = new StringBuilder();
        sb.append("showGameSpaceTipPanel: ");
        sb.append(this.f7787k != null);
        sb.append(" ");
        sb.append(this.f7786j != null);
        sb.append(" mIsHorizontal= ");
        sb.append(G);
        GaLog.e("HostAssistWindow", sb.toString());
        if (this.f7787k == null || this.f7786j == null) {
            return;
        }
        this.v = false;
        this.u.b(this.f7785i);
        this.f7785i = null;
        v(true);
        o(false);
        G(1, true);
        GaLog.e("HostAssistWindow", "showGameSpaceTipPanel: mHostTip= " + this.f7785i + " mTipLayoutParams= " + this.f7783c);
        this.u.c(this.f7785i, this.f7783c);
        this.f7787k.setVisibility(8);
        this.x = false;
        this.f7786j.setVisibility(0);
        this.f7792p.setVisibility(8);
        this.f7793q.setVisibility(8);
        this.f7794r.setVisibility(8);
        this.f7795s.setVisibility(8);
        if (HostAssistMgr.y()) {
            this.f7790n.setVisibility(8);
        }
        if (!ZteFeature.isSupportHostFreeform() || HostAssistMgr.y()) {
            this.f7791o.setVisibility(8);
        } else {
            this.f7791o.setVisibility(0);
        }
    }

    public void C() {
        StringBuilder sb = new StringBuilder();
        sb.append("showPanelMax: ");
        sb.append(this.f7787k != null);
        sb.append(" ");
        sb.append(this.f7786j != null);
        sb.append(" mIsHorizontal= ");
        sb.append(G);
        GaLog.e("HostAssistWindow", sb.toString());
        if (this.f7787k == null || this.f7786j == null) {
            return;
        }
        G(2, G);
        this.u.c(this.f7785i, this.f7783c);
        this.f7787k.setVisibility(8);
        this.x = false;
        this.f7786j.setVisibility(0);
        this.f7790n.setVisibility(0);
        if (ZteFeature.isSupportHostFreeform()) {
            this.f7791o.setVisibility(0);
        } else {
            this.f7791o.setVisibility(8);
        }
        this.f7792p.setVisibility(0);
        this.f7793q.setVisibility(0);
        if (Utils.R()) {
            this.f7794r.setVisibility(8);
        } else {
            this.f7794r.setVisibility(0);
        }
        this.f7795s.setVisibility(0);
        if (SharedPreferencesUtil.k(this.t).j()) {
            E(false);
        }
        y(true);
    }

    public void D() {
        StringBuilder sb = new StringBuilder();
        sb.append("showTipPanelMin: ");
        sb.append(this.f7787k != null);
        sb.append(" ");
        sb.append(this.f7786j != null);
        sb.append(" mIsHorizontal= ");
        sb.append(G);
        GaLog.e("HostAssistWindow", sb.toString());
        if (this.f7787k == null || this.f7786j == null) {
            return;
        }
        o(false);
        G(1, G);
        this.u.c(this.f7785i, this.f7783c);
        this.f7787k.setVisibility(8);
        this.x = false;
        this.f7786j.setVisibility(0);
        this.f7792p.setVisibility(8);
        this.f7793q.setVisibility(8);
        this.f7794r.setVisibility(8);
        this.f7795s.setVisibility(8);
        if (HostAssistMgr.y()) {
            this.f7790n.setVisibility(8);
        }
        if (!ZteFeature.isSupportHostFreeform() || HostAssistMgr.y()) {
            this.f7791o.setVisibility(8);
        } else {
            this.f7791o.setVisibility(0);
        }
        if (HostAssistMgr.z()) {
            return;
        }
        y(true);
    }

    public void E(boolean z) {
        boolean w = w();
        GaLog.e("HostAssistWindow", "showPerformancePanel: isPerformanceAdd= " + this.w + " " + w);
        this.w = w;
        if (w) {
            return;
        }
        t(G);
        if (z) {
            x(true);
        }
        this.A.f();
        this.A.s(G);
        WindowManager.LayoutParams layoutParams = this.f7784h;
        layoutParams.flags ^= 16;
        this.u.c(this.f7788l, layoutParams);
        this.w = true;
    }

    public void m() {
        if (this.v) {
            this.v = false;
            this.u.b(this.f7785i);
            this.f7785i = null;
        }
        GaLog.e("HostAssistWindow", "close: isPerformanceAdd = " + this.w);
        if (this.w) {
            this.w = false;
            this.A.j();
            this.u.b(this.f7788l);
            this.f7788l = null;
        }
    }

    public void n() {
        StringBuilder sb = new StringBuilder();
        sb.append("closePanel: ");
        sb.append(this.f7787k != null);
        sb.append(" ");
        sb.append(this.f7786j != null);
        sb.append(" mIsHorizontal= ");
        sb.append(G);
        GaLog.e("HostAssistWindow", sb.toString());
        if (this.f7787k == null || this.f7786j == null || this.f7785i == null) {
            return;
        }
        y(false);
        o(false);
        HostAssistMgr.n().j("closepanel");
        this.y.postDelayed(new Runnable() { // from class: cn.nubia.hostassist.HostAssistWindow.2
            @Override // java.lang.Runnable
            public void run() {
                HostAssistWindow.this.G(0, HostAssistWindow.G);
                if (HostAssistWindow.this.f7785i != null) {
                    HostAssistWindow.this.u.c(HostAssistWindow.this.f7785i, HostAssistWindow.this.f7783c);
                }
                HostAssistWindow.this.f7795s.setVisibility(8);
                HostAssistWindow.this.f7787k.setVisibility(0);
                HostAssistWindow.this.x = true;
            }
        }, 300L);
        z();
    }

    public void o(boolean z) {
        GaLog.e("HostAssistWindow", "closePerformancePanel: isPerformanceAdd= " + this.w);
        if (this.w) {
            if (z) {
                x(false);
            }
            this.y.postDelayed(new Runnable() { // from class: cn.nubia.hostassist.HostAssistWindow.11
                @Override // java.lang.Runnable
                public void run() {
                    if (HostAssistWindow.this.f7788l != null) {
                        HostAssistWindow.this.p();
                    }
                }
            }, 300L);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 9) {
            if (actionMasked != 10) {
                return false;
            }
            GaLog.e("HostAssistWindow", "HOVER_EXIT");
            return false;
        }
        boolean y = HostAssistMgr.y();
        boolean z = HostAssistMgr.z();
        GaLog.e("HostAssistWindow", "onClick: mDisplayId= " + this.E + " isExpandMode= " + y + " isGameSpace= " + z);
        if (this.E <= 0 || !this.x) {
            return false;
        }
        if (y || z) {
            D();
            return false;
        }
        C();
        return false;
    }

    public void p() {
        View view;
        GaLog.e("HostAssistWindow", "closePerformancePanelImm: isPerformanceAdd= " + this.w);
        if (this.w && (view = this.f7788l) != null) {
            WindowManager.LayoutParams layoutParams = this.f7784h;
            layoutParams.flags |= 16;
            this.u.c(view, layoutParams);
            this.A.j();
            this.u.b(this.f7788l);
            this.w = false;
            this.f7788l = null;
        }
        this.f7795s.setRotation(270.0f);
    }

    public void q(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("  HostAssistWindow:");
        printWriter.println("    mIsHorizontal=" + G);
        printWriter.println("    mIsHorizontalOld=" + H);
        printWriter.println("    mHoverArea=" + this.z);
        printWriter.println("    isWindowAdd=" + this.v);
        printWriter.println("    isPerformanceAdd=" + this.w);
        printWriter.println("    isTipShow=" + this.x);
        printWriter.println("    mWindowLayoutParams=" + this.f7783c.x + " " + this.f7783c.y + " " + this.f7783c.width + " " + this.f7783c.height);
        printWriter.println("    mPerformanceLayoutParams=" + this.f7784h.x + " " + this.f7784h.y + " " + this.f7784h.width + " " + this.f7784h.height);
    }

    public void r() {
        this.u = new WindowManagerUtil((WindowManager) this.t.getSystemService(WindowManager.class));
        this.A = HostViewController.e(this.t);
        this.B = GamePerformanceViewController.k(this.t);
    }

    public boolean w() {
        HostViewController hostViewController = this.A;
        return hostViewController != null && hostViewController.i() && this.w;
    }

    public void x(boolean z) {
        GaLog.e("HostAssistWindow", "playPerformanceAnimationTranslationY expand= " + z + " mPerformancePanelHeight=" + this.D);
        if (z) {
            ValueAnimator duration = ValueAnimator.ofFloat(this.D, 0.0f).setDuration(300L);
            duration.setInterpolator(new LinearInterpolator());
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.9
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (HostAssistWindow.this.f7788l == null || HostAssistWindow.this.f7788l.getVisibility() != 0) {
                        return;
                    }
                    HostAssistWindow.this.f7788l.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            duration.start();
            return;
        }
        ValueAnimator duration2 = ValueAnimator.ofFloat(0.0f, this.D).setDuration(300L);
        duration2.setInterpolator(new LinearInterpolator());
        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.10
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HostAssistWindow.this.f7788l == null || HostAssistWindow.this.f7788l.getVisibility() != 0) {
                    return;
                }
                HostAssistWindow.this.f7788l.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        duration2.start();
    }

    public void y(boolean z) {
        float f2 = G ? 25.0f : 50.0f;
        boolean w = w();
        GaLog.e("HostAssistWindow", "playTipPanelAnimation expand= " + z + " : " + this.C + " " + f2 + " " + w);
        if (z) {
            ValueAnimator duration = ValueAnimator.ofFloat(-(this.C - f2), 0.0f).setDuration(300L);
            duration.setInterpolator(new LinearInterpolator());
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (HostAssistWindow.this.f7786j == null || HostAssistWindow.this.f7786j.getVisibility() != 0) {
                        return;
                    }
                    HostAssistWindow.this.f7786j.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            ValueAnimator duration2 = ValueAnimator.ofFloat(-this.C, 0.0f).setDuration(300L);
            duration2.setInterpolator(new LinearInterpolator());
            duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (HostAssistWindow.this.f7788l == null || HostAssistWindow.this.f7788l.getVisibility() != 0) {
                        return;
                    }
                    HostAssistWindow.this.f7788l.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            ValueAnimator duration3 = ValueAnimator.ofFloat(0.0f, w ? 90.0f : -90.0f).setDuration(300L);
            duration3.setInterpolator(new LinearInterpolator());
            duration3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.5
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (HostAssistWindow.this.f7795s == null || HostAssistWindow.this.f7795s.getVisibility() != 0) {
                        return;
                    }
                    HostAssistWindow.this.f7795s.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            this.F = animatorSet;
            animatorSet.playTogether(duration, duration2, duration3);
            this.F.start();
            return;
        }
        ValueAnimator duration4 = ValueAnimator.ofFloat(0.0f, -(this.C - f2)).setDuration(300L);
        duration4.setInterpolator(new LinearInterpolator());
        duration4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HostAssistWindow.this.f7786j == null || HostAssistWindow.this.f7786j.getVisibility() != 0) {
                    return;
                }
                HostAssistWindow.this.f7786j.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        ValueAnimator duration5 = ValueAnimator.ofFloat(0.0f, -this.C).setDuration(300L);
        duration5.setInterpolator(new LinearInterpolator());
        duration5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HostAssistWindow.this.f7788l == null || HostAssistWindow.this.f7788l.getVisibility() != 0) {
                    return;
                }
                HostAssistWindow.this.f7788l.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        ValueAnimator duration6 = ValueAnimator.ofFloat(w ? 90.0f : -90.0f, 0.0f).setDuration(300L);
        duration6.setInterpolator(new LinearInterpolator());
        duration6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistWindow.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HostAssistWindow.this.f7795s == null || HostAssistWindow.this.f7795s.getVisibility() != 0) {
                    return;
                }
                HostAssistWindow.this.f7795s.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.F = animatorSet2;
        animatorSet2.playTogether(duration4, duration5, duration6);
        this.F.start();
    }
}
