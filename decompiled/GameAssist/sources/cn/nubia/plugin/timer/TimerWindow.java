package cn.nubia.plugin.timer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TimerWindow {
    private static int N;
    private static int O;
    private static int P;
    public static int Q;
    private TimerItemView A;
    private TimerItemView B;
    private TimerSettingWindow D;
    protected View G;
    protected ImageView H;
    protected WindowManager.LayoutParams I;
    private int J;
    private int K;

    /* renamed from: a, reason: collision with root package name */
    private WindowManager.LayoutParams f8779a;

    /* renamed from: b, reason: collision with root package name */
    protected View f8780b;

    /* renamed from: c, reason: collision with root package name */
    private float f8781c;

    /* renamed from: d, reason: collision with root package name */
    private float f8782d;

    /* renamed from: e, reason: collision with root package name */
    private float f8783e;

    /* renamed from: f, reason: collision with root package name */
    private float f8784f;

    /* renamed from: g, reason: collision with root package name */
    private float f8785g;

    /* renamed from: h, reason: collision with root package name */
    private float f8786h;

    /* renamed from: i, reason: collision with root package name */
    private float f8787i;

    /* renamed from: j, reason: collision with root package name */
    private int f8788j;

    /* renamed from: k, reason: collision with root package name */
    private int f8789k;

    /* renamed from: l, reason: collision with root package name */
    private long f8790l;

    /* renamed from: m, reason: collision with root package name */
    private long f8791m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f8792n;

    /* renamed from: s, reason: collision with root package name */
    private Context f8797s;
    private WindowManager t;
    private TimerItemView y;
    private TimerItemView z;

    /* renamed from: o, reason: collision with root package name */
    private boolean f8793o = false;

    /* renamed from: p, reason: collision with root package name */
    private int f8794p = 300;

    /* renamed from: q, reason: collision with root package name */
    private int f8795q = 300;

    /* renamed from: r, reason: collision with root package name */
    private int f8796r = 0;
    private String u = "";
    private boolean v = false;
    private final Handler w = new Handler(Looper.getMainLooper());
    private boolean x = false;
    private ArrayList C = new ArrayList();
    private boolean E = false;
    private boolean F = false;
    private boolean L = false;
    private final Runnable M = new Runnable() { // from class: cn.nubia.plugin.timer.TimerWindow.3
        @Override // java.lang.Runnable
        public void run() {
            TimerWindow.this.w.removeCallbacks(TimerWindow.this.M);
            if (TimerWindow.this.f8792n || TimerWindow.this.f8793o) {
                return;
            }
            TimerWindow.this.f8792n = true;
            GaLog.e("TimerMgr", "onRootTouch: mLongPressRun= " + TimerWindow.this.f8792n);
            TimerWindow timerWindow = TimerWindow.this;
            timerWindow.A(timerWindow.f8781c);
        }
    };

    public TimerWindow(Context context) {
        this.f8797s = context;
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(float f2) {
        int q2 = q(f2);
        int p2 = p(q2);
        int r2 = r(q2);
        if (this.E) {
            n();
            return;
        }
        this.E = true;
        ((TimerItemView) this.C.get(q2)).h();
        ((TimerItemView) this.C.get(q2)).k(this.E);
        this.D.t(q2, p2, r2, o());
        J();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0024, code lost:
    
        if (r1 != 3) goto L70;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean C(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.plugin.timer.TimerWindow.C(android.view.MotionEvent):boolean");
    }

    private void G() {
        TimerMgr.r().y(this.f8794p, this.f8795q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(int i2, int i3, boolean z) {
        if (this.v) {
            this.f8794p += i2;
            this.f8795q += i3;
            k();
            WindowManager.LayoutParams layoutParams = this.f8779a;
            int i4 = layoutParams.x;
            int i5 = this.f8794p;
            if (i4 != i5 || layoutParams.y != this.f8795q) {
                layoutParams.x = i5;
                layoutParams.y = this.f8795q;
                this.t.updateViewLayout(this.f8780b, layoutParams);
            }
            if (z) {
                G();
            }
        }
    }

    private void J() {
        ((Vibrator) this.f8797s.getSystemService("vibrator")).vibrate(100L);
    }

    private void j() {
        if (this.F) {
            return;
        }
        t();
        boolean j2 = RotationMgr.j();
        int P2 = GameAssistWindowManager.P();
        int Q2 = GameAssistWindowManager.Q();
        if (j2) {
            this.J = P2 / 2;
            this.K = (Q2 - (O / 2)) - 50;
        } else {
            this.J = Q2 / 2;
            this.K = (P2 - (O / 2)) - 200;
        }
        this.G.setVisibility(0);
        this.t.addView(this.G, this.I);
        this.F = true;
    }

    private void k() {
        int i2;
        boolean j2 = RotationMgr.j();
        int g2 = RotationMgr.g();
        int f2 = RotationMgr.f();
        int i3 = N;
        if (j2) {
            int i4 = this.f8794p;
            if (i4 < 0) {
                i4 = 0;
            }
            this.f8794p = i4;
            int i5 = this.f8796r;
            if (i4 > (f2 - i5) - i3) {
                i4 = (f2 - i5) - i3;
            }
            this.f8794p = i4;
            int i6 = this.f8795q;
            i2 = i6 >= 0 ? i6 : 0;
            this.f8795q = i2;
            int i7 = Q;
            if (i2 > g2 - i7) {
                i2 = g2 - i7;
            }
            this.f8795q = i2;
        } else {
            int i8 = this.f8794p;
            if (i8 < 0) {
                i8 = 0;
            }
            this.f8794p = i8;
            int i9 = this.f8796r;
            if (i8 > g2 - i9) {
                i8 = g2 - i9;
            }
            this.f8794p = i8;
            int i10 = this.f8795q;
            i2 = i10 >= 0 ? i10 : 0;
            this.f8795q = i2;
            int i11 = Q;
            if (i2 > (f2 - i11) - i3) {
                i2 = (f2 - i11) - i3;
            }
            this.f8795q = i2;
        }
        GaLog.e("TimerMgr", "checkLayoutParams: " + g2 + "x" + f2 + " isHorizontal:" + j2);
    }

    private void m(int i2) {
        this.w.postDelayed(new Runnable() { // from class: cn.nubia.plugin.timer.TimerWindow.4
            @Override // java.lang.Runnable
            public void run() {
                TimerWindow.this.E();
            }
        }, i2);
    }

    private boolean o() {
        return RotationMgr.j() ? this.f8795q < RotationMgr.g() / 2 : this.f8795q < RotationMgr.f() / 2;
    }

    private int p(int i2) {
        if (i2 < 0 || i2 >= this.C.size()) {
            return this.f8794p + (this.f8796r / 2);
        }
        View view = (View) this.C.get(i2);
        if (view != null) {
            return this.f8794p + view.getLeft() + (view.getWidth() / 2);
        }
        int i3 = this.f8794p;
        int i4 = this.f8796r;
        return i3 + ((i4 / 4) * i2) + (i4 / 8);
    }

    private int q(float f2) {
        int i2 = this.f8796r;
        if (f2 < i2 / 4) {
            return 0;
        }
        if (f2 < (i2 / 4) * 2) {
            return 1;
        }
        return f2 < ((float) ((i2 / 4) * 3)) ? 2 : 3;
    }

    private int r(int i2) {
        if (i2 < 0 || i2 >= this.C.size()) {
            return this.f8795q;
        }
        View view = (View) this.C.get(i2);
        return view == null ? this.f8795q : this.f8795q + view.getTop();
    }

    private WindowManager.LayoutParams t() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = O;
        layoutParams.width = P;
        layoutParams.gravity = 51;
        int P2 = GameAssistWindowManager.P();
        int Q2 = GameAssistWindowManager.Q();
        if (RotationMgr.j()) {
            layoutParams.x = (P2 - P) / 2;
            layoutParams.y = (Q2 - O) - 50;
        } else {
            layoutParams.x = (Q2 - P) / 2;
            layoutParams.y = (P2 - O) - 200;
        }
        layoutParams.setTitle("PluginTimerDelete");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        layoutParams.format = -2;
        layoutParams.type = 2038;
        layoutParams.flags = 792;
        this.I = layoutParams;
        return layoutParams;
    }

    private void u() {
        if (this.f8779a == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003, 75826952, -3);
            this.f8779a = layoutParams;
            layoutParams.width = this.f8796r;
            layoutParams.height = Q;
            layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            WindowManager.LayoutParams layoutParams2 = this.f8779a;
            layoutParams2.gravity = 51;
            layoutParams2.setTitle("PluginTimerMain");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f8779a);
        }
        WindowManager.LayoutParams layoutParams3 = this.f8779a;
        layoutParams3.x = this.f8794p;
        layoutParams3.y = this.f8795q;
    }

    private void v() {
        this.y.f(TimerMgr.r().s(0));
        this.z.f(TimerMgr.r().s(1));
        this.A.f(TimerMgr.r().s(2));
        this.B.f(TimerMgr.r().s(3));
        this.y.m();
        this.z.m();
        this.A.m();
        this.B.m();
    }

    private void w() {
        this.y = (TimerItemView) this.f8780b.findViewById(R.id.plugin_timer_0);
        this.z = (TimerItemView) this.f8780b.findViewById(R.id.plugin_timer_1);
        this.A = (TimerItemView) this.f8780b.findViewById(R.id.plugin_timer_2);
        this.B = (TimerItemView) this.f8780b.findViewById(R.id.plugin_timer_3);
        this.C.add(this.y);
        this.C.add(this.z);
        this.C.add(this.A);
        this.C.add(this.B);
    }

    private void x() {
        if (this.f8780b != null) {
            return;
        }
        if (this.G == null) {
            View f2 = InflaterHelper.f(R.layout.plugin_button_delete, null);
            this.G = f2;
            this.H = (ImageView) f2.findViewById(R.id.plugin_button_delete_img);
        }
        this.f8780b = InflaterHelper.f(R.layout.plugin_timer_root, null);
        w();
        this.f8780b.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.plugin.timer.TimerWindow.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return TimerWindow.this.C(motionEvent);
            }
        });
    }

    private void z(float f2) {
        if (this.E) {
            return;
        }
        int q2 = q(f2);
        GaLog.e("TimerMgr", "onItemClick index=" + q2);
        ((TimerItemView) this.C.get(q2)).e();
    }

    public void B(int i2) {
        if (this.E) {
            n();
        }
        GaLog.e("TimerMgr", "onClick index=" + i2);
        if (((TimerItemView) this.C.get(i2)).g()) {
            return;
        }
        ((TimerItemView) this.C.get(i2)).h();
        ((TimerItemView) this.C.get(i2)).e();
    }

    public void D(int i2) {
        if (this.v) {
            ((TimerItemView) this.C.get(i2)).f(TimerMgr.r().s(i2));
            this.f8780b.invalidate();
        }
    }

    public void E() {
        if (this.F) {
            this.t.removeView(this.G);
            this.F = false;
        }
    }

    public void F() {
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            ((TimerItemView) it.next()).h();
        }
    }

    public void H(int i2, int i3) {
        if (!this.v) {
            this.v = true;
            this.u = Utils.j();
            this.f8794p = i2;
            this.f8795q = i3;
            u();
            x();
            this.t.addView(this.f8780b, this.f8779a);
            GaLog.e("TimerMgr", "showPluginWindow: " + this.u + this.f8794p + " ");
        }
        v();
    }

    public void l() {
        if (this.v) {
            this.v = false;
            this.t.removeView(this.f8780b);
            n();
            GaLog.e("TimerMgr", "closePluginWindow: " + this.u);
        }
    }

    public void n() {
        this.E = false;
        this.D.g();
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            ((TimerItemView) it.next()).k(this.E);
        }
    }

    public void s() {
        GameAssistApplication j2 = GameAssistApplication.j();
        this.f8797s = j2;
        this.t = (WindowManager) j2.getSystemService(WindowManager.class);
        this.u = Utils.j();
        this.D = new TimerSettingWindow(this.f8797s);
        this.f8796r = this.f8797s.getResources().getDimensionPixelSize(R.dimen.plugin_timer_window_width);
        Q = this.f8797s.getResources().getDimensionPixelSize(R.dimen.plugin_timer_window_height);
        N = this.f8797s.getResources().getDimensionPixelSize(R.dimen.plugin_timer_navigation_size);
        P = this.f8797s.getResources().getDimensionPixelSize(R.dimen.plugin_timer_delete_btn_width);
        O = this.f8797s.getResources().getDimensionPixelSize(R.dimen.plugin_timer_delete_btn_height);
    }

    public void y() {
        if (this.v) {
            this.w.postDelayed(new Runnable() { // from class: cn.nubia.plugin.timer.TimerWindow.2
                @Override // java.lang.Runnable
                public void run() {
                    GaLog.e("TimerMgr", "onFoldChange");
                    TimerWindow.this.I(0, 0, true);
                }
            }, 2000L);
        }
    }
}
