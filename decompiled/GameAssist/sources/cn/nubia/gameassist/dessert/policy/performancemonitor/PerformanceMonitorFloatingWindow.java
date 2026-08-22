package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.GameDurationManager;
import cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow;
import cn.nubia.gameassist.dessert.policy.performancemonitor.fpsTicker.FpsTick;
import cn.nubia.gameassist.dessert.policy.performancemonitor.present.UseTimeUtils;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceStatusView;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.gameassist.utils.WindowManagerUtil;
import cn.nubia.hostassist.HostAssistMgr;
import cn.nubia.hostassist.HostDensityHelper;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.common.DisplayMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.ext.common.MutableData;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class PerformanceMonitorFloatingWindow implements GameDurationManager.CallBack {
    private static final List v0 = Arrays.asList("com.tencent.start", "com.nvidia.geforcenow", "com.netease.uuremote", "com.valvesoftware.steamlink", "com.playstation.remoteplay", "com.microsoft.xboxone.smartglass", "com.sand.airdroid", "com.sand.airmirror", "com.limelight", "com.xiaoji.egggame.redmagic", "com.xiaoji.egggame", "com.xunlei.downloadprovider");
    private PerformanceStatusView A;
    private TextView B;
    private TextClock C;
    private TextView D;
    private PerformanceStatusView E;
    private PerformanceStatusView F;
    private TextView G;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean U;
    private float a0;
    private float b0;

    /* renamed from: c, reason: collision with root package name */
    private float f6414c;
    private float c0;
    private float d0;
    private int e0;
    private int f0;
    private int g0;

    /* renamed from: h, reason: collision with root package name */
    private boolean f6415h;
    private boolean h0;

    /* renamed from: i, reason: collision with root package name */
    private boolean f6416i;
    private boolean i0;

    /* renamed from: j, reason: collision with root package name */
    private Context f6417j;
    private int j0;

    /* renamed from: k, reason: collision with root package name */
    private ViewGroup f6418k;
    private SharedPreferencesUtil k0;

    /* renamed from: l, reason: collision with root package name */
    private FrameLayout f6419l;
    private boolean l0;

    /* renamed from: m, reason: collision with root package name */
    private LinearLayout f6420m;

    /* renamed from: n, reason: collision with root package name */
    private CornerRectangle f6421n;

    /* renamed from: o, reason: collision with root package name */
    private CornerRectangle f6422o;

    /* renamed from: p, reason: collision with root package name */
    private FrameLayout f6423p;

    /* renamed from: q, reason: collision with root package name */
    private LinearLayout f6424q;

    /* renamed from: r, reason: collision with root package name */
    private LinearLayout f6425r;

    /* renamed from: s, reason: collision with root package name */
    private LinearLayout f6426s;
    private LinearLayout t;
    private LinearLayout u;
    private WindowManager.LayoutParams v;
    private WindowManagerUtil w;
    private TextClock x;
    private TextView y;
    private PerformanceStatusView z;
    private ArrayList H = new ArrayList();
    private int S = 200;
    private int T = 100;
    private int V = -1;
    private float W = 0.0f;
    private float X = 0.0f;
    private float Y = 0.0f;
    private float Z = 0.0f;
    public String mCurPkg = "";
    private boolean m0 = false;
    private HashSet n0 = new HashSet();
    private Handler o0 = new Handler(Looper.getMainLooper());
    private ContentObserver p0 = new ContentObserver(this.o0) { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z);
            if (Settings.Global.getUriFor("app_mirror_list").equals(uri)) {
                PerformanceMonitorFloatingWindow.this.D();
            }
        }
    };
    private FpsTick q0 = new AnonymousClass2();
    private DisplayMgr.Callback r0 = new DisplayMgr.Callback() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.3
        @Override // com.zte.gameassist.common.DisplayMgr.Callback
        public /* bridge */ /* synthetic */ void on3DDisplayAdded(int i2) {
            super.on3DDisplayAdded(i2);
        }

        @Override // com.zte.gameassist.common.DisplayMgr.Callback
        public /* bridge */ /* synthetic */ void on3DDisplayRemoved(int i2) {
            super.on3DDisplayRemoved(i2);
        }

        @Override // com.zte.gameassist.common.DisplayMgr.Callback
        public void onDisplayAdded(final int i2) {
            PerformanceMonitorFloatingWindow.this.K(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.3.1
                @Override // java.lang.Runnable
                public void run() {
                    GaLog.a("PerformanceMonitorFloatingWindow", "onDisplayAdded displayId = " + i2 + ", mWindowAdd = " + PerformanceMonitorFloatingWindow.this.f6415h);
                    if (PerformanceMonitorFloatingWindow.this.f6415h) {
                        PerformanceMonitorFloatingWindow performanceMonitorFloatingWindow = PerformanceMonitorFloatingWindow.this;
                        performanceMonitorFloatingWindow.X(String.valueOf(performanceMonitorFloatingWindow.j0));
                        GameDurationManager n2 = GameDurationManager.n();
                        PerformanceMonitorFloatingWindow performanceMonitorFloatingWindow2 = PerformanceMonitorFloatingWindow.this;
                        n2.p(performanceMonitorFloatingWindow2.mCurPkg, performanceMonitorFloatingWindow2);
                    }
                }
            });
        }

        @Override // com.zte.gameassist.common.DisplayMgr.Callback
        public /* bridge */ /* synthetic */ void onDisplayChanged(int i2) {
            super.onDisplayChanged(i2);
        }

        @Override // com.zte.gameassist.common.DisplayMgr.Callback
        public /* bridge */ /* synthetic */ void onDisplayRemoved(int i2) {
            super.onDisplayRemoved(i2);
        }
    };
    private View.OnTouchListener s0 = new View.OnTouchListener() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.5
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PerformanceMonitorFloatingWindow.this.C(motionEvent);
            return true;
        }
    };
    private boolean t0 = true;
    private Runnable u0 = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.8
        @Override // java.lang.Runnable
        public void run() {
            GaLog.a("PerformanceMonitorFloatingWindow", "mFadeRunnable mWindowAdd = " + PerformanceMonitorFloatingWindow.this.f6415h + ", mFloatLayout = " + PerformanceMonitorFloatingWindow.this.f6418k);
            if (!PerformanceMonitorFloatingWindow.this.f6415h || PerformanceMonitorFloatingWindow.this.f6418k == null) {
                return;
            }
            PerformanceMonitorFloatingWindow performanceMonitorFloatingWindow = PerformanceMonitorFloatingWindow.this;
            performanceMonitorFloatingWindow.R(performanceMonitorFloatingWindow.t0 ? 0.5f : 1.0f);
        }
    };

    /* renamed from: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow$2, reason: invalid class name */
    class AnonymousClass2 extends FpsTick {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFps$0(int i2) {
            PerformanceMonitorFloatingWindow.this.X(String.valueOf(i2));
        }

        private boolean shouldUpdateFps(String str) {
            return PerformanceMonitorFloatingWindow.this.mCurPkg.equals(str) || "com.zte.convert3d".equals(str) || (SystemMgr.M(PerformanceMonitorFloatingWindow.this.mCurPkg) && "com.tencent.mm".equals(str));
        }

        @Override // cn.nubia.gameassist.dessert.policy.performancemonitor.fpsTicker.FpsTick
        public void onFps(final int i2, float f2, String str, String str2) {
            if (i2 == PerformanceMonitorFloatingWindow.this.j0 || !shouldUpdateFps(str)) {
                return;
            }
            GaLog.a("PerformanceMonitorFloatingWindow", "onFps fps = " + i2 + ", mFps = " + PerformanceMonitorFloatingWindow.this.j0);
            PerformanceMonitorFloatingWindow.this.K(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.d
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceMonitorFloatingWindow.AnonymousClass2.this.lambda$onFps$0(i2);
                }
            });
            PerformanceMonitorFloatingWindow.this.j0 = i2;
        }
    }

    public PerformanceMonitorFloatingWindow(Context context, boolean z, boolean z2) {
        this.f6417j = context;
        this.l0 = z2;
        this.k0 = SharedPreferencesUtil.k(context);
        this.i0 = z;
        this.w = new WindowManagerUtil((WindowManager) this.f6417j.getSystemService("window"));
        this.J = this.f6417j.getResources().getInteger(R.integer.performance_monitor_window_width);
        this.K = this.f6417j.getResources().getInteger(R.integer.performance_monitor_window_height);
        this.P = this.f6417j.getResources().getInteger(R.integer.special_electricity_view_width);
        this.Q = this.f6417j.getResources().getInteger(R.integer.special_current_time_view_width);
        this.f6414c = this.f6417j.getResources().getInteger(R.integer.performance_monitor_animation_translation_distance);
        this.g0 = this.f6417j.getResources().getConfiguration().orientation;
        I();
        if (FoldMgr.f()) {
            GameAssistWindowManager.R.e(true, new MutableData.Observer() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.b
                @Override // com.zte.gameassist.ext.common.MutableData.Observer
                public final void a(Object obj) {
                    PerformanceMonitorFloatingWindow.this.M(((Integer) obj).intValue());
                }
            });
        }
        if (z) {
            this.f6417j.getContentResolver().registerContentObserver(Settings.Global.getUriFor("app_mirror_list"), false, this.p0);
        }
    }

    private int A(float f2) {
        float f3 = this.L / this.I;
        if (f2 < 1.0f * f3) {
            return 0;
        }
        if (f2 < 2.0f * f3) {
            return 1;
        }
        if (f2 < 3.0f * f3) {
            return 2;
        }
        return f2 < f3 * 4.0f ? 3 : 4;
    }

    private List B(boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.H.get(this.V);
        float x = linearLayout.getX();
        int width = linearLayout.getWidth();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.I; i2++) {
            int i3 = this.V;
            if (i2 != i3) {
                arrayList.add(i2 < i3 ? ObjectAnimator.ofFloat(this.H.get(i2), "translationX", z ? -this.f6414c : 0.0f, z ? 0.0f : -this.f6414c) : ObjectAnimator.ofFloat(this.H.get(i2), "translationX", z ? this.f6414c : 0.0f, z ? 0.0f : this.f6414c));
            }
        }
        float f2 = width;
        int i4 = this.L;
        float f3 = f2 / i4;
        final float f4 = x / (((i4 - x) - f2) + x);
        final float f5 = (f2 * f4) + x;
        ValueAnimator ofFloat = z ? ObjectAnimator.ofFloat(f3, 1.0f) : ObjectAnimator.ofFloat(1.0f, f3);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = PerformanceMonitorFloatingWindow.this.L * ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PerformanceMonitorFloatingWindow.this.f6421n.setRectanglePara(f5 - (f4 * floatValue), floatValue);
            }
        });
        arrayList.add(ofFloat);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(MotionEvent motionEvent) {
        if (this.f6416i) {
            GaLog.e("PerformanceMonitorFloatingWindow", "handleActionMove: animation running!");
            return;
        }
        this.d0 = motionEvent.getRawX();
        this.c0 = motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.h0 = true;
            y(false);
            this.b0 = motionEvent.getRawX();
            this.a0 = motionEvent.getRawY();
            this.Y = motionEvent.getX();
            this.Z = motionEvent.getY();
            if (this.U) {
                this.L = this.R;
                return;
            } else {
                this.L = this.J;
                return;
            }
        }
        if (action != 1) {
            if (action == 2) {
                if (!this.h0) {
                    GaLog.e("PerformanceMonitorFloatingWindow", "handleActionMove: ACTION_MOVE, don't have down event!");
                    return;
                }
                this.W = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                this.X = rawY;
                this.S = (int) (this.W - this.Y);
                this.T = (int) (rawY - this.Z);
                a0();
                return;
            }
            if (action != 3) {
                return;
            }
        }
        this.h0 = false;
        this.L = this.J;
        if (Math.pow(this.d0 - this.b0, 2.0d) + Math.pow(this.c0 - this.a0, 2.0d) < Math.pow(24.0d, 2.0d)) {
            N(motionEvent.getX());
        } else {
            P();
        }
        this.W = motionEvent.getRawX();
        this.X = motionEvent.getRawY();
        this.Z = 0.0f;
        this.Y = 0.0f;
        y(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        String o2 = HostAssistMgr.n().o();
        GaLog.e("PerformanceMonitorFloatingWindow", "handleMirrorAppChange pkgName=" + o2 + ", mWindowAdd = " + this.f6415h);
        if (this.f6415h) {
            E(o2);
        }
    }

    private void E(String str) {
        GaLog.e("PerformanceMonitorFloatingWindow", "handleNotSupportFpsApp pkg = " + str);
        if (J(str)) {
            TextView textView = this.y;
            if (textView != null) {
                textView.setText(" --FPS");
            }
            TextView textView2 = this.D;
            if (textView2 != null) {
                textView2.setText(" --FPS");
                return;
            }
            return;
        }
        TextView textView3 = this.y;
        if (textView3 != null) {
            textView3.setText(" 00FPS");
        }
        TextView textView4 = this.D;
        if (textView4 != null) {
            textView4.setText(" 00FPS");
        }
        this.q0.register();
    }

    private void F() {
        if (this.U) {
            this.f6418k = this.f6423p;
            this.F.h();
            this.E.h();
        } else {
            this.f6418k = this.f6419l;
            this.z.h();
            this.A.h();
        }
        this.H.clear();
        int childCount = this.f6420m.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            this.H.add((LinearLayout) this.f6420m.getChildAt(i2));
        }
        this.I = this.H.size();
        this.f6418k.setOnTouchListener(this.s0);
    }

    private WindowManager.LayoutParams G() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(this.i0 ? 2027 : 2008, 75826952, -3);
        this.v = layoutParams;
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        StringBuilder sb = new StringBuilder();
        sb.append("PerformanceMonitorFloatingWindow");
        sb.append(this.i0 ? "Host" : "");
        layoutParams.setTitle(sb.toString());
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.v);
        WindowManager.LayoutParams layoutParams2 = this.v;
        layoutParams2.gravity = 51;
        layoutParams2.x = this.S;
        layoutParams2.y = this.T;
        layoutParams2.width = this.L;
        layoutParams2.height = this.M;
        GaLog.a("PerformanceMonitorFloatingWindow", "initLayoutParams: mLayoutParams = " + this.v + " , mIsViewGroupShorten = " + this.U);
        if (this.U) {
            WindowManager.LayoutParams layoutParams3 = this.v;
            int i2 = this.R;
            layoutParams3.width = i2;
            this.L = i2;
        }
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        this.L = this.J;
        this.M = this.K;
        this.e0 = this.l0 ? 0 : this.f6417j.getResources().getInteger(R.integer.performance_monitor_space_up_down);
        this.f0 = this.f6417j.getResources().getInteger(R.integer.host_performance_monitor_space_up_down);
    }

    private void I() {
        GaLog.e("PerformanceMonitorFloatingWindow", "initView " + this.i0);
        if (this.i0) {
            HostDensityHelper.e(this.f6417j);
            this.f6419l = (FrameLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.performance_monitor_layout, (ViewGroup) null);
            this.f6423p = (FrameLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.layout_monitor_shorten, (ViewGroup) null);
            this.f6424q = (LinearLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.layout_monitor_current_time, (ViewGroup) null);
            this.f6425r = (LinearLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.layout_monitor_frame_rate, (ViewGroup) null);
            this.f6426s = (LinearLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.layout_monitor_net_speed, (ViewGroup) null);
            this.t = (LinearLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.layout_monitor_electricity, (ViewGroup) null);
            this.u = (LinearLayout) LayoutInflater.from(this.f6417j).inflate(R.layout.layout_monitor_game_duration, (ViewGroup) null);
        } else {
            this.f6419l = (FrameLayout) InflaterHelper.f(R.layout.performance_monitor_layout, null);
            this.f6423p = (FrameLayout) InflaterHelper.f(R.layout.layout_monitor_shorten, null);
            this.f6424q = (LinearLayout) InflaterHelper.f(R.layout.layout_monitor_current_time, null);
            this.f6425r = (LinearLayout) InflaterHelper.f(R.layout.layout_monitor_frame_rate, null);
            this.f6426s = (LinearLayout) InflaterHelper.f(R.layout.layout_monitor_net_speed, null);
            this.t = (LinearLayout) InflaterHelper.f(R.layout.layout_monitor_electricity, null);
            this.u = (LinearLayout) InflaterHelper.f(R.layout.layout_monitor_game_duration, null);
        }
        this.f6420m = (LinearLayout) this.f6419l.findViewById(R.id.performance_monitor_content);
        this.f6421n = (CornerRectangle) this.f6419l.findViewById(R.id.performance_monitor_background);
        this.x = (TextClock) this.f6419l.findViewById(R.id.performance_monitor_current_time);
        this.y = (TextView) this.f6419l.findViewById(R.id.frame_rate);
        this.z = (PerformanceStatusView) this.f6419l.findViewById(R.id.performance_monitor_net_speed);
        this.A = (PerformanceStatusView) this.f6419l.findViewById(R.id.performance_monitor_electricity);
        this.B = (TextView) this.f6419l.findViewById(R.id.performance_monitor_game_duration);
        this.f6422o = (CornerRectangle) this.f6423p.findViewById(R.id.performance_monitor_background);
        this.C = (TextClock) this.f6424q.findViewById(R.id.performance_monitor_current_time);
        this.D = (TextView) this.f6425r.findViewById(R.id.frame_rate);
        this.E = (PerformanceStatusView) this.f6426s.findViewById(R.id.performance_monitor_net_speed);
        this.F = (PerformanceStatusView) this.t.findViewById(R.id.performance_monitor_electricity);
        this.G = (TextView) this.u.findViewById(R.id.performance_monitor_game_duration);
        T();
        b0();
    }

    private boolean J(String str) {
        GaLog.e("PerformanceMonitorFloatingWindow", "isNotSupportFpsApp mCurPkg = " + this.mCurPkg + ", mHostDisplay = " + this.i0 + ", pkg = " + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return v0.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(int i2) {
        GaLog.a("PerformanceMonitorFloatingWindow", "onFoldStateChanged state = " + i2 + ", mIsFoldChange" + this.m0);
        I();
        if (!SystemMgr.H() || !this.f6415h) {
            GaLog.a("PerformanceMonitorFloatingWindow", "onFoldStateChanged not in game or not added so return!");
            return;
        }
        if (this.m0) {
            return;
        }
        this.m0 = true;
        removeFloatView();
        addFloatView(SystemMgr.t());
        GameDurationManager.n().p(this.mCurPkg, this);
        this.m0 = false;
    }

    private void N(float f2) {
        int A = A(f2);
        GaLog.e("PerformanceMonitorFloatingWindow", "onItemClick index=" + A + " , mIsViewGroupShorten = " + this.U + " , mLastClickIndex = " + this.V);
        if (this.U) {
            int x = (int) ((LinearLayout) this.H.get(this.V)).getX();
            WindowManager.LayoutParams layoutParams = this.v;
            int i2 = layoutParams.x - x;
            this.S = i2;
            layoutParams.width = this.J;
            this.U = false;
            Q(i2);
            v(false);
            V(true);
            this.V = -1;
        } else {
            this.U = true;
            LinearLayout linearLayout = (LinearLayout) this.H.get(A);
            this.v.width = linearLayout.getWidth();
            this.R = linearLayout.getWidth();
            int x2 = (int) linearLayout.getX();
            WindowManager.LayoutParams layoutParams2 = this.v;
            int i3 = x2 + layoutParams2.x;
            layoutParams2.x = i3;
            this.V = A;
            this.U = true;
            Q(i3);
            V(false);
        }
        y(true);
    }

    private void O(PerformanceStatusView performanceStatusView, PerformanceStatusView performanceStatusView2) {
        String str = (String) performanceStatusView2.getText();
        GaLog.a("PerformanceMonitorFloatingWindow", "refreshView oldStatusView.text = " + str + ", newStatusView.text = " + ((Object) performanceStatusView.getText()));
        if (!TextUtils.isEmpty(str)) {
            performanceStatusView.setText(str);
        }
        performanceStatusView.mLastTime = performanceStatusView2.mLastTime;
        performanceStatusView.mLastByte = performanceStatusView2.mLastByte;
    }

    private void P() {
        if (this.v == null) {
            GaLog.a("PerformanceMonitorFloatingWindow", "saveFloatingViewPosition,mLayoutParams is null");
            return;
        }
        this.k0.U(this.U ? "xPosition_short" : "xPosition", this.S);
        this.k0.U("yPosition", this.T);
        this.k0.U("orientation_when_move", this.g0);
        GaLog.a("PerformanceMonitorFloatingWindow", "saveFloatingViewPosition mLayoutParams.x:" + this.v.x + ",mLayoutParams.y:" + this.v.y + " , mPointX = " + this.S + " , mPointY = " + this.T + " , mOrientation = " + this.g0);
    }

    private void Q(int i2) {
        if (this.v == null) {
            GaLog.a("PerformanceMonitorFloatingWindow", "saveFloatingViewPosition,mLayoutParams is null");
            return;
        }
        GaLog.a("PerformanceMonitorFloatingWindow", "saveFloatingX: x = " + i2 + " , mIsViewGroupShorten = " + this.U);
        this.k0.U(this.U ? "xPosition_short" : "xPosition", i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(float f2) {
        CornerRectangle cornerRectangle = this.f6422o;
        if (cornerRectangle != null) {
            cornerRectangle.setAlpha(f2);
        }
        CornerRectangle cornerRectangle2 = this.f6421n;
        if (cornerRectangle2 != null) {
            cornerRectangle2.setAlpha(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public void L(String str) {
        GaLog.a("PerformanceMonitorFloatingWindow", "setGameDuration hour = " + str + " " + this.f6415h);
        if (this.f6415h) {
            ViewGroup viewGroup = this.f6418k;
            if (viewGroup == null || viewGroup.getVisibility() != 0) {
                GaLog.a("PerformanceMonitorFloatingWindow", "setGameDuration cannot set because mFloatLayout = " + this.f6418k + " , mOpenMonitorGameDurationView = " + this.B);
                return;
            }
            TextView textView = this.B;
            if (textView != null) {
                textView.setText(this.f6417j.getString(R.string.game_duration_template, str));
            }
            TextView textView2 = this.G;
            if (textView2 != null) {
                textView2.setText(this.f6417j.getString(R.string.game_duration_template, str));
            }
        }
    }

    private void T() {
        setTextDefaultFont(this.x);
        setTextDefaultFont(this.y);
        setTextDefaultFont(this.z);
        setTextDefaultFont(this.A);
        setTextDefaultFont(this.B);
        setTextDefaultFont(this.C);
        setTextDefaultFont(this.E);
        setTextDefaultFont(this.F);
        setTextDefaultFont(this.D);
        setTextDefaultFont(this.G);
    }

    private void U(View view, int i2) {
        view.getLayoutParams().width = i2;
    }

    private void V(final boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(B(z));
        if (z) {
            animatorSet.setInterpolator(new AccelerateInterpolator());
            animatorSet.setDuration(100L);
        } else {
            animatorSet.setInterpolator(new OvershootInterpolator(0.5f));
            animatorSet.setDuration(300L);
        }
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.7
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                PerformanceMonitorFloatingWindow.this.f6416i = false;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    PerformanceMonitorFloatingWindow.this.f6422o.setRectanglePara(0.0f, PerformanceMonitorFloatingWindow.this.R);
                    PerformanceMonitorFloatingWindow.this.v(true);
                }
                PerformanceMonitorFloatingWindow.this.f6416i = false;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                PerformanceMonitorFloatingWindow.this.f6416i = false;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                PerformanceMonitorFloatingWindow.this.f6416i = true;
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setInterpolator(new AccelerateInterpolator());
        animatorSet2.setDuration(z ? 100L : 50L);
        animatorSet2.playTogether(z(z));
        animatorSet2.start();
        animatorSet.start();
    }

    private void W() {
        WindowManager.LayoutParams layoutParams = this.v;
        if (layoutParams == null) {
            GaLog.a("PerformanceMonitorFloatingWindow", "tranPositionValue: mLayoutParams is null!");
            return;
        }
        int i2 = layoutParams.x;
        int i3 = layoutParams.y;
        layoutParams.x = i3;
        layoutParams.y = i2;
        this.S = i3;
        this.T = i2;
        GaLog.a("PerformanceMonitorFloatingWindow", "tranPositionValue, x:" + this.v.x + " y:" + this.v.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X(String str) {
        GaLog.a("PerformanceMonitorFloatingWindow", "updateFps fp=" + str + ",mWindowAdd=" + this.f6415h + ",mOpenFpsView=" + this.y);
        if (this.f6415h) {
            ViewGroup viewGroup = this.f6418k;
            if (viewGroup == null || viewGroup.getVisibility() != 0) {
                GaLog.a("PerformanceMonitorFloatingWindow", "updateFps cannot update fps because mFloatLayout = " + this.f6418k + " , mOpenFpsView = " + this.y);
                return;
            }
            try {
                TextView textView = this.D;
                if (textView != null) {
                    textView.setText(this.f6417j.getString(R.string.frame_rate_template, str));
                } else {
                    GaLog.a("PerformanceMonitorFloatingWindow", "updateFps mShortenFpsView is null");
                }
                TextView textView2 = this.y;
                if (textView2 != null) {
                    textView2.setText(this.f6417j.getString(R.string.frame_rate_template, str));
                } else {
                    GaLog.a("PerformanceMonitorFloatingWindow", "updateFps mOpenFpsView is null");
                }
            } catch (Exception e2) {
                GaLog.a("PerformanceMonitorFloatingWindow", "updateFps has exception e = " + e2);
            }
        }
    }

    private void Y() {
        int l2 = this.k0.l(this.U ? "xPosition_short" : "xPosition", -1);
        int l3 = this.k0.l("yPosition", -1);
        GaLog.a("PerformanceMonitorFloatingWindow", "updatePositionWithMemory: xPosition : " + l2 + " yPosition:" + l3 + " , mIsViewGroupShorten = " + this.U);
        if (!this.U && l2 < 0) {
            l2 = 0;
        }
        WindowManager.LayoutParams layoutParams = this.v;
        if (layoutParams == null || l2 == -1 || l3 == -1) {
            return;
        }
        layoutParams.x = l2;
        layoutParams.y = l3;
        this.S = l2;
        this.T = l3;
        GaLog.e("PerformanceMonitorFloatingWindow", "updatePositionWithMemory mLayoutParams=" + this.v);
    }

    private void Z() {
        WindowManagerUtil windowManagerUtil;
        ViewGroup viewGroup = this.f6418k;
        if (viewGroup == null || (windowManagerUtil = this.w) == null) {
            return;
        }
        try {
            if (this.f6415h) {
                windowManagerUtil.c(viewGroup, this.v);
            }
        } catch (IllegalArgumentException e2) {
            GaLog.b("PerformanceMonitorFloatingWindow", "updateViewLayout: " + e2.toString());
        }
    }

    private void a0() {
        w();
        Z();
    }

    private void b0() {
        String language = Locale.getDefault().getLanguage();
        if (language.equals("vi")) {
            U(this.A, this.P);
            U(this.F, this.P);
        } else if (language.equals("th")) {
            U(this.x, this.Q);
            U(this.C, this.Q);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(boolean z) {
        this.f6418k.setOnTouchListener(null);
        try {
            GaLog.a("PerformanceMonitorFloatingWindow", "addOpenOrShortenWindow: mAddedView.size = " + this.n0.size());
            Iterator it = this.n0.iterator();
            while (it.hasNext()) {
                this.w.b((View) it.next());
            }
            this.n0.clear();
            if (z) {
                FrameLayout frameLayout = this.f6423p;
                frameLayout.removeViews(1, frameLayout.getChildCount() - 1);
                this.z.j();
                this.A.j();
                int i2 = this.V;
                if (i2 == 0) {
                    this.C.refreshTime();
                    this.f6423p.addView(this.f6424q);
                } else if (i2 == 1) {
                    this.f6423p.addView(this.f6425r);
                } else if (i2 == 2) {
                    O(this.E, this.z);
                    this.f6423p.addView(this.f6426s);
                } else if (i2 == 3) {
                    O(this.F, this.A);
                    this.f6423p.addView(this.t);
                } else if (i2 == 4) {
                    this.f6423p.addView(this.u);
                }
                this.E.i();
                this.F.i();
                this.f6418k = this.f6423p;
            } else {
                this.E.j();
                this.F.j();
                O(this.z, this.E);
                O(this.A, this.F);
                this.z.i();
                this.A.i();
                this.x.refreshTime();
                this.f6418k = this.f6419l;
                w();
            }
            this.f6418k.setOnTouchListener(this.s0);
            GaLog.a("PerformanceMonitorFloatingWindow", "addOpenOrShortenWindow=" + this.v);
            this.w.a(this.f6418k, this.v);
            this.n0.add(this.f6418k);
        } catch (Exception e2) {
            GaLog.c("PerformanceMonitorFloatingWindow", "addOpenOrShortenWindow exception!", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        boolean z = this.g0 == 2;
        boolean z2 = Settings.Global.getInt(this.f6417j.getContentResolver(), "cc_navi_status", 0) == 1;
        boolean z3 = this.i0;
        int i2 = z3 ? this.N : GameAssistWindowManager.Q;
        int i3 = z3 ? this.O : GameAssistWindowManager.P;
        GaLog.j("PerformanceMonitorFloatingWindow", "checkLayoutParams: mWindowWidth = " + this.L + " , mPointX = " + this.S + " , mPointY = " + this.T + " , isHorizontal = " + z + " , screenWidth = " + i2 + " , screenHeight = " + i3);
        if (!z && z2) {
            i3 -= 120;
        }
        int max = Math.max(this.S, 0);
        this.S = max;
        this.S = Math.min(max, i2 - this.L);
        int max2 = Math.max(this.T, this.i0 ? this.f0 : this.e0);
        this.T = max2;
        int min = Math.min(max2, (i3 - this.M) - (this.i0 ? this.f0 : this.e0));
        this.T = min;
        WindowManager.LayoutParams layoutParams = this.v;
        if (layoutParams != null) {
            int i4 = layoutParams.x;
            int i5 = this.S;
            if (!(i4 == i5 && layoutParams.y == min) && i5 >= 0 && min >= 0) {
                layoutParams.x = i5;
                layoutParams.y = min;
                GaLog.e("PerformanceMonitorFloatingWindow", "checkLayoutParams (" + this.v.x + "," + this.v.y + ")(" + this.v.width + "x" + this.v.height + ")");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public void K(final Runnable runnable) {
        if (this.o0.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            this.o0.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.c
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceMonitorFloatingWindow.this.K(runnable);
                }
            });
        }
    }

    private void y(boolean z) {
        GaLog.a("PerformanceMonitorFloatingWindow", "fadeLayout fade = " + z);
        this.t0 = z;
        this.o0.removeCallbacks(this.u0);
        this.o0.postDelayed(this.u0, this.t0 ? 5000L : 0L);
    }

    private List z(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.I; i2++) {
            if (i2 != this.V) {
                arrayList.add(ObjectAnimator.ofFloat(this.H.get(i2), "alpha", z ? 0.0f : 1.0f, z ? 1.0f : 0.0f));
            }
        }
        return arrayList;
    }

    public void addFloatView(String str) {
        this.mCurPkg = str;
        GaLog.a("PerformanceMonitorFloatingWindow", "addFloatView, mWindowAdd:" + this.f6415h);
        if (this.f6415h) {
            return;
        }
        H();
        F();
        G();
        Y();
        GaLog.a("PerformanceMonitorFloatingWindow", "addFloatView, x:" + this.v.x + " y:" + this.v.y);
        if (!this.f6415h) {
            w();
            if (this.U) {
                this.f6422o.setRectanglePara(0.0f, this.R);
                v(true);
            }
            this.f6418k.setVisibility(0);
            this.f6418k.setId(R.id.performance_monitor_root);
            this.w.a(this.f6418k, this.v);
            this.n0.add(this.f6418k);
            this.f6415h = true;
            E(this.mCurPkg);
            TextClock textClock = this.x;
            if (textClock != null) {
                textClock.refreshTime();
            }
            TextClock textClock2 = this.C;
            if (textClock2 != null) {
                textClock2.refreshTime();
            }
            DisplayMgr.d().a(this.r0);
            GaLog.a("PerformanceMonitorFloatingWindow", "addFloatView mFpsTick register success");
        }
        y(true);
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("    PerformanceMonitorFloatingWindow:");
        printWriter.println("        mWindowAdd=" + this.f6415h);
        printWriter.println("        mFloatLayout=" + this.f6418k);
        if (this.f6418k != null) {
            printWriter.println("        mFloatLayout.getVisibility()=" + this.f6418k.getVisibility());
        }
        printWriter.println("        mOpenLayout=" + this.f6419l);
        printWriter.println("        mShortenLayout=" + this.f6423p);
        if (this.f6422o != null) {
            printWriter.println("        mShortenMonitorBackgroundView.width=" + this.f6422o.getBgWidth());
        }
        printWriter.println("        mOpenMonitorGameDurationView=" + this.B);
        printWriter.println("        mShortenMonitorGameDurationView=" + this.G);
        printWriter.println("        mCurPkg=" + this.mCurPkg);
        printWriter.println("        mViewsSize=" + this.I);
        printWriter.println("        mLastClickIndex=" + this.V);
        printWriter.println("        mWindowWidth=" + this.L);
        printWriter.println("        mPointX=" + this.S);
        printWriter.println("        mPointY=" + this.T);
        printWriter.println("        mIsViewGroupShorten=" + this.U);
        if (this.x != null && this.C != null) {
            printWriter.println("        mOpenCurrentTimeView.getText()=" + ((Object) this.x.getText()));
            printWriter.println("        mShortenCurrentTimeView.getText()=" + ((Object) this.C.getText()));
        }
        if (this.y != null) {
            printWriter.println("        mOpenFpsView=" + this.y);
            printWriter.println("        mOpenFpsView.getText()=" + ((Object) this.y.getText()));
            printWriter.println("        mOpenFpsView.getVisibility()=" + this.y.getVisibility());
            printWriter.println("        mOpenFpsView.getWindowVisibility()=" + this.y.getWindowVisibility());
            printWriter.println("        mOpenFpsView.getWindowToken()=" + this.y.getWindowToken());
        }
        printWriter.println("        mLayoutParams=" + this.v);
    }

    public int getClickIndex() {
        return this.V;
    }

    public boolean getIsViewGroupShorten() {
        return this.U;
    }

    public int getStatusHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public boolean isWindowAdd() {
        return this.f6415h;
    }

    @Override // cn.nubia.gameassist.common.GameDurationManager.CallBack
    public void onBundlePrepare(Bundle bundle) {
        if (bundle == null) {
            GaLog.g("PerformanceMonitorFloatingWindow", "onBundlePrepare: bundle is Null!");
            return;
        }
        long j2 = bundle.getLong("time");
        String msToH = UseTimeUtils.msToH(j2);
        GaLog.g("PerformanceMonitorFloatingWindow", "onBundlePrepare: hour = " + msToH);
        updateGameDuration(msToH);
        MultiSubScreenUtils.x(j2);
    }

    public void onLanguageChanged(String str) {
        GaLog.e("PerformanceMonitorFloatingWindow", "onLanguageChanged: language = " + str);
        I();
        if (SystemMgr.H() && this.f6415h) {
            GaLog.a("PerformanceMonitorFloatingWindow", "onLanguageChanged in game and added so readded!");
            removeFloatView();
            addFloatView(SystemMgr.t());
        }
    }

    public void onOrientationChanged(int i2) {
        this.g0 = i2;
        int l2 = this.k0.l("orientation_when_move", -1);
        int l3 = this.k0.l("orientation_rotate", -1);
        GaLog.a("PerformanceMonitorFloatingWindow", "onOrientationChanged: moveOriention = " + l2 + " , mOrientation = " + this.g0 + " ,rotateOriention = " + l3);
        int i3 = this.g0;
        if (l2 != i3 && l3 != i3) {
            W();
            a0();
            this.k0.U("orientation_rotate", this.g0);
        } else {
            if (l2 != i3 || l3 == i3) {
                return;
            }
            Y();
            a0();
            this.k0.U("orientation_rotate", this.g0);
        }
    }

    public void removeFloatView() {
        GaLog.a("PerformanceMonitorFloatingWindow", "removeFloatView , mWindowAdd:" + this.f6415h);
        this.mCurPkg = "";
        this.z.j();
        this.A.j();
        this.E.j();
        this.F.j();
        if (this.f6415h) {
            this.q0.unRegister();
            this.j0 = 0;
            DisplayMgr.d().f(this.r0);
            Handler handler = this.o0;
            if (handler != null) {
                handler.removeCallbacks(this.u0);
            }
            if (this.f6418k == null || this.w == null) {
                GaLog.a("PerformanceMonitorFloatingWindow", "removeFloatView, is null");
                return;
            }
            GaLog.a("PerformanceMonitorFloatingWindow", "removeFloatView: removeView");
            this.f6418k.setVisibility(8);
            try {
                GaLog.a("PerformanceMonitorFloatingWindow", "removeFloatView: mAddedView.size = " + this.n0.size());
                Iterator it = this.n0.iterator();
                while (it.hasNext()) {
                    this.w.b((View) it.next());
                }
                this.n0.clear();
                this.f6415h = false;
            } catch (Exception e2) {
                GaLog.b("PerformanceMonitorFloatingWindow", "removeFloatView: " + e2.toString());
            }
        }
    }

    public void setTextDefaultFont(TextView textView) {
        if (textView != null) {
            textView.setTypeface(Utils.f7698c);
        }
    }

    public void updateCurrPkg(String str) {
        this.mCurPkg = str;
    }

    public void updateFullScreen(boolean z) {
        GaLog.a("PerformanceMonitorFloatingWindow", "updateFullScreen  isFullScreen = " + z + ", mHostDisplay = " + this.i0 + ", mWindowAdd = " + this.f6415h + ", mIsFullScreen = " + this.l0);
        if (this.i0 || !this.f6415h || this.l0 == z) {
            GaLog.a("PerformanceMonitorFloatingWindow", "updateFullScreen  not need update ui !");
            this.l0 = z;
        } else {
            this.l0 = z;
            K(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorFloatingWindow.4
                @Override // java.lang.Runnable
                public void run() {
                    PerformanceMonitorFloatingWindow.this.H();
                    PerformanceMonitorFloatingWindow.this.w();
                    GameDurationManager n2 = GameDurationManager.n();
                    PerformanceMonitorFloatingWindow performanceMonitorFloatingWindow = PerformanceMonitorFloatingWindow.this;
                    n2.p(performanceMonitorFloatingWindow.mCurPkg, performanceMonitorFloatingWindow);
                }
            });
        }
    }

    public void updateGameDuration(final String str) {
        K(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.a
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceMonitorFloatingWindow.this.L(str);
            }
        });
    }

    public void updateHostScreenSize(int i2, int i3) {
        if (this.N == i2 && this.O == i3) {
            return;
        }
        this.N = i2;
        this.O = i3;
        if (this.f6415h) {
            a0();
        }
    }
}
